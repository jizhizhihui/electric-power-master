package com.electricPower.core.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttSenderConfig{

    @Value("${task.mqtt.host}")
    public   String host ;
    @Value("${task.mqtt.username}")
    private  String username;
    @Value("${task.mqtt.password}")
    private  String password ;
    @Value("${task.mqtt.clientId}")
    private String clientId;
    @Value("${task.mqtt.topic}")
    public  String topic;
    @Value("${task.mqtt.keepAlive}")
    private int keepAlive;
    @Value("${task.mqtt.reconnect}")
    private boolean reconnect;
    @Value("${task.mqtt.timeout}")
    private int timeout;

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);   //清除session
        mqttConnectOptions.setConnectionTimeout(timeout); //连接超时
        mqttConnectOptions.setUserName(username);   //登录名
        mqttConnectOptions.setPassword(password.toCharArray()); //密码
        mqttConnectOptions.setServerURIs(new String[]{host});   //服务器地址
        mqttConnectOptions.setKeepAliveInterval(keepAlive); //心跳时间,单位秒
        mqttConnectOptions.setAutomaticReconnect(reconnect); //自动重连
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("SenderClientId", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(topic);
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
}
