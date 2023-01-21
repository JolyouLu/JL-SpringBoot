package top.jolyoulu.jlservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jolyoulu.jlservice.dao.JlMenuMapper;
import top.jolyoulu.jlservice.entity.po.JlMenu;
import top.jolyoulu.jlservice.service.JlMenuService;

import java.util.Set;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 20:08
 * @Description
 */
@Slf4j
@Service
public class JlMenuServiceImpl extends ServiceImpl<JlMenuMapper, JlMenu> implements JlMenuService {

    @Autowired
    private JlMenuMapper jlMenuMapper;

    @Override
    public Set<String> selectPermissionByUserId(String userId) {
        return jlMenuMapper.selectPermissionByUserId(userId);
    }
}
