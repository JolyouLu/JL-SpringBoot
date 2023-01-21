package top.jolyoulu.common.utils;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JolyouLu
 * @Date: 2022/4/17 0:24
 * @Version 1.0
 */
public class JwtUtils {

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @param singKey 密钥
     * @return 数据声明
     */
    public static Claims decrypt(String token, String singKey)
    {
        return Jwts.parser()
                .setSigningKey(singKey)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @param singKey 密钥
     * @return 令牌
     */
    public static String encrypt(Map<String, Object> claims,String singKey)
    {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, singKey).compact();
        return token;
    }

    public static void main(String[] args) {
        //测试jwt工具类的使用
        Map<String, Object> claims = new HashMap<>();
        claims.put("token", 123);
        String jwt = JwtUtils.encrypt(claims,"jolyoulu");
        System.out.println(jwt);
        Claims token = JwtUtils.decrypt(jwt,"jolyoulu");
        System.out.println(token.get("token"));
    }
}
