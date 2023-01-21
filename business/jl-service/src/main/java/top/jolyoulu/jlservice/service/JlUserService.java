package top.jolyoulu.jlservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.jolyoulu.jlservice.entity.po.JlUser;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 20:12
 * @Description
 */
public interface JlUserService extends IService<JlUser> {

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return
     */
    JlUser getByUsername(String username);

}
