package top.jolyoulu.jlservice.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName(value = "jl_role_menu")
public class JlRoleMenu {

    /** id */
    @TableId(type = IdType.AUTO)
    private String id;

    /** role_id */
    private String roleId;

    /** menu_id */
    private String menuId;

    /** 逻辑删除 1（true）已删除， 0（false）未删除 */
    private int delFlag;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;
}