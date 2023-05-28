package com.linmingjian.library.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linmingjian.library.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@TableName("biz_book")
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {
    /** 主键ID */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 书名 */
    private String name;

    /** 作者 */
    private String author;

    /** 条形码 */
    private String code;

    /** 单价 */
    private BigDecimal price;

    /** 描述 */
    private String description;
}
