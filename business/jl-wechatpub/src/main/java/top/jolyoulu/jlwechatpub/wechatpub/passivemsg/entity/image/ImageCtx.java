package top.jolyoulu.jlwechatpub.wechatpub.passivemsg.entity.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsg;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsgProperty;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/25 11:15
 * @Version 1.0
 * 回复图片消息具体内容
 */
@Data
@AllArgsConstructor
@PassiveMsg
public class ImageCtx {

    @PassiveMsgProperty(name = "MediaId",addCDATA = true)
    private String mediaId;

}
