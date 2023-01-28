package top.jolyoulu.jlwechatpub.msghandler;

import lombok.extern.slf4j.Slf4j;
import top.jolyoulu.jlwechatpub.wechatpub.enums.MsgType;
import top.jolyoulu.jlwechatpub.wechatpub.pipline.AbstractRequestHandlerContextAdapter;
import top.jolyoulu.jlwechatpub.wechatpub.pipline.RequestContext;

import java.util.Map;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/20 17:02
 * @Version 1.0
 */
@Slf4j
public class EventTypeHandler extends AbstractRequestHandlerContextAdapter {
    public EventTypeHandler(String name) {
        super(name);
    }

    @Override
    public void requestHandle(RequestContext requestContext) {
        Map<String, String> msgMap = requestContext.getMsg();
        if (msgMap.get("MsgType").equals(MsgType.EVENT_TYPE.getType())){
            log.info("进入到EventTypeHandler");
        }else {
            super.requestHandlerContext.requestHandle(requestContext);
        }
    }
}
