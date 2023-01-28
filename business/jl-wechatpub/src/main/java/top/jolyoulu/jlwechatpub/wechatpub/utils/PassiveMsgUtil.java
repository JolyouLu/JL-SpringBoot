package top.jolyoulu.jlwechatpub.wechatpub.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsg;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.annotation.PassiveMsgProperty;
import top.jolyoulu.jlwechatpub.wechatpub.passivemsg.entity.BaseMessage;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @Author: JolyouLu
 * @Date: 2021/5/25 11:18
 * @Version 1.0
 */
public enum PassiveMsgUtil {
    INSTANCE;

    /**
     * 获取被动回复消息的xml
     * @param obj
     * @return
     */
    public String getMsgXml(Object obj){
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");
        this.getMsgXml(root,obj);
        return formatXml(document, "utf-8", false);
    }

    private void getMsgXml(Element element, Object obj){
        //获取当前对象
        Class<?> clazz = obj.getClass();
        //获取当前对象的父类对象
        Class<?> supClazz = clazz.getSuperclass();
        //如果父类是BaseMessage
        if (supClazz.getName().equals(BaseMessage.class.getName())){
            //构建父类的属性
            this.getMsgXml(element,obj,supClazz);
        }
//        if (!clazz.isAnnotationPresent(PassiveMsg.class)){
//            throw new IllegalArgumentException("Please use @PassiveMsg to decorate the "+clazz.getName()+" class");
//        }
        //判断当前对象类型
        switch (clazz.getName()){
            //如果是容器对象
            case "java.util.ArrayList":
                //遍历容器
                ArrayList list = (ArrayList) obj;
                for (Object i : list) {
                    this.getMsgXml(element,i,i.getClass());
                }
                break;
            default:
                this.getMsgXml(element,obj,clazz);
                break;
        }

    }

    private void getMsgXml(Element element, Object obj,Class<?> clazz){
        //获取对象中所有属性
        Field[] fields = clazz.getDeclaredFields();
        //遍历所有属性
        for (Field field : fields) {
            //判断数据是否包含PassiveMsgProperty注解没有，抛异常
            if (!field.isAnnotationPresent(PassiveMsgProperty.class)){
                throw new IllegalArgumentException("Please use @PassiveMsgProperty to decorate the "+field.getName()+" property");
            }
            //获取属性中的PassiveMsgProperty对象
            PassiveMsgProperty annotation = field.getAnnotation(PassiveMsgProperty.class);
            //得到该属性设置的参数
            String name = annotation.name();
            boolean addCDATA = annotation.addCDATA();
            Class<?> contentClazz = annotation.contentObj();
            //调用数据的get方法
            Object invoke = invokeGetMethod(clazz, field,obj);

            //如果get空跳过当前循环
            if (invoke == null){
                continue;
            }
            //如果随机性中的contentClazz传入的对象包含了PassiveMsg注解，表示是嵌套对象需要进行迭代生成xml
            if (contentClazz.isAnnotationPresent(PassiveMsg.class)){
                Element e = element.addElement(name);
                this.getMsgXml(e,invoke);
                continue;
            }
            //判断是否需要加<![CDATA[]]>标签
            if (addCDATA){
                element.addElement(name).addCDATA(String.valueOf(invoke));
            }else {
                element.addElement(name).addText(String.valueOf(invoke));
            }

        }
    }

    /**
     * 调用get方法
     * @return 执行结果
     */
    private Object invokeGetMethod(Class<?> clazz,Field field,Object obj){
        Object invoke = null;
        try {
            //将来属性名首字母大写
            String methodName = field.getName().replaceFirst(field.getName().substring(0, 1), field.getName().substring(0, 1).toUpperCase());
            //拼接成get方法
            Method methodGet = clazz.getMethod("get" + methodName);
            //执行get方法
            invoke = methodGet.invoke(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return invoke;
    }

    /**
     * 格式化XML文档
     * @param document xml文档
     * @param charset    字符串的编码
     * @param istrans    是否对属性和元素值进行转移
     * @return 格式化后XML字符串
     */
    private static String formatXml(Document document, String charset, boolean istrans) {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(charset);
        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw, format);
        xw.setEscapeText(istrans);
        try {
            xw.write(document);
            xw.flush();
            xw.close();
        } catch (IOException e) {
            System.out.println("格式化XML文档发生异常，请检查！");
            e.printStackTrace();
        }
        return sw.toString();
    }

}