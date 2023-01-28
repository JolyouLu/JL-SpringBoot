package top.jolyoulu.jlwechatpub.wechatpub.pipline;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/20 10:16
 * @Version 1.0
 */
public interface RequestPipeline {

    void addHandler(AbstractRequestHandlerContextAdapter ctx);

}
