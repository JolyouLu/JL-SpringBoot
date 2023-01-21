package top.jolyoulu.jlsecurity.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.jolyoulu.common.enums.GlobalExpType;
import top.jolyoulu.common.exception.GlobalException;
import top.jolyoulu.jlsecurity.entity.bo.SecurityUser;
import top.jolyoulu.jlservice.entity.po.JlUser;
import top.jolyoulu.jlservice.service.JlMenuService;
import top.jolyoulu.jlservice.service.JlUserService;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/16 23:01
 * @Version 1.0
 * 一登录后就会触发该loadUserByUsername方法
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JlUserService jlUserService;

    @Autowired
    private JlMenuService jlMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        JlUser user = jlUserService.getByUsername(username);
        if(Objects.isNull(user)){
            throw new GlobalException(GlobalExpType.USERNAME_PASSWORD_ERROR);
        }
        //查询用户权限，假数据正常情况应该去数据库获取
        Set<String> permissions = jlMenuService.selectPermissionByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser();
        securityUser.setCurrentUserInfo(user);
        securityUser.setPermissionValueList(permissions);
        return securityUser;
    }
}
