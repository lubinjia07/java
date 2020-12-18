package com.baize.framework.dto.basic;

import com.baize.framework.exception.BadRequestException;
import com.baize.framework.exception.BizErrorException;
import com.baize.framework.exception.FailureException;
import com.baize.framework.exception.NoAuthorityException;
import lombok.Data;

@Data
public class BaseResponse {
    protected int status;

    protected String msg = "";

    public BaseResponse() {
    }

    public BaseResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public void successResult(){
        this.status = 200;
        this.msg = "";
    }

    public void successResult(String msg){
        this.status = 200;
        this.msg = msg;
    }

    public void badRequestResult() {
        this.status = 400;
        this.msg = "";
    }

    public void badRequestResult(String msg) {
        this.status = 400;
        this.msg = msg;
    }

    public void noAuthorityResult() {
        this.status = 401;
        this.msg = "";
    }

    public void noAuthorityResult(String msg) {
        this.status = 401;
        this.msg = msg;
    }

    public void paramError(){
        this.status = 404;
    }

    public void paramError(String msg){
        this.status = 404;
        this.msg = msg;
    }

    public void failureResult(){
        this.status = 500;
    }

    public void failureResult(String msg) {
        this.status = 500;
        this.msg = msg;
    }

    public void bizErrorResult() {
        this.status = 501;
    }

    public void bizErrorResult(String msg) {
        this.status = 501;
        this.msg = msg;
    }

    public static BaseResponse success() {
        BaseResponse response = new BaseResponse();
        response.successResult();
        return response;
    }

    public static BaseResponse success(String msg) {
        BaseResponse response = new BaseResponse();
        response.successResult(msg);
        return response;
    }

    //region 静态方法 抛出异常
    public static void badRequest() throws BadRequestException {
        throw new BadRequestException();
    }

    public static void badRequest(String msg) throws BadRequestException {
        throw new BadRequestException(msg);
    }

    public static void noAuthority() throws NoAuthorityException {
        throw new NoAuthorityException();
    }

    public static void noAuthority(String msg) throws NoAuthorityException {
        throw new NoAuthorityException(msg);
    }

    public static void failure() throws FailureException {
        throw new FailureException();
    }

    public static void failure(String msg) throws FailureException {
        throw new FailureException(msg);
    }

    public static void bizError() throws BizErrorException {
        throw new BizErrorException();
    }

    public static void bizError(String msg) throws BizErrorException {
        throw new BizErrorException(msg);
    }
    //endregion

}
