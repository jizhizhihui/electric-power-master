package com.electricPower.utils;

import com.electricPower.project.entity.MeterData;
import lombok.extern.log4j.Log4j2;

import java.text.ParseException;

@Log4j2
public class FrameUtils {

    public static MeterData analysisLien(String message) {
        MeterData meterData = new MeterData();
        String[] strings = message.split(" ");

        //电压
        meterData.setVoltageA(BCDUtils.stringBCDToFloat(strings[9] + strings[10], 1, false));
        meterData.setVoltageB(BCDUtils.stringBCDToFloat(strings[11] + strings[12], 1, false));
        meterData.setVoltageC(BCDUtils.stringBCDToFloat(strings[13] + strings[14], 1, false));

        //电流
        meterData.setCurrentA(BCDUtils.stringBCDToFloat(strings[15] + strings[16] + strings[17] + strings[18], 3, false));
        meterData.setCurrentB(BCDUtils.stringBCDToFloat(strings[19] + strings[20] + strings[21] + strings[22], 3, false));
        meterData.setCurrentC(BCDUtils.stringBCDToFloat(strings[23] + strings[24] + strings[25] + strings[26], 3, false));
        meterData.setCurrentZeroLine(BCDUtils.stringBCDToFloat(strings[27] + strings[28] + strings[29] + strings[30], 3, false));
        meterData.setCurrentRemain(BCDUtils.stringBCDToFloat(strings[31] + strings[32] + strings[33] + strings[34], 3, false));

        //有功功率
        meterData.setActivePowerA(BCDUtils.stringBCDToFloat(strings[35] + strings[36] + strings[37] + strings[38], 3, true));
        meterData.setActivePowerB(BCDUtils.stringBCDToFloat(strings[39] + strings[40] + strings[41] + strings[42], 3, true));
        meterData.setActivePowerC(BCDUtils.stringBCDToFloat(strings[43] + strings[44] + strings[45] + strings[46], 3, true));
        meterData.setActivePowerTotal(BCDUtils.stringBCDToFloat(strings[47] + strings[48] + strings[49] + strings[50], 3, true));

        //无功功率
        meterData.setReactivePowerA(BCDUtils.stringBCDToFloat(strings[51] + strings[52] + strings[53] + strings[54], 3, true));
        meterData.setReactivePowerB(BCDUtils.stringBCDToFloat(strings[55] + strings[56] + strings[57] + strings[58], 3, true));
        meterData.setReactivePowerC(BCDUtils.stringBCDToFloat(strings[59] + strings[60] + strings[61] + strings[62], 3, true));
        meterData.setReactivePowerTotal(BCDUtils.stringBCDToFloat(strings[63] + strings[64] + strings[65] + strings[66], 3, true));

        //功率因素
        meterData.setPowerFactorA(BCDUtils.stringBCDToFloat(strings[67] + strings[68], 3, true));
        meterData.setPowerFactorB(BCDUtils.stringBCDToFloat(strings[69] + strings[70], 3, true));
        meterData.setPowerFactorC(BCDUtils.stringBCDToFloat(strings[71] + strings[72], 3, true));

        //温度
        meterData.setTemperature((int)BCDUtils.stringBCDToFloat(strings[73] + strings[74], 0, true));

        //湿度
        if (strings[75].equals("FF") && strings[76].equals("FF"))
            log.info("该字段无效");
        else
            meterData.setHumidity((int)BCDUtils.stringBCDToFloat(strings[75] + strings[76], 0, true));

        //时间
        meterData.setSaveTime();
        try {
            meterData.setAcquisitionTime(DateTimeUtils.dateToLocalDateTime(BCDUtils.stringBCDToDate(strings[77], strings[78], strings[79], strings[80], strings[81], strings[82])));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return meterData;
    }

    public static String creatCheck(String message){
        String[] s = message.split(" ");
        //产生校验帧
        int sum = 0;
        for (int i = 0; i < s.length-2; i++) {
            if (!s[i].equals("null"))
                sum += Integer.parseInt(s[i], 16);
        }
        String c = Integer.toHexString(sum).toUpperCase();
        return c.substring(c.length()-2);
    }

    public static void main(String[] args) {
        String message =
                "43 55 01 " + //前校验
                        "11 11 11 11 11 11 " +    //终端地址
                        "22 12 22 34 22 56 " +  //电压 ，小数1，非负，2
                        "00 00 12 34 00 00 56 78 00 01 90 12 00 00 01 23 00 00 00 45 " + //电流，小数3，非负，4
                        "80 00 78 90 00 01 23 45 80 00 56 78 00 23 45 67 00 00 78 90 80 01 23 45 00 00 56 78 80 23 45 67 " + //有功无功功率,小数3，可负
                        "10 00 09 98 85 00 " +  //功率因素，小数3，可负，
                        "80 25 00 65 20 08 06 11 15 00 " +  //温湿度 + 时间戳
                        "D1 16";//后校验

        MeterData meterData = analysisLien(message);
        meterData.setMeterSn("test");
        log.info(meterData.toString());
    }

}
