package com.linmingjian.library.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linmingjian.library.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_permission")
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 权限名 */
    private String name;

    /** 权限描述 */
    private String description;
}
