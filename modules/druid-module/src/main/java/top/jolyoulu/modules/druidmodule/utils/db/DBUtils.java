package top.jolyoulu.modules.druidmodule.utils.db;

import top.jolyoulu.modules.druidmodule.entity.TableColumnInfo;
import top.jolyoulu.modules.druidmodule.entity.TableInfo;

import java.sql.Connection;
import java.util.List;

/**
 * @Author: JolyouLu
 * @Date: 2023/4/5 13:33
 * @Description
 */
public abstract class DBUtils {
    protected Connection connection;

    public DBUtils(Connection connection) {
        this.connection = connection;
    }

    public abstract boolean check();

    public abstract List<String> selectDatabases();

    public abstract List<TableInfo> selectTables(String tableSchema);

    public abstract List<TableColumnInfo> selectTableColumns(String tableSchema, String tableName);

    public abstract void close();
}
