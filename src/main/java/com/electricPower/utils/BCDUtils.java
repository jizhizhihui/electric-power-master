package com.electricPower.utils;

import lombok.extern.log4j.Log4j2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        if (bcd.equals("ff"))
            return Float.parseFloat(null);

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
    public static Date stringBCDToDate(String... times) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/mm/dd-hh:mm:ss");//注意月份是MM
        Date date = simpleDateFormat.parse(
                times[0] + "/" + times[1] + "/" + times[2] + "-"
                        + times[3] + ":" + times[4] + ":" + times[5]);
        return date;
    }


    public static void main(String[] args) {
        //A相电压码 ：22 12；电压 221.2
//        log.info("num: " + stringBCDToFloat("80007890", 3, true));

        try {
            stringBCDToDate("20", "08", "06", "11", "15", "00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
