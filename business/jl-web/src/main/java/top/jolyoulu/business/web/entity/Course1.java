package top.jolyoulu.business.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description course_1
 * @author zhengkai.blog.csdn.net
 * @date 2023-01-19
 */
@Data
@TableName(value = "course_1")
public class Course1 {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * cname
    */
    private String cname;

    /**
    * user_id
    */
    private Long userId;

    /**
    * cstatus
    */
    private String cstatus;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public Course1() {}
}