package com.linmingjian.library.config.security;

import cn.hutool.core.util.StrUtil;
import com.linmingjian.library.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring Security Token过滤器
 *
 * @author lin
 * @since 2023-05-27
 */
@Slf4j
public class SecurityTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 请求头获取Token信息
        String authHeader = request.getHeader(securityProperties.getTokenHeader());
        // 存在Token
        if (StrUtil.startWith(authHeader, securityProperties.getTokenStartWith())) {
            // 去除 Bear 前缀
            String token = StrUtil.trim(StrUtil.removePrefix(authHeader, securityProperties.getTokenStartWith()));
            // 从Token获取用户名
            String username = securityUtil.getUsernameFromToken(token);
            if (StrUtil.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 验证token是否有效
                if (securityUtil.validateToken(token, userDetails)) {
                    // 重新写入Security上下文
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
