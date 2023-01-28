package top.jolyoulu.jlwechatpub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: LZJ
 * @Date: 2020/5/24 0:56
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "vxparameter")
@PropertySource(value = "classpath:vx.yml")
public class VxParameterConfig {

    private String token;

    private String appId;

    private String appSecret;

    private String accessTokenUrl;

    private String menuUrl;

    private String postMediaUrl;

    private String getMediaUrl;

    private String postTemplateUrl;

    public String getToken() {
        return token;
    }

    @Value("${token}")
    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    @Value("${appId}")
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    @Value("${appSecret}")
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    @Value("${accessTokenUrl}")
    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    @Value("${menuUrl}")
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getPostMediaUrl() {
        return postMediaUrl;
    }

    @Value("${postMediaUrl}")
    public void setPostMediaUrl(String postMediaUrl) {
        this.postMediaUrl = postMediaUrl;
    }

    public String getGetMediaUrl() {
        return getMediaUrl;
    }

    @Value("${getMediaUrl}")
    public void setGetMediaUrl(String getMediaUrl) {
        this.getMediaUrl = getMediaUrl;
    }

    public String getPostTemplateUrl() {
        return postTemplateUrl;
    }

    @Value("${postTemplateUrl}")
    public void setPostTemplateUrl(String postTemplateUrl) {
        this.postTemplateUrl = postTemplateUrl;
    }
}
