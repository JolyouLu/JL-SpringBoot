package top.jolyoulu.jlsecurity.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import top.jolyoulu.common.constant.GlobalConstant;
import top.jolyoulu.modules.redismodule.utils.RedisUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 15:40
 * @Description 退出登录处理器
 */
public class TokenLogoutHandler implements LogoutHandler {

    private TokenManager tokenManager;
    private RedisUtils redisUtils;

    public TokenLogoutHandler(TokenManager tokenManager, RedisUtils redisUtils) {
        this.tokenManager = tokenManager;
        this.redisUtils = redisUtils;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //从header里获取token
        String token = request.getHeader(GlobalConstant.TOKEN);
        if (!Objects.isNull(token)){
            //移除
            tokenManager.removeToken(token);
            //从token获取用户名
            String userInfo = tokenManager.getUserIdFromToken(token);
            redisUtils.del(userInfo);
        }
    }
}
