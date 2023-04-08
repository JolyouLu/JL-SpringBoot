package top.jolyoulu.webcommon.excption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.jolyoulu.webcommon.entity.ResultInfo;
import top.jolyoulu.webcommon.enums.GlobalExpType;

import java.util.List;


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
        if (e.getClass().getName().equals("org.springframework.security.access.AccessDeniedException")) {
            throw e;
        }
        //全局异常
        log.error(e.getMessage(), e);
        if (e instanceof GlobalException) {
            ((GlobalException) e).getGlobalExpType();
            GlobalExpType expType = ((GlobalException) e).getGlobalExpType();
            return ResultInfo.error(expType.getCode(), expType.getMessage());
        } else if (e instanceof HttpMessageNotReadableException) {
            return ResultInfo.error("请求体读取失败");
        } else if (e instanceof MethodArgumentNotValidException) {
            BindingResult result = ((MethodArgumentNotValidException) e).getBindingResult();
            List<FieldError> fieldErrors = result.getFieldErrors();
            if (!fieldErrors.isEmpty()){
                return ResultInfo.error(fieldErrors.get(0).getDefaultMessage());
            }
            return ResultInfo.error(result.toString());
        } else {
            return ResultInfo.error(e.getMessage());
        }
    }
}
