package com.baize.framework.exception;

/**
 * @author lubinjia
 * @create 2020/8/5 0:23
 */
public class FailureException extends Exception {
    public FailureException(){
        super("服务器内部错误,请联系管理员！");
    }

    public FailureException(String msg){
        super(msg);
    }
}
