package com.baize.framework.dto.basic;

import lombok.Data;

@Data
public class BaseResponse {
    public int status;

    public String msg = "";

    public void Success(){
        this.status = 200;
        this.msg = "";
    }

    public void Success(String msg){
        this.status = 200;
        this.msg = msg;
    }

    public void Failure(){
        this.status = 500;
    }

    public void Failure(String msg){
        this.status = 500;
        this.msg = msg;
    }

    public void ParamError(){
        this.status = 404;
    }

    public void ParamError(String msg){
        this.status = 404;
        this.msg = msg;
    }

}
