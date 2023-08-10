package com.wp.common.api.exception;

import com.wp.common.api.result.CommonResult;
import com.wp.common.api.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * 统一异常处理器
 * 程序发生异常，进入此处
 */
@RestControllerAdvice
@Slf4j
public class UnifiedExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public CommonResult handleException(Exception e){
        log.error("程序发生异常,Exception",e.getMessage(),e);
        return CommonResult.fail();
    }
    //特定异常处理
    @ExceptionHandler(value= BadSqlGrammarException.class)
    public CommonResult handleException(BadSqlGrammarException e){
        log.error("程序发生异常,BadSqlGrammarException",e.getMessage(),e);
        return CommonResult.setCommonResult(ResponseEnum.BAD_SQL_GRAMMAR_ERROR);
    }
    //处理自定义异常
    @ExceptionHandler(value= BusinessException.class)
    public CommonResult handleException(BusinessException e){
        log.error("程序发生异常,BusinessException",e.getMessage(),e);
        return CommonResult.fail()
                .message(e.getMessage())
                .code(e.getCode());
    }
    /**
     * Controller上一层相关异常
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public CommonResult handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        //SERVLET_ERROR(-102, "servlet请求异常"),
        return CommonResult.fail().message(ResponseEnum.SERVLET_ERROR.getMessage()).code(ResponseEnum.SERVLET_ERROR.getCode());
    }
}
