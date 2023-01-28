package top.jolyoulu.jlwechatpub.wechatpub.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author: JolyouLu
 * @Date: 2021/4/30 15:14
 * @Version 1.0
 * Http请求工具类
 */
@Slf4j
public class HttpUtil {

    private final static int SUCCESS = 200;


    /**
     * 向指定 URL 发送GET方法的请求
     * @param url 发送请求的 URL https://xxx
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param){
        return sendGet(url+"?"+param);
    }

    /**
     * 向指定 URL 发送GET方法的请求
     * @param url url 发送请求的 URL https://xxx?name1=value1&name2=value2
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url){
        String jsonString = "";
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet get = new HttpGet(url);

            log.debug("==>    getUrl: " + url);
            CloseableHttpResponse response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();

            if (SUCCESS == statusCode){
                HttpEntity entity = response.getEntity();
                jsonString = EntityUtils.toString(entity);
                log.debug("<== getResult: " + jsonString);
            }else {
                log.error("<== getResult: " + response);
            }

        } catch (IOException e) {
            log.error("调用HttpUtils.sendGet IOException, getUrl: " + url);
        }
        return jsonString;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url url 发送请求的 URL https://xxx
     * @param body Json格式请求体
     * @return 所代表远程资源的响应结果
     */
    public static String sendJsonPost(String url,String body){
        String jsonString = "";
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            log.debug("==>    postUrl: " + url);
            post.addHeader("Content-Type","application/json");
            StringEntity reqEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
            post.setEntity(reqEntity);
            CloseableHttpResponse response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS == statusCode){
                HttpEntity entity = response.getEntity();
                jsonString = EntityUtils.toString(entity);
                log.debug("<== postResult: " + jsonString);
            }else {
                log.error("<== postResult: " + response);
            }
        }catch (IOException e) {
            log.error("调用HttpUtils.sendPost IOException, postUrl: " + url);
        }
        return jsonString;
    }
}
