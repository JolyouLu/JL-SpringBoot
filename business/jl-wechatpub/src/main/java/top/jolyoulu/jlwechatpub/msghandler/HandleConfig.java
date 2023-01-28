package top.jolyoulu.jlwechatpub.msghandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.jolyoulu.jlwechatpub.wechatpub.pipline.DefaultRequestPipeline;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/20 11:37
 * @Version 1.0
 */
@Configuration
public class HandleConfig {

    @Autowired
    private DefaultRequestPipeline pipeline;

    @Bean
    public void initPipeline(){
        pipeline.addHandler(this.textTypeHandle());
        pipeline.addHandler(this.imageTypeHandle());
        pipeline.addHandler(this.eventTypeHandler());
    }

    @Bean
    public TextTypeHandle textTypeHandle(){
        return new TextTypeHandle("textTypeHandle");
    }

    @Bean
    public ImageTypeHandle imageTypeHandle(){
        return new ImageTypeHandle("imageTypeHandle");
    }

    @Bean
    public EventTypeHandler eventTypeHandler(){
        return new EventTypeHandler("eventTypeHandler");
    }

}
