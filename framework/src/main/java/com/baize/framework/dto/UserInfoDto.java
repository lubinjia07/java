package com.baize.framework.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户模型")
public class UserInfoDto {
    @ApiModelProperty(value = "userId", name = "用户id")
    private String userId;

    @ApiModelProperty(value = "useraName", name = "用户姓名")
    private String useraName;
    
}
