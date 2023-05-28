package com.linmingjian.library.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linmingjian.library.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /** 角色名 */
    private String name;

    /** 角色描述 */
    private String description;
}
