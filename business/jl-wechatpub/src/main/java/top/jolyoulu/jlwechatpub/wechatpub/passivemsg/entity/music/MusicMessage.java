package top.jolyoulu.jlwechatpub.wechatpub.passivemsg.entity.music;

import lombok.Data;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsg;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsgProperty;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.entity.BaseMessage;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/25 14:21
 * @Version 1.0
 */
@Data
@PassiveMsg
public class MusicMessage extends BaseMessage {

    @PassiveMsgProperty(name = "Music",contentObj = MusicCtx.class)
    private MusicCtx music;

}
