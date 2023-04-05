package top.jolyoulu.modules.druidmodule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: JolyouLu
 * @Date: 2023/4/5 11:24
 * @Description
 */
@Data
@AllArgsConstructor
@ToString
public class TableColumnInfo {
    /**表名称*/
    private String tableName;
    /**字段名称*/
    private String columnName;
    /**字段类型*/
    private String dataType;
    /**字段备注*/
    private String columnComment;
}
