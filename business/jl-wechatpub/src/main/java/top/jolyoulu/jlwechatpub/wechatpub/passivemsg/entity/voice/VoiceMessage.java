package top.jolyoulu.jlwechatpub.wechatpub.passivemsg.entity.voice;

import lombok.Data;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsg;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsgProperty;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.entity.BaseMessage;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/25 13:57
 * @Version 1.0
 * 回复语音消息实体
 */
@Data
@PassiveMsg
public class VoiceMessage extends BaseMessage {

    @PassiveMsgProperty(name = "Voice",contentObj = VoiceCtx.class)
    private VoiceCtx voice;
}
