package com.baize.framework.exception;

/**
 * @author lubinjia
 * @create 2020/8/6 22:33
 */
public class BizErrorException extends Exception {
    public BizErrorException(){
        super("biz error!");
    }

    public BizErrorException(String msg){
        super(msg);
    }

}
