package com.johnyehyo.servermanage.exception;

import com.alibaba.fastjson.JSON;
import com.johnyehyo.servermanage.core.bean.BusinessException;
import com.johnyehyo.servermanage.core.enums.ResponseEnum;
import com.johnyehyo.servermanage.core.vo.ResponseVo;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 全局异常
 * @author: JohnYehyo
 * @create: 2021-04-26 16:01:22
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseVo handle(Exception e) {
        BusinessException e1 = (BusinessException) e;
        return ResponseVo.error(e1.getCode(), e1.getMsg());
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVo handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> errorMesssages = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssages.add(fieldError.getDefaultMessage());
        }
        Integer code = ResponseEnum.PARAM_ERROR.getCode();
        String error = errorMesssages.stream().collect(Collectors.joining(","));
        return ResponseVo.error(code, error);
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResponseVo handleMethodArgumentNotValidException(HttpServletRequest request, BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> errorMesssages = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssages.add(fieldError.getDefaultMessage());
        }
        Integer code = ResponseEnum.PARAM_ERROR.getCode();
        return ResponseVo.error(code, JSON.toJSONString(errorMesssages));
    }
}
