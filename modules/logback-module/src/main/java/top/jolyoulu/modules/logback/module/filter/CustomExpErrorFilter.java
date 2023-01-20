package top.jolyoulu.modules.logback.module.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import top.jolyoulu.common.exception.GlobalException;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/19 17:14
 * @Description error.global.current.log 文件日志内容过滤器：只输出与GlobalException相关日志
 */
public class CustomExpErrorFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getLevel().equals(Level.ERROR)){
            ThrowableProxy throwableProxy = (ThrowableProxy) event.getThrowableProxy();
            if (null == throwableProxy){
                return FilterReply.DENY;
            }
            Throwable throwable = throwableProxy.getThrowable();
            if (throwable instanceof GlobalException){
                return FilterReply.ACCEPT;
            }
        }
        return FilterReply.DENY;
    }
}