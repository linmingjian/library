package com.linmingjian.library.config.security;

import com.linmingjian.library.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

/**
 * Spring Security 自定义校验器
 * 使用方法：
 *  lib.check('xxx')
 *
 * @author lin
 * @since 2023-05-27
 */
@Slf4j
@Service(value = "lib")
public class SecurityPermissionConfig {

    public Boolean check(String... permissions) {
        // 获取当前用户的所有权限
        Set<String> currentUserAuthorities = SecurityUtil.getCurrentUserAuthorities();
        log.info("需要权限 {}", Arrays.toString(permissions));
        log.info("用户权限 {}", currentUserAuthorities);
        // 匹配是否存在权限
        return Arrays.stream(permissions).anyMatch(currentUserAuthorities::contains);
    }
}
