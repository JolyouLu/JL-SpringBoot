package top.jolyoulu.jlwechatpub.wechatpub.passivemsg.entity.voice;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsg;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsgProperty;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/25 13:57
 * @Version 1.0
 * 回复语音消息具体内容
 */
@Data
@AllArgsConstructor
@PassiveMsg
public class VoiceCtx {

    @PassiveMsgProperty(name = "MediaId",addCDATA = true)
    private String mediaId;

}
