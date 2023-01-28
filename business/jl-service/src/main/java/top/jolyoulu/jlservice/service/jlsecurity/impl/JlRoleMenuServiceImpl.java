package top.jolyoulu.jlservice.service.jlsecurity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.jolyoulu.jlservice.dao.JlRoleMenuMapper;
import top.jolyoulu.jlservice.entity.po.JlRoleMenu;
import top.jolyoulu.jlservice.service.jlsecurity.JlRoleMenuService;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 20:11
 * @Description
 */
@Slf4j
@Service
public class JlRoleMenuServiceImpl extends ServiceImpl<JlRoleMenuMapper, JlRoleMenu> implements JlRoleMenuService {
}
