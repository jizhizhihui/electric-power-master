package com.electricPower;

import com.electricPower.common.config.ThreadPoolConfigurer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 丁许
 * @date 2019-01-23 13:35
 */
@SpringBootApplication
@MapperScan("com.electricPower.project.mapper")
@EnableScheduling
@EnableAsync
public class ElectricPowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectricPowerApplication.class, args);
	}

	//重写父类提供的跨域请求处理的接口e
	public void addCorsMappings(CorsRegistry registry) {
		//添加映射路径
		registry.addMapping("/**")
				//是否发送Cookie信息
				.allowCredentials(true)
				//放行哪些原始域头部信息
				.allowedHeaders("*")
				//放行哪些原始域（如：”http://admin.com”）
				.allowedOrigins("*")
				//放行哪些原始域请求方式( 如：”GET,POST”)
				.allowedMethods("*");

	}
}
