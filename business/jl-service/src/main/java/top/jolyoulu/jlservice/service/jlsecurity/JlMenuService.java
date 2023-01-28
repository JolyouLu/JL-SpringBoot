package top.jolyoulu.jlservice.service.jlsecurity;


import com.baomidou.mybatisplus.extension.service.IService;
import top.jolyoulu.jlservice.entity.po.JlMenu;

import java.util.Set;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 20:07
 * @Description
 */
public interface JlMenuService extends IService<JlMenu> {

    /**
     * 根据用户id获取用户权限信息
     * @param userId
     * @return
     */
    Set<String> selectPermissionByUserId(String userId);

}
