package com.baize.framework.service;

import com.baize.framework.dto.basic.ApiResponse;
import com.baize.framework.util.AsyncProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author lubinjia
 * @create 2020/4/11 0:00
 */
@Service
public class AsyncDemoService {
    @Autowired
    private AsyncProxy asyncProxy;
    @Autowired
    private AsyncProxy<ApiResponse> asyncProxy2;

    public void method1(){
        System.out.println("主方法开始！");
        method2();
        //不带返回值
        asyncProxy.async(this::method2);
        //带返回值
        ApiResponse response = asyncProxy2.async(this::method3);
        System.out.println("主方法结束！");
    }

//    @Async
    public void method2(){
        System.out.println("异步方法开始执行！");

        System.out.println("异步方法执行结束！");
    }

    /**
     * 带返回值
     * @return
     */
    public ApiResponse method3(){
        ApiResponse response = new ApiResponse();
        System.out.println("异步方法开始执行！");
        response.successResult();
        System.out.println("异步方法执行结束！");
        return response;
    }

}
