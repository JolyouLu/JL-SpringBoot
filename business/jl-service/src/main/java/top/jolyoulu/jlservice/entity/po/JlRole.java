package top.jolyoulu.jlservice.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName(value = "jl_role")
public class JlRole{

    /** 角色id */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /** 角色名称 */
    private String roleName;

    /** 角色编码 */
    private String roleCode;

    /** 备注 */
    private String remark;

    /** 逻辑删除 1（true）已删除， 0（false）未删除 */
    private int delFlag;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

}