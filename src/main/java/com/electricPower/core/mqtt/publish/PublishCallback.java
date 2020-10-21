package com.electricPower.core.mqtt.publish;//package com.JZhi.mqtt.publish;
//
//import lombok.extern.log4j.Log4j2;
//import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
//import org.eclipse.paho.client.mqttv3.MqttCallback;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//
///**
// * mqtt回调（客户端消息推送及重连）
// */
//@Log4j2
//public class PublishCallback implements MqttCallback {
//    @Override
//    public void connectionLost(Throwable throwable) {
//        log.warn("mqtt connection lost...");
//    }
//
//    @Override
//    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
//        log.debug("####gps : " + new String(mqttMessage.getPayload()));
//    }
//
//    @Override
//    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
//        log.info("mqtt deliveryComplete...");
//    }
//}
