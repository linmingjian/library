package com.linmingjian.library.exception.handler;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.linmingjian.library.base.ApiError;
import com.linmingjian.library.base.ApiErrorEnum;
import com.linmingjian.library.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiError> methodArgumentTypeMismatchException(Exception e) {
        log.error(ExceptionUtil.getMessage(e));
        ApiError apiError = ApiError.error(ApiErrorEnum.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> badRequestException(BadRequestException e) {
        log.error(ExceptionUtil.getMessage(e));
        ApiError apiError = ApiError.error(e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String errMsg = fieldError == null ? "数据校验错误" : fieldError.getDefaultMessage();
        ApiError apiError = ApiError.error(errMsg);
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiError> accessDeniedException(AccessDeniedException e) {
        log.error(ExceptionUtil.getMessage(e));
        ApiError apiError = ApiError.error(ApiErrorEnum.FORBIDDEN);
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ApiError> noHandlerFoundException(NoHandlerFoundException e) {
        log.error(ExceptionUtil.getMessage(e), e.getRequestURL());
        ApiError apiError = ApiError.error(ApiErrorEnum.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiError> handleException(Throwable e) {
        log.error(ExceptionUtil.getMessage(e), e);
        ApiError apiError = ApiError.error(ApiErrorEnum.SERVER_ERROR);
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
