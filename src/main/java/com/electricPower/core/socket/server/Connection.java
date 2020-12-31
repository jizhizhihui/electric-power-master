package com.electricPower.core.socket.server;

import com.electricPower.common.config.ApplicationContextProvider;
import com.electricPower.project.entity.TcpFlow;
import com.electricPower.project.entity.Terminal;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import static com.electricPower.core.socket.constants.SocketConstant.RETRY_COUNT;


/**
 * 封装socket添加println方法
 */
@Log4j2
@Data
public class Connection {

    /**
     * socket 连接
     */
    private Socket socket;

    /**
     * 数据
     */
    private Queue<byte[]> frame;

    /**
     * 当前连接线程
     */
    private ConnectionThread connectionThread;

    /**
     * 当前终端设备
     */
    private Terminal terminal;

    /**
     *
     */
    private TcpFlow tcpFlow;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一次更新时间，用于判断心跳
     */
    private Date lastOnTime;

    public Connection(Socket socket, ConnectionThread connectionThread) {
        this.connectionThread = connectionThread;
        this.socket = socket;
        this.frame = new LinkedList<>();

        Date now = new Date();
        createTime = now;
        lastOnTime = now;

        this.tcpFlow = new TcpFlow();
        this.terminal = new Terminal();
    }

	public void println() {
		int count = 0;
		byte[] current;
		OutputStream writer;
		do {
			try {
                writer = socket.getOutputStream();
                while (frame.peek()!=null) {
                    current = frame.poll();
                    writer.write(current);
                }
				writer.flush();
				break;
			} catch (IOException e) {
				log.info("client端socket异常：" + e );
				count++;
				if (count >= RETRY_COUNT) {
					//重试多次失败，说明client端socket异常
					this.connectionThread.stopRunning();
				}
			}
			try {
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e1) {
				log.error("Connection.println.IOException interrupt,terminal address:{}", terminal.getTerminalNum());
			}
		} while (count < 3);
	}
}