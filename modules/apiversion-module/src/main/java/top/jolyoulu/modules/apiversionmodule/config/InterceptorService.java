package top.jolyoulu.modules.apiversionmodule.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.jolyoulu.modules.apiversionmodule.entity.WebMvcInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LuZhouJin
 * @Date 2023/3/15
 * 全局拦截器配置：由于MVC被ApiHandlerConfig接管了，只能使用该方法添加拦截器
 */
@Component
public class InterceptorService {

    private static final List<WebMvcInterceptor> interceptorList = new ArrayList<>();

    /**
     * 添加拦截器
     * @param interceptor
     */
    public void addInterceptor(WebMvcInterceptor interceptor){
        interceptorList.add(interceptor);
    }

    /**
     * 获取拦截器列表
     * @return
     */
    public List<WebMvcInterceptor> getInterceptorList(){
        return interceptorList;
    }
}
