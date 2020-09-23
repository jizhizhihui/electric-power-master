package com.electricPower.core.Dataframe.downlink;

import com.electricPower.core.Dataframe.BasicFrame;
import com.electricPower.utils.DateTimeUtils;
import com.electricPower.utils.FrameUtils;
import lombok.Data;

/**
 * 应答帧
 */
@Data
public class FrameAnswer extends BasicFrame{

    private String ansFlag;

    private String time;

    public FrameAnswer(){
        setStart("43");
        setLength("12");
        setEnd("16");
    }

    public void creatCheck(String ctrl,String terminalAddress, String ansFlag){
        setCtrl(ctrl);
        this.ansFlag = ansFlag;
//        this.time = "20 08 06 11 15 00"; //测试日期
        this.time = DateTimeUtils.getStringTime();
        setCheck(FrameUtils.creatCheck(toString()));
    }

    @Override
    public String toString(){
        return getStart() + " " + getLength() + " " + getCtrl() + " " + getAddress() + " " + getAnsFlag() + " " +  getTime() + " " + getCheck() + " " + getEnd();
    }

    public static void main(String[] args) {

        FrameAnswer frameAnswer = new FrameAnswer();

        frameAnswer.creatCheck("80","11 11 11 11 11 11","00");
        System.out.println(frameAnswer.toString());
    }
}

