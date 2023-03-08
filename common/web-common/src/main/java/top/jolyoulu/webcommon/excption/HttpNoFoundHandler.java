package top.jolyoulu.webcommon.excption;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.jolyoulu.corecommon.constant.GlobalConstant;
import top.jolyoulu.webcommon.entity.ResultInfo;


import javax.servlet.http.HttpServletRequest;


/**
 * @Author: JolyouLu
 * @Date: 2023/1/20 22:09
 * @Description 404统一返回json格式
 */
@RestController
public class HttpNoFoundHandler implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public ResultInfo handleError(HttpServletRequest request) {
        return ResultInfo.error(GlobalConstant.HTTP_NOT_FOUND, "路径不存在，请检查路径是否正确");
    }

}
