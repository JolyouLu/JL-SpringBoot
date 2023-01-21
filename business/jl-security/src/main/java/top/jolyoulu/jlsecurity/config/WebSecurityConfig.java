package top.jolyoulu.jlsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import top.jolyoulu.jlsecurity.filter.TokenAuthenticationFilter;
import top.jolyoulu.jlsecurity.filter.UsernamePasswordLoginFilter;
import top.jolyoulu.jlsecurity.security.DefaultPasswordEncoderImpl;
import top.jolyoulu.jlsecurity.security.TokenLogoutHandlerImpl;
import top.jolyoulu.jlsecurity.service.impl.*;
import top.jolyoulu.jlsecurity.security.TokenManager;
import top.jolyoulu.modules.redismodule.utils.RedisUtils;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 17:04
 * @Description
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DefaultPasswordEncoderImpl defaultPasswordEncoderImpl;

    /**
     * 过滤器、登录配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //不通过session获取securityContext
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                //认证失败处理器
                .accessDeniedHandler(new AccessDeniedHandlerImpl())
                .authenticationEntryPoint(new UnAuthEntryPointImpl())
                .and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                //退出登录的接口，以及退出登录处理器配置
                .and()
                .logout().logoutUrl("/security/logout")
                .addLogoutHandler(new TokenLogoutHandlerImpl(tokenManager,redisUtils))
                .logoutSuccessHandler(new TokenLogoutSuccessHandlerImpl())
                .and()
                //过滤器：用户密码认证过滤器，处理认证成功/失败后的操作
                .addFilter(new UsernamePasswordLoginFilter(authenticationManager(),tokenManager,redisUtils))
                //过滤器：根据token解析权限数据加入到SecurityContextHolder
                .addFilter(new TokenAuthenticationFilter(authenticationManager(),tokenManager,redisUtils))
                .httpBasic();
    }

    /**
     * 不进行认证的路径设置
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                // 静态资源，可匿名访问
                .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**","/swagger-ui/**")
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**");
    }

    /**
     * 调用userDetailsService和密码处理
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoderImpl);
    }
}
