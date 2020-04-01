package com.baize.framework.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("示例响应模型")
public class DemoResponseDto {
    @ApiModelProperty(value = "用户id", required = true, example = "001")
    String userId;

    @ApiModelProperty(value = "用户名称", example = "小明")
    String userName;

    @ApiModelProperty(value = "年龄", example = "23")
    Integer age;
}
