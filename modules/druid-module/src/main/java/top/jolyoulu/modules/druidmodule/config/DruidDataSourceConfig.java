package top.jolyoulu.modules.druidmodule.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/19 20:10
 * @Version 1.0
 * Druid连接池配置
 * 参考文档：<a href="https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98">文档地址</a>
 */
@Configuration
@EnableConfigurationProperties(value = DruidDataSourceProperties.class)
public class DruidDataSourceConfig {

    @Autowired
    private DruidDataSourceProperties druidDataSourceProperties;

    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        //基础连接参数配置
        dataSource.setDriverClassName(druidDataSourceProperties.getDriverClassName());
        dataSource.setUrl(druidDataSourceProperties.getJdbcUrl());
        dataSource.setUsername(druidDataSourceProperties.getUsername());
        dataSource.setPassword(druidDataSourceProperties.getPassword());
        //设置自定义的filter
        dataSource.setProxyFilters(Arrays.asList(statFilter()));
        return dataSource;
    }

    //自定义配置StatFilter的参数
    @Bean
    public StatFilter statFilter(){
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(3000);
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }

    //druid监控面板配置
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        HashMap<String, String> params = new HashMap<>();
        params.put("loginUsername", "admin");
        params.put("loginPassword", "123456");
        bean.setInitParameters(params);
        return bean;
    }

    //配置过滤器对druid监控面板资源放开
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

        HashMap<String, String> params = new HashMap<>();
        params.put("exclusions", "*.js,*.css,/druid/*");
        filterRegistrationBean.setInitParameters(params);
        return filterRegistrationBean;

    }

}
