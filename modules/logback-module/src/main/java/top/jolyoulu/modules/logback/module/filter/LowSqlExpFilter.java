package top.jolyoulu.modules.logback.module.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import top.jolyoulu.common.enums.GlobalException;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/19 17:14
 * @Description error.lowsql.current.log 文件日志内容过滤器：只输出druid捕捉到的慢日志
 * 超多少ms为慢日志通过修改 DruidDataSourceConfig.class 类
 */
public class LowSqlExpFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getLevel().equals(Level.ERROR)){
            if (event.getLoggerName().equals("com.alibaba.druid.filter.stat.StatFilter")){
                return FilterReply.ACCEPT;
            }
        }
        return FilterReply.DENY;
    }
}
