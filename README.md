# 项目介绍
> 该项目基础是SpringBoot空框架，框架中实现了很多功能都是基于模块化的方式实现的，如果需要启动指定功能只需在依赖中添加指定模块即可

## 快速上手


## 业务模块介绍

> 业务模块中包含着一些实例程序，可帮助使用者快速找到合适自己的目标进行开发，不需要的模块可以删除掉，最后只需要在`bootstrap模块`的`pom.xml`引入所需要的业务模块即可启动项目

| 模块名称        | 功能                                | 说明                                                         |
|-------------|-----------------------------------| ------------------------------------------------------------ |
| jl-service  | 存放公共的model、service、dao            |                                                          |
| jl-security | 依赖jl-service，实现了springSecurity的模板 |                                                            |
| jl-web      | 依赖jl-service，用于测试集成模块中的功能         |                                                            |


## 集成模块介绍

> 集成模块中包含着一些第三方的基础配置，根据自己创建的业务模块按需引入极了

| 模块名称           | 功能                                                         | 说明                                                         |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| apiversion-module  | 该模块可实现编写相同的url通过请求请求头api-version来映射到相应的url接口上 | 该模块继承了`WebMvcConfigurationSupport`启用该模块时需考虑兼容性 |
| druid-module       | druid连接池模块                                              |                                                              |
| logback-module     | logback日志打印模块，以及日志输出到文件模块                  | 默认日志文件分为4个：INFO、自定义异常、其它异常、慢SQL(druid下有效) |
| mybatisplus-module | mybatisplus模块，已引入分页组件、字段自动填充                |                                                              |
| redis-module       | redis模块，已实现redis序列化                                 |                                                              |
| swagger-module     | swagger，实现swagger的集成                                   |                                                              |

### apiversion-module

> 简介：可编写相同的url的版本控制，多用于移动端开发
>
> 作用：通过`@ApiVersion`进行标记，请求在调用请求时通过在请求头中添加api-version来映射要相应版本的url

**例**

对Mapping相同的接口使用`@ApiVersion(x.x.x)`进行标记版本号，未被`@ApiVersion`标记默认使用默认版本1.0.0

~~~java
@RestController
@RequestMapping("/test")
public class TestController {
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
}
~~~

> 注意
>
> 1. apiversion-module模块中`ApiVersionConfig`类，继承了WebMvcConfigurationSupport会导致SpringBoot自带的MVC配置丢失，若在开启该模块后又需要自定义MVC的东西需要注意

### druid-module

> 简介：阿里的druid连接池
>
> 作用：druid基础配置，这个就不多解释了

**例**

当然这个只是单纯测试连接池，正常情况是配合mybatis一起使用的，后面会说到

~~~java
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/druid")
    @ResponseBody
    public Object druid() {
        return jdbcTemplate.queryForList("SELECT * FROM course_1");
    }
}
~~~

> 注意
>
> 1. druid连接池的配置都通过java代码方式实现了，目前提供了简单配置若需要复杂配置请到`DruidDataSourceConfig`类添加
> 2. druid的监控模板默认是开启，访问`localhost:端口/druid/index.html`即可，默认账号admin、密码123456，请到`DruidDataSourceConfig#statViewServlet`下修改



### logback-module

> 简介：基于logback的 日志打印，文本文件输出
>
> 作用：logback基础配置，普通异常、系统异常、慢sql日志的输出

**例**

启用后通过修改yml下的`logging.file.path`指定日志文件输出位置

~~~yml
logging:
  config: classpath:logback.xml
  level:
    top.jolyoulu: debug
  file:
    path: C://Users//mi//Downloads//${spring.application.name}
~~~

| 文件名称                    | 内容                                                         |
| --------------------------- | ------------------------------------------------------------ |
| info.current.log            | 应用的info日志                                               |
| error.global.current.log    | 与`GlobalException`相关的日志，常为预料之中的错误            |
| error.framework.current.log | (重点关注)非`GlobalException`，预料之外错误一般空指针、sql语法错误等 |
| error.lowsql.current.log    | (重点关注)配置Druid后才有内容，里面会打印查询较慢的sql对优化有帮助 |

> 注意
>
> 1. error.lowsql.current.log中的慢sql评判标准通过`DruidDataSourceConfig#statFilter`中的`setSlowSqlMillis`设置

### mybatisplus-module

> 简介：mybatisplus 
>
> 作用：mybatisplus 基础配置，自动填充、分页插件

**例**

~~~java
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private Course1Mapper course1Mapper;

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
}
~~~



### redis-module

> 简介：redis
>
> 作用：redis基础配置，序列化配置、redis工具类

**例**

~~~java
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/redis")
    @ResponseBody
    public Object redis() {
        String key = UUID.randomUUID().toString();
        redisUtils.set(key,key);
        return redisUtils.get(key);
    }

}
~~~

### swagger-module

> 简介：swagger
>
> 作用：swagger基础配置

**常用注解及说明**

| 注解               | 说明                                 |
| ------------------ | ------------------------------------ |
| @Api               | 修饰整个类，描述Controller的作用     |
| @ApiOperation      | 描述一个类的一个方法，或者说一个接口 |
| @ApiParam          | 单个参数描述                         |
| @ApiModel          | 用对象来接收参数                     |
| @ApiProperty       | 用对象接收参数时，描述对象的一个字段 |
| @ApiResponse       | HTTP响应其中1个描述                  |
| @ApiResponses      | HTTP响应整体描述                     |
| @ApiIgnore         | 使用该注解忽略这个API                |
| @ApiError          | 发生错误返回的信息                   |
| @ApiImplicitParam  | 一个请求参数                         |
| @ApiImplicitParams | 多个请求参数                         |

**例**

使用相关注解后，通过`localhost:端口/swagger-ui/index.html#/`即可看到文档

~~~java
@Api(tags = "TestController接口文档")
@RestController
@RequestMapping("/test")
public class TestController {
    
    @ApiOperation(value = "测试swagger文档")
    @GetMapping("/swagger")
    public void swagger(){

    }
}
~~~

