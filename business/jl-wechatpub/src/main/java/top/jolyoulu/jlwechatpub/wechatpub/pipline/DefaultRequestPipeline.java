package top.jolyoulu.jlwechatpub.wechatpub.pipline;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/20 10:18
 * @Version 1.0
 * 默认的管道实现用于管理所有的Handler
 */
public class DefaultRequestPipeline implements RequestPipeline{

    private AbstractRequestHandlerContextAdapter ctx;

    public DefaultRequestPipeline(AbstractRequestHandlerContextAdapter ctx) {
        this.ctx = ctx;
    }

    /**
     * 迭代添加Handler
     * @param next
     */
    @Override
    public void addHandler(AbstractRequestHandlerContextAdapter next) {
        AbstractRequestHandlerContextAdapter context = this.ctx;
        AbstractRequestHandlerContextAdapter tail = null;

        while (context.getRequestHandlerContext() != null){
            //如果下一个TailHandle那么就不获取了
            if (context.getRequestHandlerContext() instanceof TailHandle){
                tail = context.getRequestHandlerContext();
                break;
            }else {
                context = context.getRequestHandlerContext();
            }
        }
        context.setRequestHandlerContext(next);
        context.getRequestHandlerContext().setRequestHandlerContext(tail);
    }

    public AbstractRequestHandlerContextAdapter getCtx() {
        return ctx;
    }
}
