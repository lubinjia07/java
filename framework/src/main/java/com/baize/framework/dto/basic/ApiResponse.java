package com.baize.framework.dto.basic;

import lombok.Data;

@Data
public class ApiResponse<T> extends BaseResponse {
    private T data;

    public void Success(T data){
        this.status = 200;
        this.data = data;
    }

    public void Success(T data, String msg){
        this.status = 200;
        this.data = data;
        this.msg = msg;
    }

}
