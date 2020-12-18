package com.baize.framework.exception;

/**
 * @author lubinjia
 * @create 2020/8/4 23:49
 */
public class BadRequestException extends Exception  {
    public BadRequestException(){
        super("参数错误！");
    }

    public BadRequestException(String msg){
        super(msg);
    }
}
