package top.jolyoulu.common.constant;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 19:50
 * @Description redis缓存key
 */
public class RedisConstant {

    /**
     * token
     * 用户id
     */
    private final static String TOKEN = "TOKEN:%s";
    public static String getToken(String userId){
        return String.format(TOKEN,userId);
    }

}
