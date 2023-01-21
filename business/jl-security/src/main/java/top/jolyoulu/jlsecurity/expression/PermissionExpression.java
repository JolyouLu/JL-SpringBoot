package top.jolyoulu.jlsecurity.expression;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 21:05
 * @Description 基于注解实现的权限认证
 */
@Component("ss")
public class PermissionExpression {

    public boolean hasAuthority(String authority){
        //获取用户的权限表
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //判断用户是否有权限
        return authorities.contains(new SimpleGrantedAuthority(authority));
    }

}
