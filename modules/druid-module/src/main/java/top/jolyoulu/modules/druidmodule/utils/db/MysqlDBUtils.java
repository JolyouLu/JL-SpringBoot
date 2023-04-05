package top.jolyoulu.modules.druidmodule.utils.db;

import lombok.extern.slf4j.Slf4j;
import top.jolyoulu.modules.druidmodule.constant.DBConstant;
import top.jolyoulu.modules.druidmodule.entity.TableColumnInfo;
import top.jolyoulu.modules.druidmodule.entity.TableInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: JolyouLu
 * @Date: 2023/3/25 17:20
 * @Description Mysql数据库操作
 * 可使用 桥接模式 优化
 */
@Slf4j
public class MysqlDBUtils extends DBUtils {

    private MysqlDBUtils(Connection connection) {
        super(connection);
    }

    /**
     * 构建一个mysqldb工具类
     *
     * @param host      地址
     * @param port      端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    protected static DBUtils build(String host,String port, String username, String password) {
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            Connection conn = DriverManager.getConnection(DBConstant.getMysqlUrl(host,port), username, password);
            return new MysqlDBUtils(conn);
        } catch (Exception e) {
            log.error("", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 构建一个mysqldb工具类
     *
     * @param url      数据库连接地址
     * @param username 用户名
     * @param password 密码
     * @return
     */
    protected static DBUtils build(String url, String username, String password) {
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            Connection conn = DriverManager.getConnection(url, username, password);
            return new MysqlDBUtils(conn);
        } catch (Exception e) {
            log.error("", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 构建一个mysqldb工具类
     *
     * @return
     */
    protected static DBUtils build(Connection connection) {
        try {
            return new MysqlDBUtils(connection);
        } catch (Exception e) {
            log.error("", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查连接
     * @return
     */
    @Override
    public boolean check() {
        try {
            String sql = CommSQL.TEST.getSql();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) { //下移一列
                int status = resultSet.getInt(1);
                return status == 1;
            }
            pstmt.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error("", e);
        }
        return false;
    }

    /**
     * 查看当前连接的数据库
     * @return
     */
    @Override
    public List<String> selectDatabases() {
        ArrayList<String> res = new ArrayList<>();
        try {
            String sql = CommSQL.SHOW_DATABASES.getSql();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) { //下移一列
                res.add(resultSet.getString("Database"));
            }
            pstmt.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error("", e);
        }
        return res;
    }

    /**
     * 查看指定库中的表
     * @param tableSchema 库
     * @return
     */
    @Override
    public List<TableInfo> selectTables(String tableSchema) {
        ArrayList<TableInfo> res = new ArrayList<>();
        try {
            String sql = CommSQL.INFORMATION_SCHEMA_TABLES.getSql();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, tableSchema);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) { //下移一列
                res.add(new TableInfo(
                        resultSet.getString("TABLE_NAME"),
                        resultSet.getString("ENGINE"),
                        resultSet.getLong("DATA_LENGTH"),
                        resultSet.getString("TABLE_COLLATION"),
                        resultSet.getString("TABLE_COMMENT")
                ));
            }
            pstmt.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error("", e);
        }
        return res;
    }

    /**
     * 查看指定库，表中的字段信息
     * @param tableSchema 库
     * @param tableName 表
     * @return
     */
    @Override
    public List<TableColumnInfo> selectTableColumns(String tableSchema, String tableName) {
        ArrayList<TableColumnInfo> res = new ArrayList<>();
        try {
            String sql = CommSQL.INFORMATION_SCHEMA_COLUMNS.getSql();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, tableSchema);
            pstmt.setString(2, tableName);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) { //下移一列
                res.add(new TableColumnInfo(
                        resultSet.getString("TABLE_NAME"),
                        resultSet.getString("COLUMN_NAME"),
                        resultSet.getString("DATA_TYPE"),
                        resultSet.getString("COLUMN_COMMENT")
                ));
            }
            pstmt.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error("", e);
        }
        return res;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private enum CommSQL {
        TEST("SELECT 1"),
        SHOW_DATABASES("SHOW databases"),
        INFORMATION_SCHEMA_TABLES("SELECT * FROM information_schema.tables WHERE table_schema = ?"),
        INFORMATION_SCHEMA_COLUMNS("SELECT *,data_type FROM information_schema.columns WHERE table_schema= ? AND table_name = ?"),
        ;

        private final String sql;

        CommSQL(String sql) {
            this.sql = sql;
        }

        public String getSql() {
            return sql;
        }
    }

    public static void main(String[] args) {
        DBUtils utils = DBUtilsFactory.creatDBUtils(DBType.MYSQL, "localhost","3306", "root", "123456");
        System.out.println(utils.check());
        System.out.println(utils.selectDatabases());
        System.out.println(utils.selectTables("codegen"));
        System.out.println(utils.selectTableColumns("codegen", "data_source_info"));
        utils.close();
    }

}
