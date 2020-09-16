package com.electricPower.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.NotBlank;
import java.io.*;

@Log4j2
public class BytesUtil {

    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    /**
     * InputStream转化为byte[]数组
     *
     * @param input InputStream
     * @return byte[]
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream input) throws IOException {
        String message;
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while ((message = reader.readLine()) != null) {
            log.info(message);
            return stringsToBytes(message.split(" "));
        }
        return null;
    }

    public static byte[] stringsToBytes(String[] s) {
        byte[] bytes = new byte[s.length];
        for (int i = 0; i < s.length; i++) {
            if (i == 1 || i == s.length - 2) {
//                if (s[i].toCharArray()[0] == '0')
                    bytes[i] = hexToByte(s[i]);
            } else
                bytes[i] = Byte.parseByte(s[i]);
        }
        return bytes;
    }


    /*
     * byte[]数组转十六进制
     */
    public static String bytes2hexStr(byte[] bytes) {
        int len = bytes.length;
        if (len == 0) {
            return null;
        }
        char[] cbuf = new char[len * 2];
        for (int i = 0; i < len; i++) {
            int x = i * 2;
            cbuf[x] = HEX_CHARS[(bytes[i] >>> 4) & 0xf];
            cbuf[x + 1] = HEX_CHARS[bytes[i] & 0xf];
        }
        return new String(cbuf);
    }

    /**
     * 16进制字符串转byte
     *
     * @param hex String
     * @return byte
     */
    public static byte hexToByte(String hex) {
        char[] chars = hex.toCharArray();
        log.error(hex + ":" + (byte) Integer.parseInt(hex, 16));
        return (byte) Integer.parseInt(String.valueOf(new char[]{chars[0], chars[1]}), 16);
    }

    /**
     * 16进制字符串转byte[]
     *
     * @param hexStr String
     * @return byte[]
     */
    public static byte[] hexToBytes(String hexStr) {
        if (hexStr.length() % 2 != 0) {//长度为单数
            hexStr = "0" + hexStr;//前面补0
        }
        char[] chars = hexStr.toCharArray();
        int len = chars.length / 2;
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            int x = i * 2;
            bytes[i] = (byte) Integer.parseInt(String.valueOf(new char[]{chars[x], chars[x + 1]}), 16);
        }
        return bytes;
    }

    /**
     * 截取字节
     *
     * @param bytes 需要截取的字节数组
     * @param begin 开始地址
     * @param count 数量
     * @return byte[]
     */
    public static byte[] subBytes(byte[] bytes, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(bytes, begin, bs, 0, count);
        return bs;
    }

    /**
     * 字节数组数据求和
     *
     * @param bytes 数组
     * @return int
     */
    public static int sumBytes(byte[] bytes) {
        int n = 0;
        for (byte b : bytes) n += b;
        return n;
    }
}
