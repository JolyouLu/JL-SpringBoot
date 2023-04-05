package top.jolyoulu.modules.druidmodule.constant;

/**
 * @Author: JolyouLu
 * @Date: 2023/4/5 16:39
 * @Description
 */
public class DBConstant {

    private static final String MYSQL_URL = "jdbc:mysql://%s:%s/";
    public static String getMysqlUrl(String host,String port){
        return String.format(MYSQL_URL,host,port);
    }
}
