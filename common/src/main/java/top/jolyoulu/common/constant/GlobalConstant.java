package top.jolyoulu.common.constant;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/20 21:34
 * @Description 全局公共常量
 */
public class GlobalConstant {

    /** UTF-8 字符集 */
    public static final String UTF8 = "UTF-8";

    /** token */
    public final static String TOKEN = "token";
    /** token 加密密钥 */
    public final static String TOKEN_SING_KEY = "jolyoulu";
    /** token 过期时间(秒) */
    public final static Integer TOKEN_TIMEOUT = 60 * 30;

    /** http请求成功的状态码 */
    public final static Integer HTTP_SUCCESS_CODE = 200;
    /** http请求失败的状态码 */
    public final static Integer HTTP_ERROR_CODE = 500;
    /** http未知连接状态码 */
    public final static Integer HTTP_NOT_FOUND = 404;

}
