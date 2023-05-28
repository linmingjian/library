package com.linmingjian.library.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * 错误枚举
 * @author lin
 * @since 2023-05-27
 */
@Getter
public enum ApiErrorEnum {
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "无效请求"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "对不起，您需要先登录"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "对不起，您暂时没权限进行本操作"),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "请求资源不存在"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器开小差了，请稍后再试~");

    private final Integer status;
    private final String errMsg;

    ApiErrorEnum(Integer status, String errMsg) {
        this.status = status;
        this.errMsg = errMsg;
    }
}
