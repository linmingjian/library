package com.linmingjian.library.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


/**
 * 数据库实体基类
 * @author lin
 * @since 2023-05-27
 */
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    private Date createTime;
    private Date updateTime;
}
