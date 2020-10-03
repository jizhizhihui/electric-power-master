package com.electricPower.core.Dataframe.alarm;


public enum PhaseFaultA {

    // 失压
    VOLTAGELOSE("失压"),

    // 欠压
    VOLTAGEUNDER("欠压"),

    // 过压
    VOLTAGEOVERLOAD("过压"),

    // 失流
    CURRENTLOSE("失流"),

    // 过流
    CURRENTOVERLOAD("过流"),

    // 过载
    OVERLOAD("过载"),

    // 潮流反向
    CURRENTREVERSED("潮流反向"),

    // 断相
    PHASEBROKEN("断相"),

    // 断流
    CURRENTBROKEN("断流"),

    // 保留
    RESERVE("保留");

    private String msg;

    PhaseFaultA(String msg) {
        this.msg = msg;
    }
}
