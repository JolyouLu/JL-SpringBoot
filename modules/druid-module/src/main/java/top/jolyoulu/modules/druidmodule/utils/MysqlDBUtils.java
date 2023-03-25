package top.jolyoulu.modules.druidmodule.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * @Author: JolyouLu
 * @Date: 2023/3/25 17:20
 * @Description
 * Mysql数据库操作
 */
@Slf4j
public class MysqlDBUtils {

    /**
     * 获取一个JDBC连接
     * @param url 数据库连接地址
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public static Connection getConnection(String url,String username,String password){
        Connection conn = null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            log.error("",e);
        }
        return conn;
    }

    /**
     * 测试连接是否正常
     * @param connection
     * @return
     */
    public static boolean test(Connection connection){
        try {
            String sql = CommSQL.TEST.getSql();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()){ //下移一列
                int status = resultSet.getInt(1);
                return status == 1;
            }
            pstmt.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error("",e);
        }
        return false;
    }

    private enum CommSQL{
        TEST("SELECT 1");

        private final String sql;

        CommSQL(String sql) {
            this.sql = sql;
        }

        public String getSql() {
            return sql;
        }
    }

    public static void main(String[] args) {
        Connection connection = MysqlDBUtils.getConnection("jdbc:mysql://localhost:3306/codegen", "root", "123456");
        boolean testConnection = MysqlDBUtils.test(connection);
    }

}
