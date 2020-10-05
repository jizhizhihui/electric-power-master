package com.electricPower.utils;

import com.electricPower.project.entity.AlarmInfo;
import com.electricPower.project.entity.MeterData;
import lombok.extern.log4j.Log4j2;

import java.text.ParseException;
import java.time.LocalDateTime;

@Log4j2
public class FrameUtils {

    public static MeterData analysisLien(String message, boolean lien) {
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

        int count = 30;
        if (lien) {
            meterData.setCurrentRemain(BCDUtils.stringBCDToFloat(strings[31] + strings[32] + strings[33] + strings[34], 3, false));
            count += 4;
        }

        //有功功率
        meterData.setActivePowerA(BCDUtils.stringBCDToFloat(strings[count + 1] + strings[count + 2] + strings[count + 3] + strings[count + 4], 3, true));
        meterData.setActivePowerB(BCDUtils.stringBCDToFloat(strings[count + 5] + strings[count + 6] + strings[count + 7] + strings[count + 8], 3, true));
        meterData.setActivePowerC(BCDUtils.stringBCDToFloat(strings[count + 9] + strings[count + 10] + strings[count + 11] + strings[count + 12], 3, true));
        meterData.setActivePowerTotal(BCDUtils.stringBCDToFloat(strings[count + 13] + strings[count + 14] + strings[count + 15] + strings[16], 3, true));

        //无功功率
        count += 16;
        meterData.setReactivePowerA(BCDUtils.stringBCDToFloat(strings[count + 1] + strings[count + 2] + strings[count + 3] + strings[count + 4], 3, true));
        meterData.setReactivePowerB(BCDUtils.stringBCDToFloat(strings[count + 5] + strings[count + 6] + strings[count + 7] + strings[count + 8], 3, true));
        meterData.setReactivePowerC(BCDUtils.stringBCDToFloat(strings[count + 9] + strings[count + 10] + strings[count + 11] + strings[count + 12], 3, true));
        meterData.setReactivePowerTotal(BCDUtils.stringBCDToFloat(strings[count + 13] + strings[count + 14] + strings[count + 15] + strings[count + 16], 3, true));

        //功率因素
        count += 16;
        meterData.setPowerFactorA(BCDUtils.stringBCDToFloat(strings[count + 1] + strings[count + 2], 3, true));
        meterData.setPowerFactorB(BCDUtils.stringBCDToFloat(strings[count + 3] + strings[count + 4], 3, true));
        meterData.setPowerFactorC(BCDUtils.stringBCDToFloat(strings[count + 5] + strings[count + 6], 3, true));


        if (lien) {
            //温度
            meterData.setTemperature((int) BCDUtils.stringBCDToFloat(strings[count + 7] + strings[count + 8], 0, true));
            //湿度
            if (strings[count +9].equals("FF") && strings[count +10].equals("FF"))
                log.info("该字段无效");
            else
                meterData.setHumidity((int) BCDUtils.stringBCDToFloat(strings[count +9] + strings[count +10], 0, true));
            count += 4;
        }

        //运行状态字
        if (!lien){
            count += 6;
            meterData.setPhaseFaultA(strings[count+1]);
            meterData.setPhaseFaultB(strings[count+2]);
            meterData.setPhaseFaultC(strings[count+3]);
            meterData.setCombinedPhaseFault(strings[count+4]);
        }

        //时间
        meterData.setSaveTime(LocalDateTime.now());
        try {
            int  l = strings.length;
            meterData.setAcquisitionTime(DateTimeUtils.dateToLocalDateTime(BCDUtils.stringBCDToDate(strings[l-8], strings[l-7], strings[l-6], strings[l-5], strings[l-4], strings[l-3])));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return meterData;
    }

    public static AlarmInfo analysisAlarm(String message){

        AlarmInfo alarmInfo = new AlarmInfo();
        String[] strings = message.split(" ");

        alarmInfo.setAlarmSign(strings[9] + " " + strings[10]);
        alarmInfo.setAlarmCount(Integer.parseInt(strings[11]));
        if (alarmInfo.getAlarmCount() > 0)
            alarmInfo.setAlarmInfo(StringUtils.subString(strings,12,alarmInfo.getAlarmCount() * 8));
        alarmInfo.setCreateTime(LocalDateTime.now());

        return alarmInfo;
    }

    public static String anylysisAlarmSign(String sign){

        String[] strings = sign.split(" ");
        log.info(Integer.parseInt(strings[0],2) + "::: " +  Integer.parseInt(strings[1],2));
        return "";
    }

    public static String creatCheck(String message) {
        String[] s = message.split(" ");//split() 方法根据匹配给定的正则表达式来拆分字符串。
        //产生校验帧
        int sum = 0;
        for (int i = 0; i < s.length - 2; i++) {
            if (!s[i].equals("null"))
                sum += Integer.parseInt(s[i], 16);
        }
        String c = Integer.toHexString(sum).toUpperCase();
        return c.substring(c.length() - 2);
    }

    public static String creatFrame(String frame){
        String[] strings = frame.split(" ");
        StringBuilder nowFrame = new StringBuilder();
        for (String s: strings) {
            nowFrame.append((byte)Integer.parseInt(s, 16));
        }
        return nowFrame.toString();
    }

    public static void main(String[] args) {
        String message = "43 11 3F 11 11 11 11 11 11 20 09 08 10 11 12 5D 16";

        log.info(creatFrame(message));
    }

}
