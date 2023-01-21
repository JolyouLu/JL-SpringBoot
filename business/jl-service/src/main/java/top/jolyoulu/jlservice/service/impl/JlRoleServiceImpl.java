package top.jolyoulu.jlservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.jolyoulu.jlservice.dao.JlRoleMapper;
import top.jolyoulu.jlservice.entity.po.JlRole;
import top.jolyoulu.jlservice.service.JlRoleService;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 20:10
 * @Description
 */
@Slf4j
@Service
public class JlRoleServiceImpl extends ServiceImpl<JlRoleMapper, JlRole> implements JlRoleService {
}
