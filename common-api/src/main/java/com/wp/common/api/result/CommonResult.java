package com.wp.common.api.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class CommonResult {

    private Integer code;

    private String message;

    private Map<String,Object> data = new HashMap<>();
    //私有无参构造器，禁止外部访问
    private CommonResult(){}

    public static CommonResult success(){
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(ResponseEnum.SUCCESS.getCode());
        commonResult.setMessage(ResponseEnum.SUCCESS.getMessage());
        return commonResult;
    }

    public static CommonResult fail(){
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(ResponseEnum.FAIL.getCode());
        commonResult.setMessage(ResponseEnum.FAIL.getMessage());
        return commonResult;
    }

    /**
     * 设置特定的结果
     * @param result
     * @return
     */
    public static CommonResult setCommonResult(ResponseEnum result){
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(result.getCode());
        commonResult.setMessage(result.getMessage());
        return commonResult;
    }
    //设置data
    public CommonResult data(String key , Object value){
        this.data.put(key, value);
        return this;
    }
    public CommonResult data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
    //设置message
    public CommonResult message(String message){
        this.setMessage(message);
        return this;
    }
    //设置code
    public CommonResult code(Integer code){
        this.setCode(code);
        return this;
    }

 }
