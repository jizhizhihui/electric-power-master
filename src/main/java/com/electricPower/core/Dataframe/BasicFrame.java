package com.electricPower.core.Dataframe;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasicFrame implements Serializable {

    //开始字符
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
    private String end;
}
