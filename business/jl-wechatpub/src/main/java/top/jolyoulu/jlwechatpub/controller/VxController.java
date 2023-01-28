package top.jolyoulu.jlwechatpub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jolyoulu.jlwechatpub.config.VxParameterConfig;
import top.jolyoulu.jlwechatpub.wechatpub.pipline.RequestContext;
import top.jolyoulu.jlwechatpub.wechatpub.utils.CheckUtil;
import top.jolyoulu.jlwechatpub.wechatpub.utils.MessageUtil;
import top.jolyoulu.jlwechatpub.wechatpub.pipline.AbstractRequestHandlerContextAdapter;
import top.jolyoulu.jlwechatpub.wechatpub.pipline.DefaultRequestPipeline;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: LZJ
 * @Date: 2020/5/24 0:14
 * @Version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/myWeXin")
public class VxController {

    @Autowired
    private VxParameterConfig vxParameterConfig;

    @Autowired
    private DefaultRequestPipeline defaultRequestPipeline;

    /**
     * 给微信服务器校验接口
     */
    @GetMapping("/master")
    @ResponseBody
    public String init(@RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("echostr") String echostr){
        if (CheckUtil.checkSignature(vxParameterConfig.getToken(),signature,timestamp,nonce)){
            return echostr;
        }
        return null;
    }

    /**
     * 公众号收到消息是转发的接口，具体的处理类位于
     * @see top.jolyoulu.jlwechatpub.msghandler
     */
    @PostMapping(value = "/master")
    public void receiver(@RequestBody String xml, HttpServletResponse resp, HttpServletRequest request){
        try {
            Map<String, String> msgMap = MessageUtil.string2Map(xml);
            RequestContext requestContext = new RequestContext(msgMap, resp, request);
            AbstractRequestHandlerContextAdapter ctx = defaultRequestPipeline.getCtx();
            ctx.requestHandle(requestContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
