package com.linmingjian.library.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linmingjian.library.bean.entity.User;
import com.linmingjian.library.bean.vo.UserVo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends IService<User>, UserDetailsService {
    UserVo getUserById(Long userId);
}
