package com.linmingjian.library.bean.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@ApiModel("图书列表")
@Accessors(chain = true)
public class BookListVo implements Serializable {
    /** 图书ID */
    @ApiModelProperty("图书ID")
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
}
