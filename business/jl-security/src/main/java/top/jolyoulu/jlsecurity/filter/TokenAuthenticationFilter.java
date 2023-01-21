package top.jolyoulu.jlsecurity.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import top.jolyoulu.common.constant.GlobalConstant;
import top.jolyoulu.common.constant.RedisConstant;
import top.jolyoulu.jlsecurity.security.TokenManager;
import top.jolyoulu.modules.redismodule.utils.RedisUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 16:52
 * @Description 根据token获取用户权限信息，并存入到SecurityContextHolder中
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {


    private TokenManager tokenManager;
    private RedisUtils redisUtils;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisUtils redisUtils) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
        this.redisUtils = redisUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        //获取当前认证成功用户权限信息
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        //如果有权限，就把权限放入到上下文中
        if (!Objects.isNull(authentication)){
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        //从handler中获取token
        String token = request.getHeader(GlobalConstant.TOKEN);
        if (!Objects.isNull(token)){
            String userId = tokenManager.getUserIdFromToken(token);
            if (Objects.isNull(userId)){
                return null;
            }
            Set<String> permissionValueList = redisUtils.get(RedisConstant.getToken(userId));
            if (Objects.isNull(permissionValueList)){ //没权限就初始化空白的
                return new UsernamePasswordAuthenticationToken(userId,token,new ArrayList<>());
            }else {
                Collection<GrantedAuthority> authority = new ArrayList<>();
                for (String permissionValue : permissionValueList) {
                    if(StringUtils.isBlank(permissionValue)) continue;
                    SimpleGrantedAuthority auth = new SimpleGrantedAuthority(permissionValue);
                    authority.add(auth);
                }
                return new UsernamePasswordAuthenticationToken(userId,token,authority);
            }
        }
        return null;
    }
}
