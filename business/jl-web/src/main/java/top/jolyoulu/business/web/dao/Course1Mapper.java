package top.jolyoulu.business.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.jolyoulu.business.web.entity.Course1;

import java.util.List;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/19 18:10
 * @Version 1.0
 */
@Mapper
public interface Course1Mapper extends BaseMapper<Course1> {

    List<Course1> selectAll();

}
