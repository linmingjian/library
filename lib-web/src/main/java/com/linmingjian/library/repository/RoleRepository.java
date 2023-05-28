package com.linmingjian.library.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linmingjian.library.bean.entity.Role;
import com.linmingjian.library.bean.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleRepository extends BaseMapper<Role> {
}
