package com.electricPower.utils;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;


@Log4j2
public class HexUtils {

    /**
     * 用于建立十六进制字符的输出的小写字符数组
     */
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 用于建立十六进制字符的输出的大写字符数组
     */
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data byte[]
     * @return 十六进制char[]
     */
    public static char[] encodeHex(byte[] data) {
        return encodeHex(data, false);
    }

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data        byte[]
     * @param toLowerCase <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
     * @return 十六进制char[]
     */
    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    /**
     * 将字节数组转换为十六进制字符数组
     *
     * @param data     byte[]
     * @param toDigits 用于控制输出的char[]
     * @return 十六进制char[]
     */
    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }

    public static String byteToHex(byte b) {
        char[] out = new char[2];
        out[0] = DIGITS_UPPER[(0xF0 & b) >>> 4];
        out[1] = DIGITS_UPPER[0x0F & b];
        return new String(out);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data byte[]
     * @return 十六进制String
     */
    public static String encodeHexStr(byte[] data) {
        return encodeHexStr(data, true);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data        byte[]
     * @param toLowerCase <code>true</code> 传换成小写格式 ， <code>false</code> 传换成大写格式
     * @return 十六进制String
     */
    public static String encodeHexStr(byte[] data, boolean toLowerCase) {
        return encodeHexStr(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data     byte[]
     * @param toDigits 用于控制输出的char[]
     * @return 十六进制String
     */
    protected static String encodeHexStr(byte[] data, char[] toDigits) {
        return new String(encodeHex(data, toDigits));
    }

    /**
     * 将十六进制字符数组转换为字节数组
     *
     * @param data 十六进制char[]
     * @return byte[]
     * @throws RuntimeException 如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
     */
    public static byte[] decodeHex(char[] data) {

        int len = data.length;

        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }

        byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    /**
     * 将十六进制字符转换成一个整数
     *
     * @param ch    十六进制char
     * @param index 十六进制字符在字符数组中的位置
     * @return 一个整数
     * @throws RuntimeException 当ch不是一个合法的十六进制字符时，抛出运行时异常
     */
    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch
                    + " at index " + index);
        }
        return digit;
    }


//    /*
//     * 十六进制转byte[]数组
//     */
//    public static String hexStrTobytes(String hexStr) {
//
//        if (hexStr == null || hexStr.equals("")) {
//            return null;
//        }
//
//        hexStr = hexStr.replace(" ", "");
//        if (hexStr.length() % 2 != 0) {//长度为单数
//            hexStr = "0" + hexStr;//前面补0
//        }
//        char[] chars = hexStr.toCharArray();
//        int len = chars.length / 2;
//        byte[] bytes = new byte[len];
//        for (int i = 0; i < len; i++) {
//            int x = i * 2;
//            bytes[i] = (byte) Integer.parseInt(String.valueOf(new char[]{chars[x], chars[x + 1]}), 16);
//        }
//        return new String(bytes);
//    }

    /*
     * 十六进制转十进制字符
     */
    public static byte[] hexToBytes(String frame) {
        if (frame == null || frame.equals(""))
            return null;

        String[] hexStr = frame.split(" ");

        byte[] bytes = new byte[Integer.parseInt(hexStr[1], 16)];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)Integer.parseInt( hexStr[i], 16);
        }
        return bytes;
    }

    public static void main(String[] args) {
        String srcStr = "43 12 80 22 22 22 22 22 22 00 20 10 05 13 25 39 47 16";

//            String decodeStr = new String(decodeHex(encodeStr.toCharArray()));


        log.error(Arrays.toString("地".toCharArray()));

//        hexToDecString(srcStr);

//            Byte b = 180;
//            System.out.println(Arrays.toString(b));

        log.error(Integer.toHexString(0x80));
    }

}
