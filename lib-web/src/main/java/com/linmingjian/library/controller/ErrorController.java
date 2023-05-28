package com.linmingjian.library.controller;

import com.linmingjian.library.base.ApiErrorEnum;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用Error错误处理，捕获404等错误
 *
 * @author lin
 * @since 2023-05-27
 */
@Controller
public class ErrorController extends BasicErrorController {
    public ErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        HashMap<String, Object> result = new HashMap<>();
        if (status == HttpStatus.NOT_FOUND) {
            result.put("errMsg", ApiErrorEnum.NOT_FOUND.getErrMsg());
        } else {
            result.put("errMsg", "未知异常，请稍后重试~");
        }
        return new ResponseEntity<>(result, status);
    }
}
