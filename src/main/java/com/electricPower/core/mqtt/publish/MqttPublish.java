package com.electricPower.core.mqtt.publish;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttPublish {

    /**
     * 发布消息，到默认Topic（defaultTopic）
     *
     * @param data 数据
     */
    void sendToMqtt(String data);

    /**
     * 发布消息（QoS 默认为0）
     * @param data 数据
     * @param topic 主题
     */
    void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);


    /**
     * 发布消息
     * @param topic   主题
     * @param qos 消息处理机制
     *      1： “至多一次”，消息发布完全依赖底层 TCP/IP 网络。会发生消息丢失或重复。这一级别可用于如下情况，环境传感器数据，丢失一次读记录无所谓，因为不久后还会有第二次发送。
     *      2： “至少一次”，确保消息到达，但消息重复可能会发生。
     *      3： “只有一次”，确保消息到达一次。这一级别可用于如下情况，在计费系统中，消息重复或丢失会导致不正确的结果，资源开销大
     * @param data 数据
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String data);
}
