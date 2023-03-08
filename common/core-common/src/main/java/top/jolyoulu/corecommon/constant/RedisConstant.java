package top.jolyoulu.corecommon.constant;

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

    /**
     * 微信令牌 ACCESS_TOKEN
     * 用户id
     */
    private final static String VX_ACCESS_TOKEN = "VX:ACCESS:TOKEN";
    public static String getVxAccessToken(){
        return VX_ACCESS_TOKEN;
    }

    /**
     * 微信用户消息
     * msgId
     */
    private final static String VX_USER_MSG = "VX:USER:MSG";
    public static String getVxUserMsg(String msgId){
        return String.format(VX_USER_MSG,msgId);
    }

}
