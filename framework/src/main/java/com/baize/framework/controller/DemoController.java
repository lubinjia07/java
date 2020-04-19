package com.baize.framework.controller;

import com.baize.framework.dto.DemoRequestDto;
import com.baize.framework.dto.DemoResponseDto;
import com.baize.framework.dto.basic.ApiPageResponse;
import com.baize.framework.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "示例api")
@RestController
@RequestMapping("api/demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

//    ,produces = MediaType.APPLICATION_JSON_VALUE

    @ApiOperation(value = "查询单条数据", notes = "查询单条数据",
            response = String.class)
    @GetMapping("/get")
    public String get(@ApiParam("用户id") String userId){
        return demoService.getDemo();
    }

    @ApiOperation(value = "查询多条数据", notes = "查询多条数据",
            response = DemoResponseDto.class)
    @GetMapping("/list")
    public ApiPageResponse<DemoResponseDto> getList(DemoRequestDto requestDto){
        ApiPageResponse<DemoResponseDto> response = new ApiPageResponse<>();
        response.SuccessPage(demoService.getList(requestDto));
        return response;
    }

}
