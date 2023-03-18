package top.jolyoulu.modules.apiversionmodule.entity;

import lombok.Data;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author LuZhouJin
 * @Date 2023/3/15
 */
@Data
public class WebMvcInterceptor {

    private final HandlerInterceptor interceptor;

    private final List<String> pathPatterns = new ArrayList<>();

    private final List<String> excludePathPatterns = new ArrayList<>();

    public WebMvcInterceptor(HandlerInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    /**
     * 需要拦截路径
     * @param path
     * @return
     */
    public WebMvcInterceptor addPathPatterns(String... path){
        pathPatterns.addAll(Arrays.asList(path));
        return this;
    }

    /***
     * 屏蔽的路径
     * @param path
     * @return
     */
    public WebMvcInterceptor excludePathPatterns(String... path){
        excludePathPatterns.addAll(Arrays.asList(path));
        return this;
    }
}
