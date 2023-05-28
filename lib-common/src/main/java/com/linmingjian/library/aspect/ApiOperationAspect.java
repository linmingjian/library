package com.linmingjian.library.aspect;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linmingjian.library.util.RequestUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面
 * @author lin
 * @since 2023-05-27
 */
@Slf4j
@Aspect
@Component
public class ApiOperationAspect {
    private final ThreadLocal<Long> currentTime = new ThreadLocal<>();

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void pointcut() {
    }

    @SuppressWarnings("all")
    @Around("pointcut()")
    public Object apiOperationAround(ProceedingJoinPoint joinPoint) throws Throwable {
        currentTime.set(System.currentTimeMillis());

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);

        HttpServletRequest request = RequestUtil.getHttpServletRequest();
        String requestURI = request.getRequestURI();
        String requestIp = RequestUtil.getIp(request);
        String requestParameter = RequestUtil.getParameter(method, joinPoint.getArgs());

        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        String methodDesc = annotation.value();

        log.info("==========================================");
        // log.info("请求IP：{}", requestIp);
        log.info("地址：{}", requestURI);
        log.info("参数：{}", requestParameter);
        log.info("方法：{}", methodName);
        log.info("描述：{}", methodDesc);

        Object proceed = joinPoint.proceed();
        Long duration = System.currentTimeMillis() - currentTime.get();
        if (proceed instanceof ResponseEntity) {
            ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) proceed;
            log.info("响应：{}", new ObjectMapper().writeValueAsString(responseEntity.getBody()));
        }

        log.info("耗时：{} ms", duration);
        log.info("==========================================");
        currentTime.remove();
        return proceed;
    }

    /**
     *
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void apiOperationAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Long duration = System.currentTimeMillis() - currentTime.get();
        log.error("错误信息：{}", ExceptionUtil.getMessage(e));
        log.info("耗时：{} ms", duration);
        log.info("==========================================");
        currentTime.remove();
    }
}
