package top.jolyoulu.common.enums;

import lombok.Data;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/20 22:43
 * @Description
 */
public enum GlobalExpType {
    UNKNOWN_ERROR(999,"未知错误");

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
