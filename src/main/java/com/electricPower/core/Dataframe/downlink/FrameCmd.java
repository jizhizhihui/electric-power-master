package com.electricPower.core.Dataframe.downlink;

import com.electricPower.core.Dataframe.BasicFrame;

public class FrameCmd extends BasicFrame {

    public FrameCmd(String ctrl, String terminalAddress){
        setCtrl(ctrl);
        setAddress(terminalAddress);
    }

    public String creatFrameCmd(){
        //产生校验帧
//        setCheck();
        return "43 0B " + getCtrl() + " " + getAddress() + " " +  getCheck() + " 16";
    }
}
