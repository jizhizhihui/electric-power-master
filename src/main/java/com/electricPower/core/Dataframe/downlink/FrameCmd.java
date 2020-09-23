package com.electricPower.core.Dataframe.downlink;

import com.electricPower.core.Dataframe.BasicFrame;
import com.electricPower.utils.FrameUtils;

/**
 * 召测帧
 */
public class FrameCmd extends BasicFrame {

    public FrameCmd(String ctrl, String terminalAddress){
        setStart("43");
        setEnd("16");

        setLength("0B");
        setCtrl(ctrl);
        setAddress(terminalAddress);
        setCheck(FrameUtils.creatCheck(toString()));
    }

    @Override
    public String toString(){
        return getStart() + " " + getLength() + " " + getCtrl() + " " + getAddress()  + " " +  getCheck() + " " + getEnd();
    }
}
