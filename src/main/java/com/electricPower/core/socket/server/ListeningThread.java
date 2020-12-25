package com.electricPower.core.socket.server;

import com.electricPower.core.socket.constants.SocketConstant;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 监听
 */
@Log4j2
class ListeningThread extends Thread {
	private SocketServer socketServer;

	private ServerSocket serverSocket;

	private boolean isRunning;

	public ListeningThread(SocketServer socketServer) {
		this.socketServer = socketServer;
		this.serverSocket = socketServer.getServerSocket();
		isRunning = true;
		log.info("socket服务端开始监听");
	}

	@Override
	public void run() {
		while (isRunning) {
			if (serverSocket.isClosed()) {
				isRunning = false;
				break;
			}
			try {
				Socket socket;
				socket = serverSocket.accept();
				if (socketServer.getExistConnectionThreadList().size() > SocketConstant.MAX_SOCKET_THREAD_NUM) {
					//超过线程数量
					PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
					writer.println("已超过连接最大数限制，请稍后再试");
					socket.close();
				}
				//连接登录
				log.info("socket连接,ip:{}", socket.getInetAddress().toString());

				ConnectionThread connectionThread = new ConnectionThread(socket, socketServer);
				socketServer.getExistConnectionThreadList().add(connectionThread);
				//todo:这边最好用线程池
				connectionThread.start();
			} catch (IOException e) {
				log.error("ListeningThread.run failed,exception:{}", e.getMessage());
			}
		}
	}

	/**
	 * 关闭所有的socket客户端连接的线程
	 */
	public void stopRunning() {
		for (ConnectionThread currentThread : socketServer.getExistConnectionThreadList()) {
			currentThread.stopRunning();
		}
		isRunning = false;
	}
}