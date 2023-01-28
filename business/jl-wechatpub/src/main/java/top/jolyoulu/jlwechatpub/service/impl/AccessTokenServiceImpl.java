package top.jolyoulu.jlwechatpub.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.jolyoulu.jlwechatpub.config.VxParameterConfig;
import top.jolyoulu.jlwechatpub.service.AccessTokenService;
import top.jolyoulu.jlwechatpub.wechatpub.utils.HttpUtil;

/**
 * @Author: LZJ
 * @Date: 2020/5/25 20:27
 * @Version 1.0
 */
@Slf4j
@Service
@Transactional
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    private VxParameterConfig vxParameterConfig;

    @Override
    public String getAccessToken() {
        String url = vxParameterConfig.getAccessTokenUrl();
        String appId = vxParameterConfig.getAppId();
        String appSecret = vxParameterConfig.getAppSecret();
        url = url.replaceAll("APPID",appId);
        url = url.replaceAll("APPSECRET",appSecret);
        return HttpUtil.sendGet(url);
    }
}
