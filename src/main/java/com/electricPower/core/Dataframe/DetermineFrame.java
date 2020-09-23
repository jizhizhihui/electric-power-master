package com.electricPower.core.Dataframe;

import com.electricPower.common.exception.frame.FrameCheckFailureException;
import com.electricPower.utils.StringUtils;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

/**
 * 校验数据帧
 */
@Log4j2
@Data
public class DetermineFrame extends BasicFrame {

    private String[] frame;

    /**
     * 初始化数据帧，检验数据长度
     *
     * @param frame 数据帧
     */
    public void setDetermineFrame(String[] frame) {
        if (frame.length == Integer.parseInt(frame[1], 16)) {
            this.frame = frame;
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

    /**
     * 校验数据帧
     */
    public boolean checkFrame() {
        if (!getStart().equals("43") || !getEnd().trim().equals("16"))
            return false;
        int sum = 0;
        for (int i = 0; i < frame.length - 2; i++)
            sum += Integer.parseInt(frame[i], 16);
        String c = Integer.toHexString(sum).toUpperCase();
        return c.substring(c.length() - 2).equals(getCheck());
    }

    public static void main(String[] args) {

        String s = "43 0B 01 11 11 11 11 11 11 B5 16";
//                "43 55 01 " + //前校验
//                        "11 11 11 11 11 11 " +    //终端地址
//                        "22 12 22 34 22 56 " +  //电压 ，小数1，非负，2
//                        "00 00 12 34 00 00 56 78 00 01 90 12 00 00 01 23 00 00 00 45 " + //电流，小数3，非负，4
//                        "80 00 78 90 00 01 23 45 80 00 56 78 00 23 45 67 00 00 78 90 80 01 23 45 00 00 56 78 80 23 45 67 " + //有功无功功率,小数3，可负
//                        "10 00 09 98 85 00 " +  //功率因素，小数3，可负，
//                        "80 25 00 65 20 08 06 11 15 00 " +  //温湿度 + 时间戳
//                        "D1 16";//后校验

    }
}
