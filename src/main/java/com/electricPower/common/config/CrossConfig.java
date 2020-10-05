package com.electricPower.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
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