package top.jolyoulu.jlwechatpub.msghandler;

import lombok.extern.slf4j.Slf4j;
import top.jolyoulu.jlwechatpub.wechatpub.enums.MsgType;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.entity.image.ImageCtx;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.entity.image.ImageMessage;
import top.jolyoulu.jlwechatpub.wechatpub.pipline.AbstractRequestHandlerContextAdapter;
import top.jolyoulu.jlwechatpub.wechatpub.pipline.RequestContext;
import top.jolyoulu.jlwechatpub.wechatpub.utils.PassiveMsgUtil;

import java.util.Date;
import java.util.Map;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/20 15:34
 * @Version 1.0
 * 图片处理Handle
 */
@Slf4j
public class ImageTypeHandle extends AbstractRequestHandlerContextAdapter {

    public ImageTypeHandle(String name) {
        super(name);
    }

    @Override
    public void requestHandle(RequestContext requestContext) {
        Map<String, String> msgMap = requestContext.getMsg();
        if (msgMap.get("MsgType").equals(MsgType.IMAGE_TYPE.getType())){
            ImageMessage imageMessage = new ImageMessage();
            imageMessage.setToUserName(msgMap.get("FromUserName"));
            imageMessage.setFromUserName(msgMap.get("ToUserName"));
            imageMessage.setCreateTime(new Date().getTime());
            imageMessage.setMsgType(MsgType.IMAGE_TYPE.getType());
            imageMessage.setImage(new ImageCtx(msgMap.get("MediaId")));
            String xml = PassiveMsgUtil.INSTANCE.getMsgXml(imageMessage);
            requestContext.resultMsg(xml);
        }else {
            super.requestHandlerContext.requestHandle(requestContext);
        }
    }
}
