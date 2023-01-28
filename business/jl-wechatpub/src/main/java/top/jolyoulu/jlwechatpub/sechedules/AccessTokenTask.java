package top.jolyoulu.jlwechatpub.sechedules;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.jolyoulu.common.constant.RedisConstant;
import top.jolyoulu.jlwechatpub.entity.bo.AccessToken;
import top.jolyoulu.jlwechatpub.service.AccessTokenService;
import top.jolyoulu.modules.redismodule.utils.RedisUtils;

/**
 * @Author: LZJ
 * @Date: 2020/5/25 20:24
 * @Version 1.0
 */
@Slf4j
@Component
@EnableScheduling
public class AccessTokenTask {

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * access_token 是公众的全局唯一调用凭据
     * access_token 的有效期为 2 个小时，需要定时刷新 access_token，重复获取会导致之前一次获取的失效
     * 延迟一秒执行
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 7000*1000 )
//    @Scheduled(initialDelay = 1000, fixedDelay = 10000 )
    public void getTouTiaoAccessToken(){
        try {
            String json = accessTokenService.getAccessToken();
            AccessToken accessToken = JSONObject.parseObject(json, AccessToken.class);
            String token = accessToken.getAccess_token();
            redisUtils.set(RedisConstant.getVxAccessToken(),token);
            log.info("微信accessToken保存成功:{}",token);
        } catch (Exception e) {
            log.error("获取微信adcessToken保存失败!");
            e.printStackTrace();
        }

    }
}
