package top.jolyoulu.jlservice.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName(value = "jl_menu")
public class JlMenu{

    /** 编号  */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /** 所属上级 */
    private String pid;

    /** 名称 */
    private String name;

    /** 类型(1:菜单，2:按钮) */
    private int type;

    /** 权限值 */
    private String permissionValue;

    /** 访问路径 */
    private String path;

    /** 组件路径 */
    private String component;

    /** 图标 */
    private String icon;

    /** 状态(0:禁止，1:正常) */
    private int status;

    /** 逻辑删除 1（true）已删除， 0（false）未删除 */
    private int delFlag;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

}