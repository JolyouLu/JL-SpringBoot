package top.jolyoulu.jlsecurity.security;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import top.jolyoulu.corecommon.constant.GlobalConstant;
import top.jolyoulu.corecommon.utils.JwtUtils;
import top.jolyoulu.jlservice.entity.po.JlUser;

import java.util.HashMap;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 15:26
 * @Description token管理器，生成token信息
 */
@Component
public class TokenManager {

    //根据用户名生成token
    public String creatToken(JlUser user){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("username",user.getUsername());
        return JwtUtils.encrypt(map, GlobalConstant.TOKEN_SING_KEY);
    }

    //根据token获取用户信息
    public String getUserIdFromToken(String token){
        Claims claims = JwtUtils.decrypt(token, GlobalConstant.TOKEN_SING_KEY);
        return claims.get("id",String.class);
    }

    //输出token
    public void removeToken(String token){

    }
}
