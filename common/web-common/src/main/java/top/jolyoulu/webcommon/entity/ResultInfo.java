package top.jolyoulu.webcommon.entity;


import lombok.Data;
import top.jolyoulu.corecommon.constant.GlobalConstant;
import top.jolyoulu.webcommon.enums.GlobalExpType;

import java.util.Objects;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/20 21:32
 * @Description 统一返回对象
 */
@Data
public class ResultInfo<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回内容
     */
    private String msg;

    /**
     * 数据对象
     */
    private T data;

    /**
     * 初始化一个新创建的 ResultInfo 对象，使其表示一个空消息。
     */
    private ResultInfo() {
    }

    /**
     * 初始化一个新创建的 ResultInfo 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    private ResultInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 ResultInfo 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    private ResultInfo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        if (!Objects.isNull(data)) {
            this.data = data;
        }
    }

    /**
     * 初始化一个新创建的 ResultInfo 对象
     *
     * @param globalExpType
     */
    public ResultInfo(GlobalExpType globalExpType) {
        this.code = globalExpType.getCode();
        this.msg = globalExpType.getMessage();
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static<T> ResultInfo<T> success() {
        return ResultInfo.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static<T> ResultInfo<T> success(T data) {
        return ResultInfo.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static<T> ResultInfo<T> success(String msg) {
        return ResultInfo.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static<T> ResultInfo<T> success(String msg, T data) {
        return new ResultInfo<T>(GlobalConstant.HTTP_SUCCESS_CODE, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static<T> ResultInfo<T> error() {
        return ResultInfo.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static<T> ResultInfo<T> error(String msg) {
        return ResultInfo.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static<T> ResultInfo<T> error(String msg, T data) {
        return new ResultInfo<T>(GlobalConstant.HTTP_ERROR_CODE, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static<T> ResultInfo<T> error(int code, String msg) {
        return new ResultInfo<T>(code, msg, null);
    }
}
