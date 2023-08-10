package com.wp.common.api.exception;


import com.wp.common.api.result.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常①先定义异常，②在程序中抛出异常，③在全局异常中处理(返回结果给前端)
 */
@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException{

    private Integer code;

    private String message;

    public BusinessException(String message){
        this.message =message;
    }

    public BusinessException(String message , Integer code){
        this.message =message;
        this.code = code;
    }

    /**
     *
     * @param message
     * @param code
     * @param cause  原始错误信息
     */
    public BusinessException(String message , Integer code,Throwable cause){
        super(cause);
        this.message =message;
        this.code = code;
    }

    public BusinessException(ResponseEnum responseEnum){
        this.message =responseEnum.getMessage();
        this.code = responseEnum.getCode();
    }

    public BusinessException(ResponseEnum responseEnum, Throwable cause){
        super(cause);
        this.message =responseEnum.getMessage();
        this.code = responseEnum.getCode();
    }


}
