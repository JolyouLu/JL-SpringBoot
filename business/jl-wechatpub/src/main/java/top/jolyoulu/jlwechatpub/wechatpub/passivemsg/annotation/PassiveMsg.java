package top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/25 10:56
 * @Version 1.0
 * 被动消息回复注释类
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PassiveMsg {
}
