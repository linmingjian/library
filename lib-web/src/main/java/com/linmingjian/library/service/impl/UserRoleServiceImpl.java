package com.linmingjian.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linmingjian.library.bean.entity.Role;
import com.linmingjian.library.bean.entity.UserRole;
import com.linmingjian.library.repository.RoleRepository;
import com.linmingjian.library.repository.UserRoleRepository;
import com.linmingjian.library.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleRepository, UserRole> implements UserRoleService {
}
