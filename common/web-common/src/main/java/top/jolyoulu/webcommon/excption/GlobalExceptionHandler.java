package top.jolyoulu.webcommon.excption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.jolyoulu.webcommon.entity.ResultInfo;
import top.jolyoulu.webcommon.enums.GlobalExpType;


/**
 * @Author: JolyouLu
 * @Date: 2023/1/20 21:42
 * @Description
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 其它未知的异常
     */
    @ExceptionHandler(Exception.class)
    public ResultInfo handleException(Exception e) throws Exception {
        //处理 SpringSecurity抛出异常但AccessDeniedHandler不生效
        //捕获AccessDeniedException再抛出就可以被AccessDeniedHandler捕获到了
        if (e.getClass().getName().equals("org.springframework.security.access.AccessDeniedException")){
            throw e;
        }
        //全局异常
        log.error(e.getMessage(), e);
        if (e instanceof GlobalException){
            ((GlobalException) e).getGlobalExpType();
            GlobalExpType expType = ((GlobalException) e).getGlobalExpType();
            return ResultInfo.error(expType.getCode(),expType.getMessage());
        }else {
            return ResultInfo.error(e.getMessage());
        }
    }
}
