package top.jolyoulu.corecommon.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @Author: JolyouLu
 * @Date: 2023/3/18 20:42
 * @Description
 */
public class AESUtils {

    /**
     * AES 加密算法
     * @param input 原文
     * @param key 密钥（必须16个字节）
     * @return
     * @throws Exception
     */
    private static String encrypt(String input, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        //创建加密规则 参数1：密钥的字节 参数2：加密类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        //进行加密初始化 参数1：选择模式，加密模式/解密模式 参数2：加密规则
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        //传入原文字节数值，调用加密方法
        byte[] bytes = cipher.doFinal(input.getBytes());
        String str = new String(Base64.getEncoder().encode(bytes));
        return str;
    }

    /**
     * AES 解密算法
     * @param input 密文
     * @param key 密钥（必须16个字节）
     * @return
     * @throws Exception
     */
    private static String decrypt(String input, String key) throws Exception {
        byte[] deBase64 = Base64.getDecoder().decode(input.getBytes());
        Cipher cipher = Cipher.getInstance("AES");
        //创建解密规则 参数1：密钥的字节 参数2：加密类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        //进行解密初始化 参数1：选择模式，加密模式/解密模式 参数2：加密规则
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        //传入密文字节数值，调用解密方法
        byte[] bytes = cipher.doFinal(deBase64);
        return new String(bytes);
    }

    public static void main(String[] args) throws Exception{
        //原文
        String input = "Hello World";
        //定义密钥（使用AES加密，密钥必须是16字节）
        String key = "1234567812345678";
        System.out.println("<======加密======>");
        String encryptAES = encrypt(input, key);
        System.out.println(encryptAES);
        System.out.println("<======解密======>");
        String decryptAES = decrypt(encryptAES, key);
        System.out.println(decryptAES);
    }

}
