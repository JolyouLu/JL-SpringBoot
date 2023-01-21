package top.jolyoulu.modules.logbackmodule.aspects;


import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/19 17:08
 * @Description Http日志拦截器，拦截并打印日志信息
 */
@Slf4j
@Aspect
@Component
public class HttpLogAspect {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution( * *.*..controller..*.*(..))")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 请求计时
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();
        long endTime = System.currentTimeMillis();
        // 打印日志
        String appVersion = request.getHeader("api-version");
        StringBuilder logStr = new StringBuilder();
        logStr.append("\nINFO: ").append(request.getMethod()).append(" ") //请求类型
                .append("app-version=").append(StringUtils.isBlank(appVersion) ? "1.0.0" : appVersion).append(" ") //版本号
                .append(request.getRequestURL().toString()).append(" ") //请求全路径
                .append(endTime - startTime).append("ms"); //耗时
        logStr.append("\nINFO: ").append("class-method=").append(pjp.getSignature().getDeclaringTypeName()).append(".").append(pjp.getSignature().getName()); //实现方法
        logStr.append("\nINFO: ").append("args=").append(Arrays.toString(pjp.getArgs())); //请求参数
        logStr.append("\nINFO: ").append("return=").append(ob); //返回值
        log.info(logStr.toString());
        return ob;
    }
}
