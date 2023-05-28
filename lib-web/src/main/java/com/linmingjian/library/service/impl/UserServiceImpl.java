package com.linmingjian.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linmingjian.library.bean.dto.UserDetailsDto;
import com.linmingjian.library.bean.entity.User;
import com.linmingjian.library.bean.vo.UserVo;
import com.linmingjian.library.repository.UserRepository;
import com.linmingjian.library.service.PermissionService;
import com.linmingjian.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserService {
    @Autowired
    private PermissionService permissionService;

    @Override
    @Cacheable(value = "user", key = "#userId")
    public UserVo getUserById(Long userId) {
        User user = baseMapper.selectById(userId);
        UserVo userVo;
        if (user != null) {
            userVo = new UserVo()
                    .setId(user.getId())
                    .setNickname(user.getNickname())
                    .setUsername(user.getUsername());
        } else {
            userVo = null;
        }
        return userVo;
    }

    @Override
    @Cacheable(value = "user", key = "'UserDetails' + #username")
    public UserDetailsDto loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (null == user) {
            throw new UsernameNotFoundException("没有找到该用户");
        }

        Set<String> permissionSet = permissionService.getPermissionsByUserId(user.getId());
        Set<SimpleGrantedAuthority> authoritySet = permissionSet
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        return new UserDetailsDto(user, authoritySet);
    }
}
