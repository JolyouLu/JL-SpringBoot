package top.jolyoulu.webcommon.utils;

import com.alibaba.fastjson2.JSONObject;
import top.jolyoulu.webcommon.entity.ResultInfo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        try(PrintWriter writer = response.getWriter()) {
            writer.println(JSONObject.toJSONString(resultInfo));
        }
    }

    /**
     * 返回二进制流结果给前端
     * @param response response
     * @param stream 流
     */
    public static void outStream(HttpServletResponse response, byte[] stream) throws IOException {
        response.setStatus(200);
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        try(ServletOutputStream os = response.getOutputStream()) {
            os.write(stream);
        }
    }
}
