package top.jolyoulu.business.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.jolyoulu.business.web.dao.Course1Mapper;
import top.jolyoulu.business.web.entity.Course1;
import top.jolyoulu.modules.apiversionmodule.apiverion.ApiVersion;
import top.jolyoulu.modules.redismodule.utils.RedisUtils;
import top.jolyoulu.webcommon.enums.GlobalExpType;
import top.jolyoulu.webcommon.excption.GlobalException;

import java.util.Random;
import java.util.UUID;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/19 14:41
 * @Version 1.0
 */
@Api(tags = "TestController接口文档")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private Course1Mapper course1Mapper;

    //测试druid连接池功能
    @GetMapping("/druid")
    @ResponseBody
    public Object druid() {
        return jdbcTemplate.queryForList("SELECT * FROM course_1");
    }

    //测试redis读写数据功能
    @GetMapping("/redis")
    @ResponseBody
    public Object redis() {
        String key = UUID.randomUUID().toString();
        redisUtils.set(key,key);
        return redisUtils.get(key);
    }

    //测试mybatis功能
    @GetMapping("/mybatis")
    @ResponseBody
    public Object mybatis() {
        //测试xml是否能扫描到
        course1Mapper.selectAll();
        String uuid = UUID.randomUUID().toString();
        Course1 course1 = new Course1();
        course1.setUserId(new Random().nextLong());
        course1.setCname(uuid);
        course1.setCstatus(uuid);
        course1Mapper.insert(course1);
        return course1Mapper.selectOne(new LambdaQueryWrapper<Course1>().eq(Course1::getCname,uuid));
    }

    //测试mybatis 分页功能
    @GetMapping("/mybatis/page")
    @ResponseBody
    public Object mybatisPage() {
        String uuid = UUID.randomUUID().toString();
        Page<Course1> page = new Page<>();
        Page<Course1> page1 = course1Mapper.selectPage(page, null);
        return page1;
    }

    //测试接口版本功能
    @GetMapping("/apiversion")
    public String apiversion(){
        return "v1.0.0";
    }

    @ApiVersion("1.1.0")
    @GetMapping("/apiversion")
    public String apiversion1_1_0(){
        return "v1.1.0";
    }

    @ApiVersion("1.2.0")
    @GetMapping("/apiversion")
    public String apiversion1_2_0(){
        return "v1.2.0";
    }

    //测试接口文档
    @ApiOperation(value = "测试swagger文档")
    @GetMapping("/swagger")
    public void swagger(){

    }

    //测试自定义异常
    @GetMapping("/myExp")
    public void myExp(){
        throw new GlobalException(GlobalExpType.UNKNOWN_ERROR);
    }
}
