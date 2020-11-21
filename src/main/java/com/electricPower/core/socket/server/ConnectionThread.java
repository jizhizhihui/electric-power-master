package com.electricPower.core.socket.server;

import com.electricPower.common.config.ApplicationContextProvider;
import com.electricPower.common.exception.frame.FrameCheckFailureException;
import com.electricPower.core.Dataframe.CtrlFrame;
import com.electricPower.core.Dataframe.DetermineFrame;

import com.electricPower.common.exception.frame.FrameInvalidCtrlException;
import com.electricPower.core.Dataframe.downlink.FrameAnswer;
//import com.JZhi.rabbitmq.MsgProducer;
import com.electricPower.core.socket.client.SocketClient;
//import com.electricPower.project.entity.AlarmInfo;
import com.electricPower.project.entity.AlarmInfo;
import com.electricPower.project.entity.MeterData;
//import com.electricPower.project.service.IAlarmInfoService;
import com.electricPower.project.entity.Terminal;
import com.electricPower.project.service.IAlarmInfoService;
import com.electricPower.project.service.IMeterDataService;
import com.electricPower.project.service.ITcpFlowService;
import com.electricPower.project.service.ITerminalService;
import com.electricPower.project.service.impl.MeterDataServiceImpl;
import com.electricPower.utils.FrameUtils;
import com.electricPower.utils.HexUtils;
import com.electricPower.utils.StringUtils;
import javafx.application.Application;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 每一个client连接开一个线程
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class ConnectionThread extends Thread {

    //数据库操作
    private IMeterDataService meterDataService;
    private IAlarmInfoService alarmInfoService;
    private ITerminalService terminalService;
    private ITcpFlowService tcpFlowService;

//    private MsgProducer msgProducer;

    /**
     * 客户端的socket
     */
    private Socket socket;

    /**
     * 服务socket
     */
    private SocketServer socketServer;

    /**
     * 封装的客户端连接socket
     */
    private Connection connection;

    /**
     * 判断当前连接是否运行
     */
    private boolean isRunning;

    /**
     * 有效数据帧
     */
    private List<String> subpackage;

    /**
     * 分包
     */
    private StringBuilder frame;

    public ConnectionThread(Socket socket, SocketServer socketServer) {
        this.meterDataService = ApplicationContextProvider.getBean(IMeterDataService.class);
        this.alarmInfoService = ApplicationContextProvider.getBean(IAlarmInfoService.class);
        this.terminalService = ApplicationContextProvider.getBean(ITerminalService.class);
        this.tcpFlowService = ApplicationContextProvider.getBean(ITcpFlowService.class);

//        this.msgProducer = ApplicationContextProvider.getBean(MsgProducer.class);
        this.socket = socket;
        this.socketServer = socketServer;
        connection = new Connection(socket, this);
        Date now = new Date();
        connection.setCreateTime(now);
        connection.setLastOnTime(now);
        isRunning = true;
        subpackage = new ArrayList<>();
        frame = new StringBuilder();
    }

    @Override
    public void run() {
        while (isRunning) {

            if (socket.isClosed()) {
                isRunning = false;
                break;
            }
            try {
                byte[] bytes = new byte[1024];
                while (socket.getInputStream().read(bytes) > 0) {
                    //接受数据
//                    log.info("服务端收到消息：" + HexUtils.encodeHexStr(bytes, false).replaceAll("0+$", ""));
                    int s = HexUtils.encodeHexStr(bytes, false).replaceAll("0+$", "").length() / 2;
                    connection.getTcpFlow().transferData(s);

                    getFrame(bytes);
                    Arrays.fill(bytes, (byte) 0);
                    log.info("服务端收到有效数据帧：" + subpackage.toString());

                    //更新心跳判断时间
                    Date now = new Date();
                    connection.setLastOnTime(now);

                    //操作数据
                    for (String message : subpackage) {
                        doFrame(message);
                    }
                    subpackage.clear();
                }

            } catch (Exception e) {
                log.error("ConnectionThread.run failed. Exception:{}", e.getMessage());
                connection.getTcpFlow().connLogin();
                connection.getTcpFlow().setTime();
                this.stopRunning();
            }
        }
    }

    public void stopRunning() {
        log.info("停止socket连接,ip:{}", this.socket.getInetAddress().toString());
        try {
            terminalClose();
            tcpClose();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tcpClose() {
        if (!connection.getTcpFlow().getAddress().equals("") && connection.getTcpFlow().getByteNum()!=0) {
            tcpFlowService.save(connection.getTcpFlow());
            connection.getTcpFlow().setByteNum(0);
        }
    }

    private void terminalClose() {
        if (!connection.getTerminal().getTerminalNum().equals("")) {
            connection.getTerminal().setIsAlive(false);
            terminalService.updateById(connection.getTerminal());
        }
    }


    /**
     * 获取数据帧
     *
     * @param bytes 字节数组
     */
    private void getFrame(byte[] bytes) {

        StringBuilder stringBuilder = new StringBuilder();
        int start = 0, len;

        if (frame.length() > 0) {
            String[] strings = frame.toString().split(" ");
            for (int i = strings.length; i < Integer.parseInt(strings[1], 16); i++) {
                frame.append(HexUtils.byteToHex(bytes[i]));
                if (i != Integer.parseInt(strings[1]) - 1)
                    frame.append(" ");
            }
            subpackage.add(frame.toString());
            frame.delete(0, frame.length());
            start += Integer.parseInt(strings[1]);
        }

        while (Integer.toHexString(bytes[start]).equals("43")) {
            len = bytes[start + 1];
            if (len >= bytes.length) {
                len = bytes.length;
            }
            for (int i = start; i < len; i++) {
                stringBuilder.append(HexUtils.byteToHex(bytes[i]));
                if (i != len - 1)
                    stringBuilder.append(" ");
                if (i == len - 1 && bytes[i] != 22) {
                    break;
                }
            }
            start += len;
            if (len != bytes.length && bytes[start - 1] == 22)
                subpackage.add(stringBuilder.toString());
            else
                frame.append(stringBuilder);
        }
    }

    /**
     * 操作数据帧（检验，返回，存储）
     *
     * @param message 数据帧
     */
    private void doFrame(String message) {
        DetermineFrame determineFrame = new DetermineFrame();
        determineFrame.setDetermineFrame(message.split(" "));

        if (determineFrame.checkFrame()) {

            connection.getTerminal().setTerminalNum(determineFrame.getAddressNoSpace());
            connection.getTerminal().setIsAlive(true);
            terminalService.updateById(connection.getTerminal());

            connection.getTcpFlow().setAddress(determineFrame.getAddressNoSpace());
            connection.getTcpFlow().setTime();
            tcpFlowService.save(connection.getTcpFlow());
            connection.getTcpFlow().setByteNum(0);

            //验证终端，存储连接
            terminalLogin(determineFrame.getAddressNoSpace());

            String ctrl = determineFrame.getCtrl();
            if (CtrlFrame.HEART.getVal().equals(ctrl)) {

                log.info(CtrlFrame.HEART.getMsg());
                AnswerFramePrint("80", determineFrame.getAddress(), "00");

            } else if (CtrlFrame.LINE.getVal().equals(ctrl)) {

                log.info(CtrlFrame.LINE.getMsg());
                MeterData line = FrameUtils.analysisLien(message, true);
                if (line != null) {
                    line.setTerminalNum(determineFrame.getAddressNoSpace());
                    meterDataService.save(line);
                }
                AnswerFramePrint("80", determineFrame.getAddress(), "00");

            } else if (CtrlFrame.MASTER.getVal().equals(ctrl)) {

                log.info(CtrlFrame.MASTER.getMsg());
                MeterData master = FrameUtils.analysisLien(message, false);
                if (master != null) {
                    master.setTerminalNum(determineFrame.getAddress().replace(" ", ""));
                    meterDataService.save(master);
                }
                AnswerFramePrint("80", determineFrame.getAddress(), "00");

            } else if (CtrlFrame.ALARM.getVal().equals(ctrl)) {

                log.info(CtrlFrame.ALARM.getMsg());
                AlarmInfo alarmInfo = FrameUtils.analysisAlarm(message);
                alarmInfo.setTerminalNum(determineFrame.getAddressNoSpace());
                alarmInfoService.save(alarmInfo);

//                msgProducer.sendMsg("曾勇2");
                AnswerFramePrint("80", determineFrame.getAddress(), "00");
            } else {
                log.warn("控制字节 " + ctrl + " 无效");

//                throw new FrameInvalidCtrlException();
            }

        } else {
            AnswerFramePrint("C0", determineFrame.getAddress(), "02");
            log.info("失败数据帧：" + determineFrame.toString());
            throw new FrameCheckFailureException("数据帧校验失败");
        }
    }

    /**
     * 终端验证 登录，存储连接
     * @param terminalNum
     */
    private void terminalLogin(String terminalNum){
        if (!socketServer.getExistSocketMap().containsKey(terminalNum)){
            //终端校验
            if (terminalService.getById(terminalNum) != null) {
                log.info(String.valueOf(terminalService.getById(terminalNum)));
                socketServer.getExistSocketMap().put(terminalNum, connection);
            }
        }
    }


    private void AnswerFramePrint(String ctrl, String address, String ansFlag) {
        FrameAnswer frameAnswer = new FrameAnswer(ctrl, address, ansFlag);
        frameAnswer.setAddress(FrameUtils.reverseAddress(frameAnswer.toString()));
        log.info("应答帧：" + frameAnswer.toString());
        connection.println(HexUtils.hexToBytes(frameAnswer.toString()));
    }

}