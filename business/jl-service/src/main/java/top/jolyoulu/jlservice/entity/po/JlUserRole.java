package top.jolyoulu.jlservice.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName(value = "jl_user_role")
public class JlUserRole{

    /** 主键id */
    @TableId(type = IdType.AUTO)
    private String id;

    /** 角色id */
    private String roleId;

    /** 用户id */
    private String userId;

    /** 逻辑删除 1（true）已删除， 0（false）未删除 */
    private int delFlag;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;
}