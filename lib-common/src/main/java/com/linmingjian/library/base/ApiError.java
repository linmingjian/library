package com.linmingjian.library.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;


/**
 * 通用错误
 * @author lin
 * @since 2023-05-27
 */
@Data
public class ApiError {
    @JsonIgnore
    private transient Integer status;
    private String errMsg;

    private ApiError() {
        this.status = HttpStatus.BAD_REQUEST.value();
    }

    public static ApiError error(String message) {
        ApiError apiError = new ApiError();
        apiError.setErrMsg(message);
        return apiError;
    }

    public static ApiError error(Integer status, String message) {
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setErrMsg(message);
        return apiError;
    }

    public static ApiError error(ApiErrorEnum errorEnum) {
        ApiError apiError = new ApiError();
        apiError.setStatus(errorEnum.getStatus());
        apiError.setErrMsg(errorEnum.getErrMsg());
        return apiError;
    }
}
