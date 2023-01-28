package top.jolyoulu.jlservice.service.jlsecurity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.jolyoulu.jlservice.dao.JlUserRoleMapper;
import top.jolyoulu.jlservice.entity.po.JlUserRole;
import top.jolyoulu.jlservice.service.jlsecurity.JlUserRoleService;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 20:13
 * @Description
 */
@Slf4j
@Service
public class JlUserRoleServiceImpl extends ServiceImpl<JlUserRoleMapper, JlUserRole> implements JlUserRoleService {
}
