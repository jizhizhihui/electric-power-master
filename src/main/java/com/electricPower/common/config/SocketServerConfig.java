package com.electricPower.common.config;

import com.electricPower.core.socket.server.SocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 丁许
 * @date 2019-01-24 22:24
 */
@Configuration
@Slf4j
//@PropertySource("classpath:application-task.yml")
public class SocketServerConfig {

	@Value("${socket.port}")
	private int port;

	@Bean
	public SocketServer socketServer() {
		SocketServer socketServer = new SocketServer(port);
		socketServer.start();
		return socketServer;
	}
}
