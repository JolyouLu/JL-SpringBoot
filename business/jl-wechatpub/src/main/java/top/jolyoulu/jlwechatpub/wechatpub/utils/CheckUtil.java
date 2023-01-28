package top.jolyoulu.jlwechatpub.wechatpub.utils;

import java.util.Arrays;

/**
 * @Author: LZJ
 * @Date: 2020/5/24 0:17
 * @Version 1.0
 */
public class CheckUtil {

    /**
     * 验证微信get请求
     * @param token
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String token,String signature,String timestamp,String nonce){
        String[] arr = new String[]{token,timestamp,nonce};
        Arrays.sort(arr);
        StringBuffer content = new StringBuffer();
        for(int i = 0 ; i < arr.length ; i++){
            content.append(arr[i]);
        }
        String temp = Sha1Util.getSha1(content.toString());
        return temp.equals(signature);
    }
}
