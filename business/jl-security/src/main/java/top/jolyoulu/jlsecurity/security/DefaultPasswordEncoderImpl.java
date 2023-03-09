package top.jolyoulu.jlsecurity.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import top.jolyoulu.corecommon.utils.MD5Utils;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 15:14
 * @Description 默认的密码处理器，用于对密码md5加密处理与比较密码
 */
@Component
public class DefaultPasswordEncoderImpl implements PasswordEncoder {

    public DefaultPasswordEncoderImpl() {
        this(-1);
    }

    public DefaultPasswordEncoderImpl(int strength) {

    }

    /**
     * 对前端发来的密码进行加密
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Utils.encrypt(rawPassword.toString());
    }

    /**
     * rawPassword 未加密的密码(前端给的)
     * encodedPassword 加密后的密码(经过encode方法的)
     * 比较2个密码是否相等
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Utils.encrypt(rawPassword.toString()));
    }
}
