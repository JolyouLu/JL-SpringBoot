package top.jolyoulu.modules.mybatisplus.module.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/19 18:25
 * @Description 默认的自动填充实现
 */
@Slf4j
@Component
public class DefaultMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject,"creatTime",LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject,"updateTime",LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject,"updateTime",LocalDateTime.class, LocalDateTime.now());
    }

}
