package com.electricPower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ElectricPowerApplication {

	public static void main(String[] args) {
		//spring应用启动
		SpringApplication.run(ElectricPowerApplication.class, args);
	}
}
