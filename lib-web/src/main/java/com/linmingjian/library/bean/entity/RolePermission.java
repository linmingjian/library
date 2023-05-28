package com.linmingjian.library.bean.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linmingjian.library.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_role_permission")
@EqualsAndHashCode(callSuper = true)
public class RolePermission extends BaseEntity {
    /** 主键ID */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 角色ID */
    private Long roleId;

    /** 权限ID */
    private Long permissionId;
}
