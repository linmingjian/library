package com.linmingjian.library.config.security;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.linmingjian.library.base.ApiError;
import com.linmingjian.library.base.ApiErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Spring Security权限拒绝处理器
 *
 * @author lin
 * @since 2023-05-27
 */
@Component
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        ApiError apiError = ApiError.error(ApiErrorEnum.FORBIDDEN);
        ServletUtil.write(response, JSONUtil.toJsonStr(apiError), MediaType.APPLICATION_JSON_VALUE);
    }
}
