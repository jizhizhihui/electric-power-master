package com.electricPower.core.mqtt.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttPublishController {

    @Autowired
    MqttPublish publish;

    /**
     * 发布消息
     * @param message 消息
     * @param topic 主题
     * @return String
     */
    @GetMapping("/sendMqttMessage")
    public String sendMqttMessage(String message, String topic) {
        publish.sendToMqtt(message, topic);
        return "ok";
    }
}
