package com.electricPower.utils;

import lombok.extern.log4j.Log4j2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

@Log4j2
public class BCDUtils {

    /**
     * BCD字符串解析
     *
     * @param bcd     BCD字符串
     * @param decimal 小数位数
     * @param sign    首位正负，true（开启），false（关闭）
     * @return float
     */
    public static float stringBCDToFloat(String bcd, int decimal, boolean sign) {

        if (bcd.substring(0,2).equals("ff") || bcd.substring(0,2).equals("FF"))
            return Float.NaN;

        float num = 0;
        if (decimal != 0) {
            num += Float.parseFloat("0." + bcd.substring(bcd.length() - decimal));
        }
        if (sign) {
            if (bcd.length() - decimal != 1)
                num += Float.parseFloat(bcd.substring(1, bcd.length() - decimal));
            int first = Integer.parseInt(bcd.substring(0, 1), 16);
            if (first >= 8) {
                num += first - 8;
                num = -num;
            } else {
                num += first;
            }
        } else
            num += Float.parseFloat(bcd.substring(0, bcd.length() - decimal));
        return num;
    }

    /**
     * BCD字符串解析
     *
     * @param times BCD字符串
     * @return data
     */
    public static LocalDateTime stringBCDToLocalDataTime(String... times) throws ParseException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String time =  df.format(localDateTime).substring(0,2) + times[0] + "-" + times[1] + "-" + times[2] + " " + times[3] + ":" + times[4] + ":" + times[5];
        return  LocalDateTime.parse(time, df);
    }


    public static void main(String[] args) {
        //A相电压码 ：22 12；电压 221.2
//        if (stringBCDToFloat("848512", 3, true) != Float.NaN) {
//            log.info("num: " + stringBCDToFloat("848512", 3, true));
//        }

        //字符串转时间戳
//        try {
//            log.error(stringBCDToLocalDataTime("20", "08", "06", "11", "15", "00"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        System.getProperty("user.home");
    }
}
