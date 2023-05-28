package com.linmingjian.library.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linmingjian.library.bean.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolePermissionRepository extends BaseMapper<RolePermission> {
}
