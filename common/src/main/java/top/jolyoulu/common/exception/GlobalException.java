package top.jolyoulu.common.exception;

import top.jolyoulu.common.enums.GlobalExpType;

/**
 * @Author: JolyouLu
 * @Date: 2023/1/19 16:16
 * @Version 1.0
 */
public class GlobalException extends RuntimeException{
    //异常枚举
    private GlobalExpType globalExpType;

    private GlobalException() {}

    public GlobalException(GlobalExpType globalExpType) {
        super(globalExpType.getMessage());
        this.globalExpType = globalExpType;
    }

    public GlobalExpType getGlobalExpType() {
        return globalExpType;
    }
}
