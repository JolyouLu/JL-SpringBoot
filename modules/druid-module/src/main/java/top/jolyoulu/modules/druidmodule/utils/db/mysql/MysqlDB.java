package top.jolyoulu.modules.druidmodule.utils.db.mysql;

import top.jolyoulu.modules.druidmodule.entity.TableColumnInfo;
import top.jolyoulu.modules.druidmodule.entity.TableInfo;

import java.util.List;

/**
 * @Author: JolyouLu
 * @Date: 2023/4/8 13:18
 * @Description
 */
public interface MysqlDB {

    /**
     * 获取数据库列表
     * @return
     */
    List<String> selectDatabases();

    /**
     * 获取指定数据库下的所有表
     * @param tableSchema
     * @return
     */
    List<TableInfo> selectTables(String tableSchema);

    /**
     * 获取指定库，表中的字段信息
     * @param tableSchema
     * @param tableName
     * @return
     */
    List<TableColumnInfo> selectTableColumns(String tableSchema, String tableName);
}
