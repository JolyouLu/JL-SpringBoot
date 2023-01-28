package top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/25 10:46
 * @Version 1.0
 * 被动消息回复注解字段
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PassiveMsgProperty {

    /**
     * 对应微信消息的字段
     */
    String name() default "";

    /**
     * 是否需添加CDATA
     */
    boolean addCDATA() default false;

    /**
     * 如果包含对象需设置对象class
     */
    Class<?> contentObj() default String.class;
}
