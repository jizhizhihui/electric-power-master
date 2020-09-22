package com.electricPower.core.socket.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

@Slf4j
@Data
public class SocketClient {

	private Socket socket;

	private Date lastOnTime;

	public SocketClient(InetAddress ip, int port) {
		try {
			socket = new Socket(ip, port);
			socket.setKeepAlive(true);	//测试是否启用了
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}

	//暂时没有用到
	public void println(String message) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			writer.println(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}

	/**
	 * This function blocks.
	 *
	 * @return
	 */
	public String readLine() throws Exception {
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		return reader.readLine();
	}

	/**
	 * Ready for use.
	 */
	public void close() {
		try {
			// Send a message to tell the server to close the connection.
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}
}