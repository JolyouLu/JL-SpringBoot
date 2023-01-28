package top.jolyoulu.jlservice.service.jlsecurity.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jolyoulu.jlservice.dao.JlUserMapper;
import top.jolyoulu.jlservice.entity.po.JlUser;
import top.jolyoulu.jlservice.service.jlsecurity.JlUserService;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 20:12
 * @Description
 */
@Slf4j
@Service
public class JlUserServiceImpl extends ServiceImpl<JlUserMapper, JlUser> implements JlUserService {

    @Autowired
    private JlUserMapper jlUserMapper;

    @Override
    public JlUser getByUsername(String username) {
        LambdaQueryWrapper<JlUser> ew = new LambdaQueryWrapper<>();
        ew.eq(JlUser::getUsername,username);
        return jlUserMapper.selectOne(ew);
    }

}
