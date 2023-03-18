package top.jolyoulu.corecommon.utils;

import java.util.Random;

/**
 * @Author: JolyouLu
 * @Date: 2023/3/18 14:53
 * @Description
 */
public class RandomUtils {

    private static final String str = "qwertyuioplkjhgfdsazxcvbnmQAZWSXEDCRFVTGBYHNUJMIKLOP0123456789";

    /**
     * 生成随机字符串
     * @param len 字符串长度
     * @return
     */
    public static String genStr(int len){
        StringBuilder res = new StringBuilder();
        Random rd = new Random();
        for (int i = 0; i < len; i++) {
            res.append(str.charAt(rd.nextInt(str.length())));
        }
        return res.toString();
    }

    /**
     * 生成随机字符串
     * @param len 字符串长度
     * @param sourceStr 从源字符串中生成
     * @return
     */
    public static String genStr(int len,String sourceStr){
        StringBuilder res = new StringBuilder();
        Random rd = new Random();
        for (int i = 0; i < len; i++) {
            res.append(sourceStr.charAt(rd.nextInt(sourceStr.length())));
        }
        return res.toString();
    }

}
