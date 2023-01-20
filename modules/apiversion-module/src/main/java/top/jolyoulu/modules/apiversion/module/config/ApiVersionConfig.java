package top.jolyoulu.modules.apiversion.module.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import top.jolyoulu.modules.apiversion.module.apiverion.ApiHandlerMapping;


/**
 * @Author: JolyouLu
 * @Date: 2023/1/20 15:49
 * @Description
 */
@Configuration
public class ApiVersionConfig extends WebMvcConfigurationSupport {

    //自定义映射
    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new ApiHandlerMapping();
    }
}
