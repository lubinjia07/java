package com.baize.framework.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("示例请求模型")
public class DemoRequestDto {
    @ApiModelProperty(value = "用户id", required = true, example = "001")
    private String userId;

    @ApiModelProperty(value = "用户名称", example = "小明")
    private String userName;

}
