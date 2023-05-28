package com.linmingjian.library.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linmingjian.library.bean.entity.Permission;

import java.util.Set;

public interface PermissionService extends IService<Permission> {
    Set<String> getPermissionsByUserId(Long userId);
}
