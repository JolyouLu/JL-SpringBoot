package top.jolyoulu.modules.apiversion.module.apiverion;

import java.lang.annotation.*;


/**
 * @Author: JolyouLu
 * @Date: 2023/1/20 15:50
 * @Description 自定义注解，用于controller方法中标记指定接口的版本号
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {
    // 版本，默认 1.0.0
    String value() default "1.0.0";
}
