package com.electricPower.utils;

import lombok.extern.log4j.Log4j2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@Log4j2
public class StringUtils {

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
            if ((message = reader.readLine()) != null)
                break;
        }
        log.info(message);
        return message.split(" ");
    }

    /**
     * String[] to String
     *
     * @param strings String[]
     * @param start   start address
     * @param end   end address
     * @return String
     */
    public static String subString(String[] strings, int start, int end) {
        StringBuilder s = new StringBuilder();
        for (; start < strings.length && start <= end; start++)
            s.append(strings[start]).append(" ");
        return s.toString().trim();
    }


    public static String addressSplit(String address){
        char[] add = address.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < add.length; i+=2) {
            stringBuilder.append(add[i]).append(add[i+1]).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        log.info(addressSplit("111111111111"));
    }
}
