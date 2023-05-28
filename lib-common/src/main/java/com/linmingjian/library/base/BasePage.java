package com.linmingjian.library.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页基类
 * @author lin
 * @since 2023-05-27
 */
@Getter
@Setter
@AllArgsConstructor
@ApiModel("分页")
public class BasePage<T> {
    @ApiModelProperty("当前页")
    private long current;

    @ApiModelProperty("每页数")
    private long size;

    @ApiModelProperty("总页数")
    private long pages;

    @ApiModelProperty("数据总数")
    private long total;

    @ApiModelProperty("分页数据")
    private List<T> list;
}
