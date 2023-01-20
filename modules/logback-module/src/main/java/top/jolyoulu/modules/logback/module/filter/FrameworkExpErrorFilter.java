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
 * @Description error.framework.current.log 文件日志内容过滤器：只输出非GlobalException的未知异常
 */
public class FrameworkExpErrorFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getLevel().equals(Level.ERROR)){
            if (event.getLoggerName().equals("com.alibaba.druid.filter.stat.StatFilter")){
                return FilterReply.DENY;
            }

            ThrowableProxy throwableProxy = (ThrowableProxy) event.getThrowableProxy();
            if (null == throwableProxy){
                return FilterReply.NEUTRAL;
            }
            Throwable throwable = throwableProxy.getThrowable();
            //非自定义异常都归类框架异常
            if (!(throwable instanceof GlobalException)){
                return FilterReply.ACCEPT;
            }
        }
        return FilterReply.DENY;
    }
}