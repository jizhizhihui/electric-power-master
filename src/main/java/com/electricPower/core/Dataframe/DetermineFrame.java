package com.electricPower.core.Dataframe;

import com.electricPower.common.exception.frame.FrameCheckFailureException;
import com.electricPower.utils.StringUtils;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

/**
 * 校验数据帧
 */
@Log4j2
public class DetermineFrame extends BasicFrame{

    private String dataFrame;

    public DetermineFrame(String[] frame){
        if (frame.length == Integer.parseInt(frame[1],16)) {
            setStart(frame[0]);
            setLength(frame[1]);
            setCtrl(frame[2]);
            setAddress(StringUtils.subString(frame, 3, 3));
            setCheck(frame[frame.length - 2]);
            setEnd(frame[frame.length - 1]);
        } else {
            log.error("帧数据长度异常：" + frame.length + "; frame : " + Arrays.toString(frame));
            throw new FrameCheckFailureException("数据帧长度异常");
        }
    }

    public void checkFrame(){
        if (getStart() .equals("43")  && getEnd() .equals( "16") ){

            //校验
//            && getCheck().equals(Integer.parseInt(getCheck(), 16) % 256 + "")
            //校验终端
            throw new FrameCheckFailureException("数据帧校验失败");
        }
    }
}
