package top.jolyoulu.modules.apiversionmodule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import top.jolyoulu.modules.apiversionmodule.apiverion.ApiHandlerMapping;
import top.jolyoulu.modules.apiversionmodule.entity.WebMvcInterceptor;


/**
 * @Author: JolyouLu
 * @Date: 2023/1/20 15:49
 * @Description
 */
@Configuration
public class ApiVersionConfig extends DelegatingWebMvcConfiguration {

    @Autowired
    private InterceptorService interceptorConfig;

    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new ApiHandlerMapping();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        for (WebMvcInterceptor item : interceptorConfig.getInterceptorList()) {
            registry.addInterceptor(item.getInterceptor())
                    .addPathPatterns(item.getPathPatterns())
                    .excludePathPatterns(item.getExcludePathPatterns());
        }
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        //匹配路径 /swagger-ui/** 加载swagger-ui资源
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
        super.addResourceHandlers(registry);
    }
}
