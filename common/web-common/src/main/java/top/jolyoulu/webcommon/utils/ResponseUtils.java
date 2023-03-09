package top.jolyoulu.webcommon.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import top.jolyoulu.webcommon.entity.ResultInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/21 15:52
 * @Description Response 请求处理类
 */
public class ResponseUtils {

    /**
     * 返回Json结果给前端
     * @param response response
     * @param resultInfo 结果内容
     */
    public static void outJson(HttpServletResponse response, ResultInfo resultInfo) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(JSONObject.toJSONString(resultInfo));
    }
}
