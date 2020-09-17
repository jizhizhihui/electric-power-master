package com.electricPower.utils;

import lombok.extern.log4j.Log4j2;

import java.text.ParseException;

@Log4j2
public class FrameUtils {

    public static void analysisLien(String message) {
//        String[] strings = message.split(" ");
//        log.info("长度：" + strings.length);
//        int count = 9;
//        log.info("电压");
//        for (int i = 0; i < 6; i += 2) {
//            BCDUtils.stringBCDToFloat(strings[count + i] + strings[count + i + 1], 1, false);
//        }
//
//        count += 6;
//        log.info("电流");
//        for (int i = 0; i < 20; i += 4) {
//            BCDUtils.stringBCDToFloat(strings[count + i] + strings[count + i + 1] + strings[count + i + 2] + strings[count + i + 3], 3, false);
//        }
//
//        count += 20;
//        log.info("有功无功功率");
//        for (int i = 0; i < 32; i += 4) {
//            BCDUtils.stringBCDToFloat(strings[count + i] + strings[count + i + 1] + strings[count + i + 2] + strings[count + i + 3], 3, true);
//        }
//
//        count += 32;
//        log.info("功率因素");
//        for (int i = 0; i < 6; i += 2) {
//            BCDUtils.stringBCDToFloat(strings[count + i] + strings[count + i + 1], 3, true);
//        }
//
//        count += 6;
//        log.info("温度");
//        BCDUtils.stringBCDToFloat(strings[count] + strings[count + 1], 0, true);
//
//        count += 2;
//        log.info("湿度");
//        if (strings[count].equals("FF") && strings[count + 1].equals("FF"))
//            log.info("该字段无效");
//        else
//            BCDUtils.stringBCDToFloat(strings[count] + strings[count + 1], 0, true);
//
//        count += 2;
//        log.info("时间");
//        try {
//            BCDUtils.stringBCDToDate(strings[count], strings[count + 1], strings[count + 2] ,strings[count + 3],strings[count + 4] ,strings[count + 5]);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


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

        analysisLien(message);
    }
}
