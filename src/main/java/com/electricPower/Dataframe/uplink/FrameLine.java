package com.electricPower.Dataframe.uplink;

import lombok.Data;

import java.util.List;

@Data
public class FrameLine {

    //电压
    private List<Float> voltage;

    //电流
    private List<Float> current;

    //零线电流
    private float currentzeroline;

    //剩余电流
    private float currentRemain;

    //有功功率
    private List<Float> activePower;

    //总有功功率
    private float activePowerTotal;

    //无功功率
    private List<Float> reactivePower;

    //总无功功率
    private float reactivePowerTotal;

    //功率因素
    private List<Float> powerFactor;

    //变压器温度
    private float transformerTemperature;

    //湿度
    private float humidity;

    //时间戳
    private float collecttedTime;

    public FrameLine() {
    }
}
