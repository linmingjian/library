package com.linmingjian.library.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.linmingjian.library.config.security.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SecurityUtil {
    @Autowired
    private SecurityProperties securityProperties;

    private JWT jwt;

    @PostConstruct
    public void init() {
        JWTSigner signer = JWTSignerUtil.hs256(securityProperties.getTokenKey().getBytes());
        jwt = JWT.create().setSigner(signer);
    }

    public String createToken(Authentication authentication) {
        DateTime expireTime = DateUtil.offsetSecond(new Date(), securityProperties.getTokenValidityInSeconds());
        return jwt.setExpiresAt(expireTime)
                .setJWTId(IdUtil.simpleUUID())
                .setSubject(authentication.getName())
                .sign();
    }

    public boolean validateToken(String token) {
        try {
            return jwt.parse(token).verify();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    public Date getExpireAt(String token) {
        return jwt.parse(token).getPayloads().getDate(JWT.EXPIRES_AT);
    }

    public boolean isTokenExpired(String token) {
        Date expireDate = getExpireAt(token);
        return expireDate.before(new Date());
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            username = Convert.toStr(jwt.parse(token).getPayload(JWT.SUBJECT));
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public static Set<String> getCurrentUserAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .stream()
                .map((Function<GrantedAuthority, String>) GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return StrUtil.equals(username, userDetails.getUsername()) && !isTokenExpired(token);
    }
}
