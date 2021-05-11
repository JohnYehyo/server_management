package com.johnyehyo.servermanage.core.bean;

import com.johnyehyo.servermanage.core.enums.ResponseEnum;

/**
 * @description: 业务异常
 * @author: JohnYehyo
 * @create: 2021-05-11 20:08:00
 */
public class BusinessException extends RuntimeException{

    private Integer code;
    private String msg;

    public BusinessException(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getValue();
    }

    public BusinessException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }



    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
