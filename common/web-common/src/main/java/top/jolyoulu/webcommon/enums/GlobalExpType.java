package top.jolyoulu.webcommon.enums;

import lombok.Data;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/20 22:43
 * @Description
 */
public enum GlobalExpType {
    UNAUTH_ERROR(1000,"请先登录授权"),
    PERMISSION_DENIED(1002,"权限不足"),
    USERNAME_PASSWORD_ERROR(1003,"用户名或密码错误"),
    UNKNOWN_ERROR(9999,"未知错误")
    ;

    private final Integer code;
    private final String message;

    GlobalExpType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
