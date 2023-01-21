package top.jolyoulu.jlsecurity.service.impl;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import top.jolyoulu.common.entity.ResultInfo;
import top.jolyoulu.common.enums.GlobalExpType;
import top.jolyoulu.common.utils.ResponseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 15:50
 * @Description 认证失败的处理类
 */
public class UnAuthEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtils.outJson(response, new ResultInfo(GlobalExpType.UNAUTH_ERROR));
    }
}
