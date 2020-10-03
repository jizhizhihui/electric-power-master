package com.electricPower.core.Dataframe.alarm;

public enum CombinedPhaseFault {

    // 电压逆相序
    VoltagePhaseReversed("电压逆相序"),
    // 电流逆相序
    CurrentPhaseReversed("电流逆相序"),
    // 电压不平衡
    VoltageUnbalance("电压不平衡"),
    // 电流不平衡
    CurrentUnbalance("电流不平衡"),
    // 辅助电源失电
    UpsPowerOff("辅助电源失电"),
    // 掉电
    PowerOff("掉电"),
    // 需量超限
    CapacityHi("需量超限"),
    // 总功率因素超下限
    PowerFactorLow("总功率因素超下限"),
    // 电流严重不平衡
    CurrentUnbalanceBad("电流严重不平衡"),
    // 保留
    Reserve("保留");

    String msg;

    CombinedPhaseFault(String msg) {
        this.msg = msg;
    }
}
