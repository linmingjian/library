package com.linmingjian.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linmingjian.library.bean.entity.Permission;
import com.linmingjian.library.bean.entity.RolePermission;
import com.linmingjian.library.bean.entity.UserRole;
import com.linmingjian.library.repository.PermissionRepository;
import com.linmingjian.library.service.PermissionService;
import com.linmingjian.library.service.RolePermissionService;
import com.linmingjian.library.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionRepository, Permission> implements PermissionService {
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService roleMenuService;

    @Override
    @Cacheable(value = "permission", key = "#userId")
    public Set<String> getPermissionsByUserId(Long userId) {
        List<UserRole> userRoleList = userRoleService.list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, userId));
        Set<Long> permissionIdSet = userRoleList
                .stream()
                .flatMap(userRole -> roleMenuService.list(new LambdaQueryWrapper<RolePermission>()
                                .eq(RolePermission::getRoleId, userRole.getRoleId()))
                        .stream())
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toSet());
        return baseMapper.selectList(new LambdaQueryWrapper<Permission>().in(Permission::getId, permissionIdSet))
                .stream().map(Permission::getName).collect(Collectors.toSet());
    }
}
