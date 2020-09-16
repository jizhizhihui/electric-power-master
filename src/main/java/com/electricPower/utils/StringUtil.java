package com.electricPower.utils;

import lombok.extern.log4j.Log4j2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Log4j2
public class StringUtil {

    /**
     * InputStream转化为String[]数组
     *
     * @param input InputStream
     * @return byte[]
     * @throws IOException
     */
    public static String[] toStringArray(InputStream input) throws IOException {
        String message;
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            if( (message = reader.readLine()) != null)
                break;
        }
        log.info(message);
        return message.split(" ");
    }

    /**
     * String[] to String
     * @param strings String[]
     * @param start start address
     * @param count count
     * @return String
     */
    public static String subString(String[] strings, int start, int count) {
        StringBuilder s = new StringBuilder();
        for (; start < strings.length && start <= count ;start ++ )
            s.append(strings[start]);
        log.info(s.toString());
        return s.toString();
    }



    /**
     * 16进制字符串转byte
     *
     * @param hex String
     * @return short
     */
    public static int hexToInt(String hex) {
        return Integer.parseInt(hex, 16);
    }


    public static void main(String[] args) {
        String hexStr = "43 1 11 11 11 11 11 11 22 12 22 34 22 56 00 00 12 34 00 00 56 78 00 01 90 12 00 00 01 23 00 00 00 45 80 00 78 90 00 01 23 45 80 00 56 78 00 23 45 67 00 00 78 90 80 01 23 45 00 00 56 78 80 23 45 67 10 00 09 98 85 00 80 25 00 65 20 08 06 11 15 00 16";
        int max = 0;

        for (String s : hexStr.split(" ")){
            max += Integer.parseInt(s);
        }

        max += StringUtil.hexToInt("D1") + StringUtil.hexToInt("55");

        log.info(StringUtil.hexToInt("D1"));
        log.info("和：" + max);
        log.info("取模：" + max % 256);
    }
}
