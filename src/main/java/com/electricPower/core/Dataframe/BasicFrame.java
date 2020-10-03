package com.electricPower.core.Dataframe;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@PropertySource(value = "classpath:application-task.yml")
public class BasicFrame implements Serializable {

    //开始字符
    @Value("${task.protocol.start}")
    private String start;

    //长度字符
    private String length;

    //控制字符
    private String ctrl;

    //终端地址字符
    private String address;

    //校验字符
    private String check;

    //结束字符
    @Value("${task.protocol.end}")
    private String end;

    @Override
    public String toString(){ return start + " " + length + " " + ctrl + " " + address + " " + check + " " + end;
    }

    public static void main(String[] args) {
        System.out.println("tostring: " + new BasicFrame().toString());
    }
}
