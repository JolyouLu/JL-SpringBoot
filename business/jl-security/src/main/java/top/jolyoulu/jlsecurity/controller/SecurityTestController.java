package top.jolyoulu.jlsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.jolyoulu.common.entity.ResultInfo;

import java.util.Collection;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 20:54
 * @Description
 */
@RestController
@RequestMapping("/security")
public class SecurityTestController {

    @GetMapping("/test")
    public ResultInfo test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            authority.getAuthority();
        }
        System.out.println(authorities);
        return ResultInfo.success();
    }

    @PreAuthorize("@ss.hasAuthority('teacher.lis1t')")
    @GetMapping("/hasAuthority")
    public ResultInfo hasAuthority() {
        return ResultInfo.success();
    }

}
