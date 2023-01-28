package top.jolyoulu.jlwechatpub.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.jolyoulu.common.constant.RedisConstant;
import top.jolyoulu.jlwechatpub.wechatpub.utils.MessageUtil;
import top.jolyoulu.modules.redismodule.utils.RedisUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: LZJ
 * @Date: 2020/6/23 9:07
 * @Version 1.0
 */
@Slf4j
@Aspect
@Component
public class VxMsgAspect {

    //定义切入点
    @Pointcut("execution(* top.jolyoulu.jlwechatpub.controller.VxController.receiver(..))")
    public void pointCutVXReceiver(){};

    //环绕通知
    @Around("pointCutVXReceiver()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //消息重排解决方案默认使用（SpringAop+HashMap）
        return mapSolution(pjp);
    }

    /**
     * 消息重排解决方案一(默认使用)：SpringAop+HashMap，适合普通单机应用
     */
    //线程安全的HashMap
    private static final ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();

    public Object mapSolution(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        //从连接点中获取参数
        Object[] args = pjp.getArgs();
        String xml = (String) args[0];
        //将来获取到的参数格式化
        Map<String, String> msgMap = MessageUtil.string2Map(xml);
        String msgId = msgMap.get("FromUserName") + msgMap.get("CreateTime");
        //如果已经存在于map中不做任何处理
        if (map.containsKey(msgId)){
            log.error("请求处理超时,接收到重复消息!");
            //直接返回空
            return null;
        }else {
            //否则将消息存入到map中
            map.put(msgId,msgId);
            //执行切面的方法，得到接口返回结果
            retVal = pjp.proceed(args);
            //执行完毕后删除map中保存的值
            map.remove(msgId);
        }
        //返回执行结果
        return retVal;
    }

    /**
     * 消息重排解决方案二(默认不使用)：SpringAop+Redis，适合分布式情况下
     */
    @Autowired
    private RedisUtils redisUtils;

    public Object redisSolution(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        //从连接点中获取参数
        Object[] args = pjp.getArgs();
        String xml = (String) args[0];
        //将来获取到的参数格式化
        Map<String, String> msgMap = MessageUtil.string2Map(xml);
        String msgId = msgMap.get("FromUserName") + msgMap.get("CreateTime");
        //检查是否存在重复消息
        String redisKey = RedisConstant.getVxUserMsg(msgId);
        if (redisUtils.exists(redisKey)){
            log.error("请求处理超时,接收到重复消息!");
            //直接返回空
            return null;
        }else {
            redisUtils.set(redisKey,msgId,10);
            //执行切面的方法，得到接口返回结果
            retVal = pjp.proceed(args);
        }
        return retVal;
    }
}
