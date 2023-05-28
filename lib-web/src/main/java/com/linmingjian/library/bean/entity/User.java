package com.linmingjian.library.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linmingjian.library.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 用户名 */
    private String username;

    /** 用户昵称 */
    private String nickname;

    /** 密码 */
    private String password;
}
