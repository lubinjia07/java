package com.baize.framework.dto.basic;

import lombok.Data;

import java.util.List;

@Data
public class ApiPageResponse<T> extends BaseResponse{
    public List<T> data;

    public void Success(List<T> data){
        this.status = 200;
        this.data = data;
    }

    public void Success(List<T> data, String msg){
        Success(data);
        this.msg = msg;
    }

    public void SuccessPage(List<T> data){
        this.status = 200;
        this.data = data;
    }

    public void SuccessPage(List<T> data, String msg){
        Success(data);
        this.msg = msg;
    }

}
