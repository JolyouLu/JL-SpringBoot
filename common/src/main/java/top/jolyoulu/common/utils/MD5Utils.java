package top.jolyoulu.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 15:02
 * @Description
 */
public class MD5Utils {

    private final static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * MD5加密
     * @param input 加密内容
     * @return 返回16进制字符串
     */
    public static String encrypt(String input){
        return toHexStr(md5.digest(input.getBytes()));
    }

    /**
     * MD5加密
     * @param input 加密内容
     * @param salt 加盐
     * @return 返回16进制字符串
     */
    public static String encrypt(String input,String salt){
        return toHexStr(md5.digest((input + salt).getBytes()));
    }

    /**
     * 将bytes转为16进制格式
     */
    private static String toHexStr(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for (byte aByte : bytes) {
            String s = Integer.toHexString(aByte & 0xff);
            //判断如果密文长度是1，需要在高位补0
            if (s.length() == 1){
                s = "0" + s;
            }
            builder.append(s);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5Utils.encrypt("123456"));
    }
}
