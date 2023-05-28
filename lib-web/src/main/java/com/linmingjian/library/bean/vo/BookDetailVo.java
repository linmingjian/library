package com.linmingjian.library.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("图书详情")
@Accessors(chain = true)
public class BookDetailVo implements Serializable {
    /** 书ID */
    @ApiModelProperty("书ID")
    private Long id;

    /** 书名 */
    @ApiModelProperty("书名")
    private String name;

    /** 作者 */
    @ApiModelProperty("作者")
    private String author;

    /** 描述 */
    @ApiModelProperty("描述")
    private String description;

    /** 单价 */
    @ApiModelProperty("单价")
    private BigDecimal price;

    /** 条码 */
    @ApiModelProperty("条码")
    private String code;
}
