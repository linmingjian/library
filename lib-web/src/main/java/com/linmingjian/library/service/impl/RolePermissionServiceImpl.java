package com.linmingjian.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linmingjian.library.bean.entity.RolePermission;
import com.linmingjian.library.repository.RolePermissionRepository;
import com.linmingjian.library.service.RolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionRepository, RolePermission> implements RolePermissionService {

}
