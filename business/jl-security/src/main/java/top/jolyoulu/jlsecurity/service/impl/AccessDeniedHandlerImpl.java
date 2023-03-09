package top.jolyoulu.jlsecurity.service.impl;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import top.jolyoulu.webcommon.entity.ResultInfo;
import top.jolyoulu.webcommon.enums.GlobalExpType;
import top.jolyoulu.webcommon.utils.ResponseUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 21:20
 * @Description 权限不足的处理类
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtils.outJson(response, new ResultInfo(GlobalExpType.PERMISSION_DENIED));
    }
}
