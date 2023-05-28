package com.linmingjian.library.controller;

import com.linmingjian.library.bean.dto.TokenDto;
import com.linmingjian.library.bean.vo.TokenVo;
import com.linmingjian.library.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("auth")
@Api(tags = "系统：授权接口")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @ApiOperation("获取凭证")
    @PostMapping(value = "token" , produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenVo> getToken(@Validated @RequestBody @ApiParam("凭证参数") TokenDto tokenDto) {
        TokenVo token = tokenService.getToken(tokenDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @ApiOperation("删除凭证")
    @DeleteMapping(value = "token")
    public ResponseEntity<Void> deleteToken() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
