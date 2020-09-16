package com.electricPower.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author J.y
 */
@Data
@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "pool")
public class ThreadPoolConfigurer {

	private int corePoolSize;

	private int keepAliveSeconds;

	private int maxPoolSize;

	private String threadNamePrefix;

	@Bean(name = "clientTaskPool")
	public ThreadPoolTaskExecutor clientTaskPool() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setKeepAliveSeconds(keepAliveSeconds);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setThreadNamePrefix(threadNamePrefix);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();

//		System.out.println(corePoolSize + " :: "  + keepAliveSeconds + " :: "  + maxPoolSize + " :: "  + threadNamePrefix);
		return executor;
	}

	@Bean(name = "clientMessageTaskPool")
	public ThreadPoolTaskExecutor clientMessageTaskPool() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setKeepAliveSeconds(60);
		executor.setMaxPoolSize(Integer.MAX_VALUE);
		executor.setThreadNamePrefix("clientMessageTaskPool");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}
}
