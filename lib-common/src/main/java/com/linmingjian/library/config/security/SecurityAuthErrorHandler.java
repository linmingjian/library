package com.linmingjian.library.config.security;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.linmingjian.library.base.ApiError;
import com.linmingjian.library.base.ApiErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Spring Security未认证处理器
 *
 * @author lin
 * @since 2023-05-27
 */
@Component
public class SecurityAuthErrorHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        ApiError apiError = ApiError.error(ApiErrorEnum.UNAUTHORIZED);
        ServletUtil.write(response, JSONUtil.toJsonStr(apiError), MediaType.APPLICATION_JSON_VALUE);
    }
}
