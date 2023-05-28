package com.linmingjian.library.service;

import com.linmingjian.library.bean.dto.TokenDto;
import com.linmingjian.library.bean.vo.TokenVo;

public interface TokenService {
    TokenVo getToken(TokenDto tokenDto);
}
