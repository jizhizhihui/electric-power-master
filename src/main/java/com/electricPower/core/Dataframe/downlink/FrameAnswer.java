package com.electricPower.core.Dataframe.downlink;

import com.electricPower.core.Dataframe.BasicFrame;

public class FrameAnswer extends BasicFrame{

    //应答标识
    private String ansFlag;

    public FrameAnswer(String ctrl, String terminalAddress,String ansFlag){
        setCtrl(ctrl);
        setAddress(terminalAddress);
        this.ansFlag = ansFlag;
    }

    public String creatFrameAnswer(){
        //产生校验帧
//        setCheck();
        return "43 12 " + getCtrl() + " " + getAddress() + " " + ansFlag + " " +  getCheck() + " 16";
    }

}
