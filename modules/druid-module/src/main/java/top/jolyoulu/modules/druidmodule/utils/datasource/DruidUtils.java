package top.jolyoulu.modules.druidmodule.utils.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: JolyouLu
 * @Date: 2023/3/25 17:04
 * @Description 数据源工具类型
 */
@Slf4j
public class DruidUtils {

    private static final Map<String, DruidDataSource> map = new ConcurrentHashMap<>();

    /**
     * 根据id构建数据源
     */
    public static DataSource creatDataSource(String id, Properties prop) {
        try {
            if (map.containsKey(id)) {
                return map.get(id);
            } else {
                DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(prop);
                if (Objects.nonNull(map.putIfAbsent(id, dataSource))) {
                    dataSource.close();
                    return map.get(id);
                }
                return dataSource;
            }
        } catch (Exception e) {
            log.error("", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id获取一个数据源
     *
     * @param id
     * @return
     */
    public static DataSource getDataSource(String id) {
        DataSource dataSource = null;
        if (map.containsKey(id)) {
            dataSource = map.get(id);
        }
        return dataSource;
    }

    /**
     * 关闭一个数据源
     *
     * @param id
     * @return
     */
    public static void cloDataSource(String id) {
        if (map.containsKey(id)) {
            DruidDataSource dataSource = map.get(id);
            dataSource.close();
        }
    }

    /**
     * 根据id获取一个数据库连接
     *
     * @param id
     * @return
     */
    public static Connection getConnection(String id) {
        Connection connection = null;
        try {
            if (map.containsKey(id)) {
                connection = map.get(id).getConnection();
            }
        } catch (SQLException e) {
            log.error("", e);
        }
        return connection;
    }


}
