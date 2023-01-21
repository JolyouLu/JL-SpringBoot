package top.jolyoulu.jlservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.jolyoulu.jlservice.entity.po.JlUser;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 18:45
 * @Description
 */
@Mapper
public interface JlUserMapper extends BaseMapper<JlUser> {
}
