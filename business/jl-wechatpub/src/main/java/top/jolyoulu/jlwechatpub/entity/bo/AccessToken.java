package top.jolyoulu.jlwechatpub.entity.bo;

import lombok.Data;

/**
 * @Author: LZJ
 * @Date: 2020/5/25 19:53
 * @Version 1.0
 */
@Data
public class AccessToken {

    private String access_token;

    private Long expires_in;
}
