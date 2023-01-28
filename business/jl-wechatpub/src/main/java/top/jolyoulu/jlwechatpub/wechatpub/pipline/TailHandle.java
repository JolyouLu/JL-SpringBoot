package top.jolyoulu.jlwechatpub.wechatpub.pipline;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/20 15:50
 * @Version 1.0
 * 管道尾
 */
@Slf4j
public class TailHandle extends AbstractRequestHandlerContextAdapter {
    public TailHandle(String name) {
        super(name);
    }

    @Override
    public void requestHandle(RequestContext requestContext) {
        log.warn("未处理消息：{}",requestContext.getMsg());
    }
}
