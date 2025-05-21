package com.gec.springbootcourse.util;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理、数据预处理等
 */
@ControllerAdvice
public class ControllerExceptionHandler {


    /**
     * 校验异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result validExceptionHandler(BindException e) {
        Result result = new Result();
        result.setCode("500");
        result.setMsg(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return result;
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result validExceptionHandler(MethodArgumentNotValidException e) {
        Result result = new Result();
        result.setCode("500");
        result.setMsg(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return result;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result validExceptionHandler(BusinessException e) {
        Result result = new Result();
        result.setCode("500");
        result.setMsg(e.getCode().getDesc());
        return result;
    }


}
