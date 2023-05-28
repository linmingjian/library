package com.linmingjian.library.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ApiModel("图书")
public class BookDto {

    @NotBlank(message = "书名不能为空")
    @ApiModelProperty(value = "书名", example = "边城")
    private String name;

    @NotBlank(message = "作者不能为空")
    @ApiModelProperty(value = "作者", example = "沈从文")
    private String author;

    @NotBlank(message = "描述不能为空")
    @ApiModelProperty(value = "描述", example = "文学书籍")
    private String description;

    @NotNull(message = "单价不能为空")
    @ApiModelProperty(value = "单价", example = "30")
    private BigDecimal price;

    @NotBlank(message = "条码不能为空")
    @ApiModelProperty(value = "条码", example = "9230492303")
    private String code;
}
