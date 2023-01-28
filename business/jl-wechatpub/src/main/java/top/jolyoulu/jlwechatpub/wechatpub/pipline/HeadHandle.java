package top.jolyoulu.jlwechatpub.wechatpub.pipline;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/20 11:33
 * @Version 1.0
 * 管道头
 */
@Slf4j
public class HeadHandle extends AbstractRequestHandlerContextAdapter {

    public HeadHandle(String name) {
        super(name);
    }

    @Override
    public void requestHandle(RequestContext requestContext) {
        log.info("用户消息:{}",requestContext.getMsg());
        super.requestHandlerContext.requestHandle(requestContext);
    }
}
