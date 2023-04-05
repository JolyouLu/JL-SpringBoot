package top.jolyoulu.modules.druidmodule.utils.db;

import java.sql.Connection;

/**
 * @Author: JolyouLu
 * @Date: 2023/4/5 13:49
 * @Description
 */
public class DBUtilsFactory {

    /**
     * 基于已有的连接，获取一个DBUtils
     *
     * @param type       数据库类型
     * @param connection 连接
     * @return
     */
    public static DBUtils creatDBUtils(DBType type, Connection connection) {
        DBUtils dbUtils = null;
        switch (type) {
            case MYSQL:
                dbUtils = MysqlDBUtils.build(connection);
                break;
        }
        return dbUtils;
    }

    /**
     * 获取一个DBUtils
     *
     * @param type     数据库类型
     * @param host     地址
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public static DBUtils creatDBUtils(DBType type, String host, String port, String username, String password) {
        DBUtils dbUtils = null;
        switch (type) {
            case MYSQL:
                dbUtils = MysqlDBUtils.build(host, port, username, password);
                break;
        }
        return dbUtils;
    }
}
