package com.linmingjian.library.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("登录凭证")
public class TokenVo implements Serializable {
    /** 登录凭证 */
    @ApiModelProperty("登录凭证")
    private String token;
}
