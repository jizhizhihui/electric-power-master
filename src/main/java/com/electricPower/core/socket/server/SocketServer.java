package com.electricPower.core.socket.server;

import com.electricPower.common.config.ApplicationContextProvider;
import com.electricPower.core.socket.constants.SocketConstant;
import com.electricPower.project.service.IMeterDataService;
import com.electricPower.project.service.impl.MeterDataServiceImpl;
import com.electricPower.utils.FrameUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 丁许
 */
@Slf4j
@Data
public class SocketServer {

    private ServerSocket serverSocket;

    /**
     * 服务监听主线程
     */
    private ListeningThread listeningThread;

    /**
     * 用户扫已有的socket处理线程
     * 1. 没有的线程不引用
     * 2. 关注是否有心跳
     * 3. 关注是否超过登陆时间
     */
    private ScheduledExecutorService scheduleSocketMonitorExecutor = Executors
            .newSingleThreadScheduledExecutor(r -> new Thread(r, "socket_monitor_" + r.hashCode()));

    /**
     * 存储只要有socket处理的线程
     */
    private List<ConnectionThread> existConnectionThreadList = Collections.synchronizedList(new ArrayList<>());

    /**
     * 存储当前由用户信息活跃的的socket线程
     */
    private ConcurrentMap<String, Connection> existSocketMap = new ConcurrentHashMap<>();

    public SocketServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            log.error("本地socket服务启动失败.exception:{}", e);
        }
    }

    /**
     * 开一个线程来开启本地socket服务，开启一个monitor线程
     */
    public void start() {
//        FrameUtils.svarTest(meterDataService);
        listeningThread = new ListeningThread(this);
        listeningThread.start();
        //每隔1s扫一次ThreadList
        scheduleSocketMonitorExecutor.scheduleWithFixedDelay(() -> {
            Date now = new Date();
            existConnectionThreadList.forEach(connectionThread -> {
                    //心跳验证
                    Date lastOnTime = connectionThread.getConnection().getLastOnTime();
                    long heartDuration = now.getTime() - lastOnTime.getTime();
                    if (heartDuration > SocketConstant.HEART_RATE) {
                        //心跳超时,关闭当前线程
                        log.warn("心跳超时");
                        connectionThread.stopRunning();
                        existConnectionThreadList.remove(connectionThread);
//                        existConnectionThreadList
                }
            });
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * 关闭本地socket服务
     */
    public void close() {
        try {
            //先关闭monitor线程，防止遍历list的时候
            scheduleSocketMonitorExecutor.shutdownNow();
            if (serverSocket != null && !serverSocket.isClosed()) {
                listeningThread.stopRunning();
                listeningThread.suspend();
                listeningThread.stop();

                serverSocket.close();
            }
        } catch (IOException e) {
            log.error("SocketServer.close failed.exception:{}", e);
        }
    }

    //socket 测试
    public static void main(String[] args) throws IOException {
        // TODO 服务端处理客户端连接请求
        ServerSocket serverSocket = new ServerSocket(2323);
        // 接收到客户端连接请求之后为每个客户端创建一个新的线程进行链路处理
        new Thread(() -> {
            while (true) {
                try {
                    // 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();
                    // 每一个新的连接都创建一个线程，负责读取数据
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            // 按字节流方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) { }
                    }).start();
                } catch (IOException e) { }
            }
        }).start();
    }
}