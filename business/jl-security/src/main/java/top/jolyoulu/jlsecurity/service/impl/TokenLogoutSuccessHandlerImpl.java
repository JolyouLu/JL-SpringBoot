package top.jolyoulu.jlsecurity.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import top.jolyoulu.common.entity.ResultInfo;
import top.jolyoulu.common.enums.GlobalExpType;
import top.jolyoulu.common.utils.ResponseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 22:21
 * @Description 退出成功后的处理类
 */
public class TokenLogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResponseUtils.outJson(response, ResultInfo.success());
    }
}
