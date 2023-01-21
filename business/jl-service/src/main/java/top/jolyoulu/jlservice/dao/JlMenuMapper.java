package top.jolyoulu.jlservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jolyoulu.jlservice.entity.po.JlMenu;

import java.util.Set;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 20:06
 * @Description
 */
@Mapper
public interface JlMenuMapper extends BaseMapper<JlMenu> {

    /**
     * 活跃用户权限
     * @param userId
     * @return
     */
    Set<String> selectPermissionByUserId(@Param("userId") String userId);

}
