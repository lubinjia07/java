package com.baize.framework.dto.basic;

import lombok.Data;

@Data
public class ApiResponse<T> extends BaseResponse {
    private T data;

    public void successResult(T data){
        this.status = 200;
        this.data = data;
    }

    public void successResult(T data, String msg){
        this.status = 200;
        this.data = data;
        this.msg = msg;
    }

}
