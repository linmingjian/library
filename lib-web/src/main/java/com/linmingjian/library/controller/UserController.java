package com.linmingjian.library.controller;

import com.linmingjian.library.bean.vo.UserVo;
import com.linmingjian.library.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "系统：用户接口")
@RestController
@RequestMapping("sys")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("查询用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "path", required = true)
    public ResponseEntity<UserVo> getUser(@PathVariable("userId") Long userId) {
        UserVo userVo = userService.getUserById(userId);
        return new ResponseEntity<>(userVo, HttpStatus.OK);
    }
}
