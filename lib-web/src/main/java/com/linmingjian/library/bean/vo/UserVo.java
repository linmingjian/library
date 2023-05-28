package com.linmingjian.library.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ApiModel("用户信息")
@Accessors(chain = true)
public class UserVo implements Serializable {
    /** 用户ID */
    @ApiModelProperty("用户ID")
    private Long id;

    /** 用户账号 */
    @ApiModelProperty("用户账号")
    private String username;

    /** 用户昵称 */
    @ApiModelProperty("用户昵称")
    private String nickname;
}
