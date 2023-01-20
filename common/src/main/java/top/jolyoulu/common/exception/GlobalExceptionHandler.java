package top.jolyoulu.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import top.jolyoulu.common.constant.GlobalConstant;
import top.jolyoulu.common.enums.GlobalExpType;
import top.jolyoulu.common.utils.ResultInfo;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/20 21:42
 * @Description
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常
     */
    @ExceptionHandler(GlobalException.class)
    public ResultInfo baseException(GlobalException e){
        log.error(e.getMessage(), e);
        GlobalExpType expType = e.getGlobalExpType();
        return ResultInfo.error(expType.getCode(),expType.getMessage());
    }

    /**
     * 其它未知的异常
     */
    @ExceptionHandler(Exception.class)
    public ResultInfo handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultInfo.error(e.getMessage());
    }
}
