package com.baize.framework.dto.basic;

import lombok.Data;

@Data
public class BaseResponse {
    protected int status;

    protected String msg = "";

    public void successResult(){
        this.status = 200;
        this.msg = "";
    }

    public void successResult(String msg){
        this.status = 200;
        this.msg = msg;
    }

    public void failureResult(){
        this.status = 500;
    }

    public void Failure(String msg){
        this.status = 500;
        this.msg = msg;
    }

    public void paramError(){
        this.status = 404;
    }

    public void paramError(String msg){
        this.status = 404;
        this.msg = msg;
    }

}
