//package com.electricPower.common.config;
//
//import com.electricPower.core.socket.server.SocketServer;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//@Configuration
//@Slf4j
//@PropertySource("classpath:application-task.yml")
//public class SocketServerConfig {
//
//	@Value("${task.socket.server.port}")
//	private int port;
//
//	@Bean
//	public SocketServer socketServer() {
//	    log.info(port + "");
//		SocketServer socketServer = new SocketServer(port);
//		socketServer.start();
//		return socketServer;
//	}
//}
