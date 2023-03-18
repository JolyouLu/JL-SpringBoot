package top.jolyoulu.webbootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: JolyouLu
 * @Date: 2023/3/18 12:20
 * @Description
 */
@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("top.jolyoulu.**.dao")                  //Mapper扫描
@ComponentScan(basePackages = {"top.jolyoulu.*"})   //Bean扫描
public class WebBootstrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebBootstrapApplication.class,args);
    }
}
