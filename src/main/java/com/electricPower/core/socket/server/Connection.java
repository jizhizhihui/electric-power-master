package com.electricPower.core.socket.server;

import com.electricPower.project.entity.TcpFlow;
import com.electricPower.project.entity.Terminal;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import static com.electricPower.core.socket.constants.SocketConstant.RETRY_COUNT;


/**
 * 封装socket添加println方法
 */
@Log4j2
@Data
public class Connection {

	private TcpFlow tcpFlow;

	/**
	 * 当前的socket连接实例
	 */
	private Socket socket;

	/**
	 * 当前连接线程
	 */
	private ConnectionThread connectionThread;

	/**
	 * 当前终端设备
	 */
	private Terminal terminal;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 最后一次更新时间，用于判断心跳
	 */
	private Date lastOnTime;

	public Connection(Socket socket, ConnectionThread connectionThread) {
		this.socket = socket;
		this.connectionThread = connectionThread;
		this.tcpFlow = new TcpFlow();
		this.terminal = new Terminal();
	}

	public void println(byte[] message) {
		int count = 0;
		OutputStream writer;
		do {
			try {
				writer = socket.getOutputStream();
				writer.write(message);
				writer.flush();
				break;
			} catch (IOException e) {
				log.info("说明client端socket异常");
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