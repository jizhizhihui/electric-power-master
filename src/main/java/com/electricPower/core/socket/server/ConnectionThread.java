package com.electricPower.core.socket.server;

import com.electricPower.common.config.ApplicationContextProvider;
import com.electricPower.common.exception.frame.FrameCheckFailureException;
import com.electricPower.core.Dataframe.CtrlFrame;
import com.electricPower.core.Dataframe.DetermineFrame;

import com.electricPower.common.exception.frame.FrameInvalidCtrlException;
import com.electricPower.core.Dataframe.downlink.FrameAnswer;
import com.electricPower.core.socket.client.SocketClient;
import com.electricPower.project.entity.AlarmInfo;
import com.electricPower.project.entity.MeterData;
import com.electricPower.project.service.IAlarmInfoService;
import com.electricPower.project.service.IMeterDataService;
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
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 每一个client连接开一个线程
 *
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class ConnectionThread extends Thread {

    private IMeterDataService meterDataService;
    private IAlarmInfoService alarmInfoService;

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
                    //log.info("服务端收到消息：" + HexUtils.encodeHexStr(bytes,false).replaceAll("0+$", ""));
                    getFrame(bytes);
                    log.info("服务端收到消息：" + subpackage.toString());

                    //更新心跳判断时间
                    Date now = new Date();
                    connection.setLastOnTime(now);

                    //操作数据
                    for (String message : subpackage)
                        doFrame(message);
                }

            } catch (IOException e) {
                log.error("ConnectionThread.run failed. IOException:{}", e.getMessage());
                this.stopRunning();
            }
        }
    }

    public void stopRunning() {
        log.info("停止socket连接,ip:{}", this.socket.getInetAddress().toString());
        try {
            socket.close();
        } catch (IOException e) {
            log.error("ConnectionThread.stopRunning failed.exception:{}", e);
        }
    }

    /**
     * 获取数据帧
     * @param bytes 字节数组
     */
    private void getFrame(byte[] bytes) {

        StringBuilder stringBuilder = new StringBuilder();
        int start = 0, len;

        if (frame.length() > 0) {
            String[] strings = frame.toString().split(" ");
            for (int i = strings.length; i < Integer.parseInt(strings[1],16); i++) {
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
                log.info("leg: " + len);
                if (len >= bytes.length){
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
                if (len != bytes.length && bytes[start-1] == 22)
                    subpackage.add(stringBuilder.toString());
                else
                    frame.append(stringBuilder);
        }
    }

    /**
     * 操作数据帧（检验，返回，存储）
     * @param message 数据帧
     */
    private void doFrame(String message){
        DetermineFrame determineFrame = new DetermineFrame();
        determineFrame.setDetermineFrame(message.split(" "));

        if (determineFrame.checkFrame()) {

//                        if (socketServer.getExistSocketMap().containsKey(determineFrame.getAddress())) {
//                            Connection existConnection = socketServer.getExistSocketMap().get(determineFrame.getAddress());
//                            existConnection.getConnectionThread().stopRunning();
//                        } else {
//                            //终端校验
//                        }

            String ctrl = determineFrame.getCtrl();
            if (CtrlFrame.HEART.getVal().equals(ctrl)) {

                log.info(CtrlFrame.HEART.getMsg());
                FrameAnswer frameAnswer = new FrameAnswer("12", determineFrame.getAddress(), "80");
                connection.println(frameAnswer.toString());

            } else if (CtrlFrame.LINE.getVal().equals(ctrl)) {

                log.info(CtrlFrame.LINE.getMsg());
                MeterData line = FrameUtils.analysisLien(message, true);
                line.setMeterSn(determineFrame.getAddress());
                meterDataService.save(line);

            } else if (CtrlFrame.MASTER.getVal().equals(ctrl)) {

                log.info(CtrlFrame.MASTER.getMsg());
                MeterData master = FrameUtils.analysisLien(message, false);
                master.setMeterSn(determineFrame.getAddress());
                meterDataService.save(master);

            } else if (CtrlFrame.ALARM.getVal().equals(ctrl)) {

                log.info(CtrlFrame.ALARM.getMsg());
                AlarmInfo alarmInfo = FrameUtils.analysisAlarm(message);
                alarmInfo.setTerminalNum(determineFrame.getAddress());
                alarmInfoService.save(alarmInfo);

            } else {
                log.warn("控制字节 " + ctrl + " 无效");
                throw new FrameInvalidCtrlException();
            }
            //                    //添加到已校验map中
//                    socketServer.getExistSocketMap().put(determineFrame.getAddress(), connection);

        } else {
            FrameAnswer frameAnswer = new FrameAnswer("12", determineFrame.getAddress(), "C0");
            log.error("检验失败，应答帧：" + frameAnswer.toString());
            log.info("失败数据帧：" + determineFrame.toString());
            connection.println(frameAnswer.toString());
            throw new FrameCheckFailureException("数据帧校验失败");
        }
    }
}