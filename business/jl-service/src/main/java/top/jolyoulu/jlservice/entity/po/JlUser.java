package top.jolyoulu.jlservice.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName(value = "jl_user")
public class JlUser{

    /** 用户id */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 昵称 */
    private String nickName;

    /** 用户头像 */
    private String salt;

    /** 逻辑删除 1（true）已删除， 0（false）未删除 */
    private int delFlag;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

}