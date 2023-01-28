package top.jolyoulu.jlwechatpub.wechatpub.passivemsg.entity;

import lombok.Data;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsg;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsgProperty;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/25 15:14
 * @Version 1.0
 */
@Data
@PassiveMsg
public class BaseMessage {

    @PassiveMsgProperty(name = "ToUserName",addCDATA = true)
    protected String toUserName;

    @PassiveMsgProperty(name = "FromUserName",addCDATA = true)
    protected String fromUserName;

    @PassiveMsgProperty(name = "CreateTime")
    protected Long createTime;

    @PassiveMsgProperty(name = "MsgType",addCDATA = true)
    protected String msgType;

}
