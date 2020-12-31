package com.electricPower.core.Dataframe;

public enum CtrlFrame {

    //本地测量数据帧: 01; 主站发起表示召测本地数据
    LINE("01", "本地测量数据帧"),

    //总表测量数据帧: 02; 主站发起表示召测总表数据
    MASTER("02", "总表采集数据帧"),

    //终端请求终端
    TERMINAL("0f", "终端请求终端"),

    //报警帧: 33
    ALARM("33", "报警帧"),

    //报警数据帧: 34
    ALARMDATA("34", "报警帧数据帧"),

    //心跳帧: 3F
    HEART("3F", "心跳帧"),


    //主站确认应答帧
    ENSURE("3F", "主站确认应答帧"),

    //主站否认应答帧
    DENY("3F", "主站否认应答帧"),

    //召测户表
    CALLLINE("81", "召测户表数据"),

    //召测总表
    CALLMASTER("82", "召测总表数据");


    private String val;
    private String msg;

    CtrlFrame(String val, String msg) {
        this.val = val;
        this.msg = msg;
    }

    public String getVal() {
        return val;
    }

    public String getMsg() {
        return msg;
    }

}
