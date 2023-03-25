package top.jolyoulu.corecommon.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @Author: JolyouLu
 * @Date: 2023/3/18 20:42
 * @Description
 */
public class DESUtils {

    /**
     * DES 加密算法
     * @param input 原文
     * @param key 密钥（必须8个字节）
     * @return
     * @throws Exception
     */
    public static String encrypt(String input, String key){
        try {
            if (key.length() != 8){
                throw new IllegalArgumentException("The key length must be equal to 8");
            }
            Cipher cipher = Cipher.getInstance("DES");
            //创建加密规则 参数1：密钥的字节 参数2：加密类型
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "DES");
            //进行加密初始化 参数1：选择模式，加密模式/解密模式 参数2：加密规则
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
            //传入原文字节数值，调用加密方法
            byte[] bytes = cipher.doFinal(input.getBytes());
            //打印密文,直接打印是乱码的，需要使用Base64打印
            String str = new String(Base64.getEncoder().encode(bytes));
            return str;
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * DES 解密算法
     * @param input 密文
     * @param key 密钥（必须8个字节）
     * @return
     * @throws Exception
     */
    public static String decrypt(String input, String key){
        try {
            if (key.length() != 8){
                throw new IllegalArgumentException("The key length must be equal to 8");
            }
            byte[] deBase64 = Base64.getDecoder().decode(input.getBytes());
            Cipher cipher = Cipher.getInstance("DES");
            //创建解密规则 参数1：密钥的字节 参数2：加密类型
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "DES");
            //进行解密初始化 参数1：选择模式，加密模式/解密模式 参数2：加密规则
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            //传入密文字节数值，调用解密方法
            byte[] bytes = cipher.doFinal(deBase64);
            //打印原文
            return new String(bytes);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) throws Exception{
        //原文
        String input = "Hello World";
        //定义密钥（使用DES加密，密钥必须是8字节）
        String key = "12345678";
        System.out.println("<======加密======>");
        String encryptDES = encrypt(input, key);
        System.out.println(encryptDES);
        System.out.println("<======解密======>");
        String decryptDES = decrypt(encryptDES, key);
        System.out.println(decryptDES);
    }

}
