package com.linmingjian.library.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 错误请求异常
 *
 * @author lin
 * @since 2023-05-27
 */
@Getter
public class BadRequestException extends RuntimeException {
    private Integer status = HttpStatus.BAD_REQUEST.value();

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(HttpStatus status, String msg) {
        super(msg);
        this.status = status.value();
    }
}
