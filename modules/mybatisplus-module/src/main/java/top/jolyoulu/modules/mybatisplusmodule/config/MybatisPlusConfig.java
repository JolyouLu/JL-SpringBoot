package top.jolyoulu.modules.mybatisplusmodule.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import top.jolyoulu.modules.mybatisplusmodule.plugin.dataMask.DataMaskPlugin;
import top.jolyoulu.modules.mybatisplusmodule.plugin.sqllog.SqlLogPlugin;

import javax.sql.DataSource;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/20 15:11
 * @Description Mybatis分页插件配置
 */
@Configuration
public class MybatisPlusConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory MybatisSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*.xml")
        );
        factoryBean.setDataSource(dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.addInterceptor(new DataMaskPlugin());
        configuration.addInterceptor(new SqlLogPlugin());
        factoryBean.setConfiguration(configuration);
        return factoryBean.getObject();
    }

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
