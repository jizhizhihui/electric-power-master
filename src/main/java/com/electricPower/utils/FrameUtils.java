package com.electricPower.utils;

import com.alibaba.fastjson.JSONObject;
import com.electricPower.project.entity.AlarmInfo;
import com.electricPower.project.entity.MeterData;
import lombok.extern.log4j.Log4j2;

import java.text.ParseException;
import java.time.LocalDateTime;
@Log4j2
public class FrameUtils {

    /**
     * 解析 本地数据帧 或 总表数据帧
     * @param message 数据帧
     * @param lien true： 本地数据； false：总表数据
     * @return MeterData
     */
    public static MeterData analysisLien(String message, boolean lien) {
        MeterData meterData = new MeterData();
        String[] strings = message.split(" ");

        //时间
        meterData.setSaveTime(LocalDateTime.now());
        meterData.setAcquisitionTime(getFrameDateTime(strings));

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
            if (strings[count + 9].equals("FF") && strings[count + 10].equals("FF"))
                log.info("该字段无效");
            else
                meterData.setHumidity((int) BCDUtils.stringBCDToFloat(strings[count + 9] + strings[count + 10], 0, true));
            count += 4;
        }

        //运行状态字
        if (!lien) {
            count += 6;
            meterData.setPhaseFaultA(strings[count + 1]);
            meterData.setPhaseFaultB(strings[count + 2]);
            meterData.setPhaseFaultC(strings[count + 3]);
            meterData.setCombinedPhaseFault(strings[count + 4]);
        }

        return meterData;
    }

    /**
     * 解析 报警数据帧
     * @param message 数据帧
     * @return AlarmInfo
     */
    public static AlarmInfo analysisAlarm(String message) {

        AlarmInfo alarmInfo = new AlarmInfo();
        String[] strings = message.split(" ");

        // 时间戳
        alarmInfo.setAlarmTime(getFrameDateTime(strings));
        // 报警标识
        alarmInfo.setAlarmSign(strings[9] + " " + strings[10]);
        // 报警数
        alarmInfo.setAlarmCount(Integer.parseInt(strings[11]));
        // 户表报警
        if (alarmInfo.getAlarmCount() > 0)
            alarmInfo.setAlarmInfo(analysisHouseholdAlarm(alarmInfo.getAlarmCount(), StringUtils.subString(strings, 12, alarmInfo.getAlarmCount() * 8)));

        return alarmInfo;
    }

    public static void analysisAlarmSign(String sign) {
        String[] alarmSign = {
                "保留", "线缆温度超限", "剩余电流超限", "零线电流异常", "开表盖开端钮盖", "功率因素超下限", "电流逆相序", "电压逆相序",
                "断流", "断相", "过载", "过流", "失流", "过压", "欠压", "失压"
        };

        String[] strings = {sign.substring(0, 2), sign.substring(2)};

        int[] n = {128, 64, 32, 16, 8, 4, 2, 1};
        for (int i = 0, count = 0; i < 2; i++, count += 8) {
            int num = Integer.parseInt(strings[i], 16);
            for (int j = 0; j < n.length; j++) {
                if ((num & n[j]) != 0) {
                    log.info(alarmSign[count + j]);
                }
            }
        }
//        return "";
    }

    /**
     * 创建校验字节
     * @param message 数据帧
     * @return String
     */
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

    /**
     * 获取数据帧时间戳
     * @param strings 数据帧
     * @return LocalDateTime
     */
    public static LocalDateTime getFrameDateTime(String[] strings) {
        try {
            int l = strings.length;
            LocalDateTime localDateTime = BCDUtils.stringBCDToLocalDataTime(strings[l - 8], strings[l - 7], strings[l - 6], strings[l - 5], strings[l - 4], strings[l - 3]);
            log.info("时间戳打印：" + localDateTime);
            if (localDateTime.toString().equals("0000-00-00T00:00:00"))
                return null;
            return localDateTime;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取户表的报警信息
     * @param num 报警数
     * @param message 数据帧
     * @return JSONObject
     */
    public static JSONObject analysisHouseholdAlarm(int num, String message) {
        JSONObject jsonObject = new JSONObject();

        String[] msg = message.split(" ");
        for (int i = 0, count = 0; i < num && count + 8 < msg.length; i++, count += 8) {
            jsonObject.put(StringUtils.subString(msg, count, count + 5), msg[count + 7] + msg[count + 8]);
        }
        return jsonObject;
    }

    /**
     * 终端地址逆序
     * @param frame 数据帧
     * @return String 终端地址
     */
    public static String reverseAddress(String frame){
        return reverseAddress(frame.split(" "));
    }

    public static String reverseAddress(String[] frame){
        if (frame.length > 8)
            return frame[8] + " " + frame[7] +  " " + frame[6] +  " " + frame[5] +  " " + frame[4] + " " +  frame[3];
        return null;
    }

    public static void main(String[] args) {
        log.info(analysisAlarm("11 11 11 11 11 12 12 3A 11 11 11 11 11 11 12 3A 11 11 11 11 11 31 12 3A 11"  ));// 20 08 06 11 15 00
//        analysisAlarmSign("123A");

//        log.error(creatCheck("43 55 01 11 11 11 11 11 11 FF FF 22 34 22 56 00 00 12 34 00 00 56 78 00 01 90 12 00 00 01 23 00 00 00 45 80 00 78 90 00 01 23 45 80 00 56 78 00 23 45 67 00 00 78 90 80 01 23 45 00 00 56 78 80 23 45 67 10 00 09 98 85 00 80 25 00 65 20 08 06 11 15 00 D2 16"));

//        try {
//            log.error(BCDUtils.stringBCDToLocalDataTime("20","08","06","11","15","00"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//            log.error(LocalDateTime.parse(BCDUtils.stringBCDToDate("20","08","06","11","15","00",df)));


    }
}
