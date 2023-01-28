package top.jolyoulu.jlwechatpub.wechatpub.pipline;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/20 11:10
 * @Version 1.0
 * 处理内容上下文
 */
public class RequestContext {

    private Map<String, String> msg;
    private HttpServletResponse response;
    private HttpServletRequest request;

    /**
     * 回复客户端消息
     * @param msg 消息内容
     */
    public void resultMsg(String msg){
        try {
            this.response.setContentType("text/xml;charset=UTF-8");
            this.response.setCharacterEncoding("UTF-8");
            this.response.getWriter().write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RequestContext(Map<String, String> msg, HttpServletResponse response, HttpServletRequest request) {
        this.msg = msg;
        this.response = response;
        this.request = request;
    }

    public Map<String, String> getMsg() {
        return msg;
    }
}
