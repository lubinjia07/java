package com.baize.framework.exception;

/**
 * @author lubinjia
 * @create 2020/8/5 0:22
 */
public class NoAuthorityException extends Exception {
    public NoAuthorityException(){
        super("请登录");
    }

    public NoAuthorityException(String msg){
        super(msg);
    }
}
