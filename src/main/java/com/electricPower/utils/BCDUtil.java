package com.electricPower.utils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BCDUtil {

    /**
     * BCD字符串解析
     *
     * @param bcd     BCD字符串
     * @param decimal 小数位数
     * @param sign    首位正负，true（开启），false（关闭）
     * @return float
     */
    public static float stringBCDToFloat(String bcd, int decimal, boolean sign) {

        float num = 0;
        if (decimal != 0)
            num += Float.parseFloat("0." + bcd.substring(bcd.length() - decimal));
        if (sign) {
            num += Float.parseFloat(bcd.substring(1, bcd.length() - decimal));
            if (Integer.parseInt(bcd.substring(0,1)) >= 8) num = -num;
        } else
            num += Float.parseFloat(bcd.substring(0, bcd.length() - decimal));

        log.info(num);
        return num;
    }

    public static byte[] DecToBCDArray(long num) {
        int digits = 0;

        long temp = num;
        while (temp != 0) {
            digits++;
            temp /= 10;
        }

        int byteLen = digits % 2 == 0 ? digits / 2 : (digits + 1) / 2;
        boolean isOdd = digits % 2 != 0;

        byte[] bcd = new byte[byteLen];

        for (int i = 0; i < digits; i++) {
            byte tmp = (byte) (num % 10);

            if (i == digits - 1 && isOdd)
                bcd[i / 2] = tmp;
            else if (i % 2 == 0)
                bcd[i / 2] = tmp;
            else {
                byte foo = (byte) (tmp << 4);
                bcd[i / 2] |= foo;
            }

            num /= 10;
        }

        for (int i = 0; i < byteLen / 2; i++) {
            byte tmp = bcd[i];
            bcd[i] = bcd[byteLen - i - 1];
            bcd[byteLen - i - 1] = tmp;
        }

        return bcd;
    }

    public static String BCDtoString(byte bcd) {
        StringBuilder sb = new StringBuilder();

        byte high = (byte) (bcd & 0xf0);
        high >>>= (byte) 4;
        high = (byte) (high & 0x0f);
        byte low = (byte) (bcd & 0x0f);

        sb.append(high);
        sb.append(low);

        return sb.toString();
    }


    public static void main(String[] args) {
        //A相电压码 ：22 12；电压 221.2
        log.info("num: " + stringBCDToFloat("80007890", 3, true));
    }
}
