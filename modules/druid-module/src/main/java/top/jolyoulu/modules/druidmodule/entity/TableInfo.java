package top.jolyoulu.modules.druidmodule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: JolyouLu
 * @Date: 2023/4/5 12:25
 * @Description
 */
@Data
@AllArgsConstructor
@ToString
public class TableInfo {
    /**表名称*/
    private String tableName;
    /**存储引擎*/
    private String engine;
    /**表数据长度*/
    private Long dataLength;
    /**表编码*/
    private String tableCollation;
    /**表备注*/
    private String tableComment;
}
