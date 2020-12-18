package com.baize.framework.exception;

import com.baize.framework.dto.basic.BaseResponse;
import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author lubinjia
 * @create 2020/8/4 23:45
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 统一拦截自定义异常
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse exceptionHandle(Exception e) {
        if(e instanceof BadRequestException){
            return new BaseResponse(400, e.getMessage());
        } if(e instanceof NoAuthorityException){
            return new BaseResponse(401, e.getMessage());
        } if(e instanceof FailureException){
            return new BaseResponse(500, e.getMessage());
        }  else {//其他未捕获异常
            LOGGER.error("exception:{}", e.getMessage(), e);
            return new BaseResponse(500, "服务器内部错误,请联系系统管理员!");
        }
    }

    /**
     * 拦截@RequestBody上validate失败后抛出的异常：MethodArgumentNotValidException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse parameterExceptionHandler(MethodArgumentNotValidException e) {
        LOGGER.error("exception:{}", e.getMessage(), e);
        BaseResponse response = new BaseResponse();
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        //只返回一个参数错误信息
        /*if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = (FieldError) errors.get(0);
                response.badRequestResult(fieldError.getDefaultMessage());
                return response;
            }
        }*/

        //返回所有参数错误信息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            List<String> errorMsgList = new ArrayList<>();
            if(!CollectionUtils.isEmpty(errors)){
                for (ObjectError error : errors) {
                    errorMsgList.add(error.getDefaultMessage());
                }
            }
            //返回所有异常信息，用分号隔开
            response.badRequestResult(Joiner.on(";").join(errorMsgList));
            return response;
        }
        response.badRequestResult(e.getMessage());
        return response;
    }

    /**
     * 拦截@RequestParam上validate失败后抛出的异常：ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse parameterExceptionHandler(ConstraintViolationException e){
        LOGGER.error("exception:{}", e.getMessage(), e);
        BaseResponse response = new BaseResponse();
        Set<ConstraintViolation<?>> constraintViolations =  e.getConstraintViolations();
        if(!CollectionUtils.isEmpty(constraintViolations)){
            List<String> errorMsgList = new ArrayList<>();
            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                errorMsgList.add(constraintViolation.getMessage());
            }
            response.badRequestResult(Joiner.on(",").join(errorMsgList));
            return response;
        }
        response.badRequestResult(e.getMessage());
        return response;
    }


    /**
     * 统一拦截运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(ConstraintViolationException e){
        LOGGER.error("exception:{}", e.getMessage(), e);
        return new BaseResponse(501, "系统出现异常，请联系系统管理！");
    }

}
