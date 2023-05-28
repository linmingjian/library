package com.linmingjian.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linmingjian.library.bean.entity.Role;
import com.linmingjian.library.repository.RoleRepository;
import com.linmingjian.library.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleRepository, Role> implements RoleService {

}
