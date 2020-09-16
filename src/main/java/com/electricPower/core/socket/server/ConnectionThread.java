package com.electricPower.core.socket.server;

import com.electricPower.core.Dataframe.CtrlFrame;
import com.electricPower.core.Dataframe.DetermineFrame;

import com.electricPower.common.exception.frame.FrameInvalidCtrlException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

/**
 * 每一个client连接开一个线程
 *
 * @author 丁许
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class ConnectionThread extends Thread {

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

    public ConnectionThread(Socket socket, SocketServer socketServer) {
        this.socket = socket;
        this.socketServer = socketServer;
        connection = new Connection(socket, this);
        Date now = new Date();
        connection.setCreateTime(now);
        connection.setLastOnTime(now);
        isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            // Check whether the socket is closed.
            if (socket.isClosed()) {
                isRunning = false;
                break;
            }
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while ((message = reader.readLine()) != null) {
                    log.info("服务端收到消息：" + message);
                }

                //更新心跳判断时间
                Date now = new Date();
                connection.setLastOnTime(now);

                assert false;
                DetermineFrame determineFrame = new DetermineFrame(message.split(" "));
                determineFrame.checkFrame();

                if (socketServer.getExistSocketMap().containsKey(determineFrame.getAddress())) {
                    Connection existConnection = socketServer.getExistSocketMap().get(determineFrame.getAddress());
                    existConnection.getConnectionThread().stopRunning();
                } else {
                    //终端校验
                }

                String ctrl = determineFrame.getCtrl();
                if (CtrlFrame.LINE.getVal().equals(ctrl)){
                    log.info(CtrlFrame.LINE.getMsg());

                } else  if (CtrlFrame.MASTER.getVal().equals(ctrl)) {
                    log.info(CtrlFrame.MASTER.getMsg());

                }else  if (CtrlFrame.HEART.getVal().equals(ctrl)){
                    log.info(CtrlFrame.HEART.getMsg());}

                else  if (CtrlFrame.ALARM.getVal().equals(ctrl)) {
                    log.info(CtrlFrame.ALARM.getMsg());

                }else {
                    log.warn("控制字节 " + ctrl + " 无效");
                    throw new FrameInvalidCtrlException();
                }

                //添加到已校验map中
                socketServer.getExistSocketMap().put(determineFrame.getAddress(), connection);

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
}