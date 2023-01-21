package top.jolyoulu.jlsecurity.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import top.jolyoulu.common.constant.GlobalConstant;
import top.jolyoulu.common.constant.RedisConstant;
import top.jolyoulu.common.entity.ResultInfo;
import top.jolyoulu.common.enums.GlobalExpType;
import top.jolyoulu.common.utils.ResponseUtils;
import top.jolyoulu.jlsecurity.entity.bo.SecurityUser;
import top.jolyoulu.jlsecurity.security.TokenManager;
import top.jolyoulu.jlservice.entity.po.JlUser;
import top.jolyoulu.modules.redismodule.utils.RedisUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 16:02
 * @Description 用户密码登录过滤器
 */
public class UsernamePasswordLoginFilter extends UsernamePasswordAuthenticationFilter {

    private TokenManager tokenManager;
    private RedisUtils redisUtils;
    private AuthenticationManager authenticationManager;

    public UsernamePasswordLoginFilter(AuthenticationManager authenticationManager,
                                       TokenManager tokenManager,
                                       RedisUtils redisUtils) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisUtils = redisUtils;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/security/login", "POST"));
    }

    /**
     * 获取表单提交用户和密码
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            JlUser user = new ObjectMapper().readValue(request.getInputStream(), JlUser.class);
            String username = user.getUsername();
            String password = user.getPassword();
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 认证成功处理
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        //认证成功后先获取认证成功的用户信息
        SecurityUser user = (SecurityUser) authResult.getPrincipal();
        //根据用户名生成token
        String token = tokenManager.creatToken(user.getCurrentUserInfo());
        //把用户名称和用户权限列表加入到redis
        String key = RedisConstant.getToken(user.getCurrentUserInfo().getId());
        redisUtils.set(key,user.getPermissionValueList(), GlobalConstant.TOKEN_TIMEOUT, TimeUnit.SECONDS);
        //返回token
        ResponseUtils.outJson(response, ResultInfo.success(token));
    }

    /**
     * 认证失败处理
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        //返回错误提示
        ResponseUtils.outJson(response, new ResultInfo(GlobalExpType.USERNAME_PASSWORD_ERROR));
    }
}
