package top.jolyoulu.corecommon.utils;

/**
 * @Author: JolyouLu
 * @Date: 2023/3/24 17:17
 * @Description
 */
public class IPUtils {

    /**
     * 将ip地址转换为数字
     * 将每个整数乘以256的相应次幂，最后，将这些乘积相加，得到一个32位无符号整数，这就是IPv4地址对应的数字
     *
     * @param ipAddress
     * @return
     */
    public static Long ipToNumber(String ipAddress) {
        String[] ipAddressInArray = ipAddress.split("\\.");
        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {
            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }
        return result;
    }

}
