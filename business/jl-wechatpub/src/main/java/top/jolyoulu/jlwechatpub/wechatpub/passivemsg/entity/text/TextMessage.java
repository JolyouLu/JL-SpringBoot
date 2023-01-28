package top.jolyoulu.jlwechatpub.wechatpub.passivemsg.entity.text;

import lombok.Data;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsg;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsgProperty;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.entity.BaseMessage;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/25 11:02
 * @Version 1.0
 * 回复文本消息实体类
 */
@Data
@PassiveMsg
public class TextMessage extends BaseMessage {

    @PassiveMsgProperty(name = "Content",addCDATA = true)
    private String content;
}
