package top.jolyoulu.jlwechatpub.wechatpub.pipline;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/20 11:07
 * @Version 1.0
 */
@Configuration
public class PipelineConfig {

    @Bean
    public DefaultRequestPipeline defaultRequestPipeline(){
        DefaultRequestPipeline pipeline = new DefaultRequestPipeline(new HeadHandle("headHandle"));
        pipeline.addHandler(new TailHandle("tailHandle"));
        return pipeline;
    }
}
