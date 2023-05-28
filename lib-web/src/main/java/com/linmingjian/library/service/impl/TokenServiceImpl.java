package com.linmingjian.library.service.impl;

import com.linmingjian.library.bean.dto.TokenDto;
import com.linmingjian.library.bean.vo.TokenVo;
import com.linmingjian.library.config.security.SecurityProperties;
import com.linmingjian.library.service.TokenService;
import com.linmingjian.library.service.UserService;
import com.linmingjian.library.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUtil securityUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public TokenVo getToken(TokenDto tokenDto) {
        // 账号
        String username = tokenDto.getUsername();
        String password = tokenDto.getPassword();

        // 登录检测
        UserDetails userDetails = userService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UsernameNotFoundException("用户名或密码不正确！");
        }

        // Security 上下文登录
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 签发Token
        String token = securityUtils.createToken(authenticationToken);
        TokenVo tokenVo = new TokenVo();
        tokenVo.setToken(token);
        return tokenVo;
    }
}
