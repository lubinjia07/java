package com.baize.framework.controller;

import com.baize.framework.dto.basic.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lubinjia
 * @create 2020/4/19 20:51
 */
@RestController
@Api("api/excel")
public class ExcelController {
    @GetMapping("/export")
    public ApiResponse exportExcel(){
        ApiResponse response = new ApiResponse();

        return response;
    }

    @PostMapping("/import")
    public ApiResponse importExcel(HttpServletRequest request){
        ApiResponse response = new ApiResponse();

        return response;
    }

}
