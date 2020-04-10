package com.baize.framework.service;

import com.baize.framework.dto.basic.ApiResponse;
import com.baize.framework.util.TransProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lubinjia
 * @create 2020/4/11 0:00
 */
@Service
public class TransDemoService {
    @Autowired
    private TransProxy transProxy;
    @Autowired
    private TransProxy<ApiResponse> transProxy2;

    public void method1(){
        System.out.println("主方法开始！");
        method2();
        //不带返回值
        transProxy.trans(this::method2);
        //带返回值
        ApiResponse response = transProxy2.trans(this::method3);
        System.out.println("主方法结束！");
    }

//    @Transactional
    public void method2(){
        System.out.println("事务方法开始执行！");

        System.out.println("事务方法执行结束！");
    }

    /**
     * 带返回值
     * @return
     */
    public ApiResponse method3(){
        ApiResponse response = new ApiResponse();
        System.out.println("事务方法开始执行！");
        response.successResult();
        System.out.println("事务方法执行结束！");
        return response;
    }
}
