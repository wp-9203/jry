package com.wp.jry.sms.controller.api;


import com.wp.common.api.exception.Assert;
import com.wp.common.api.result.CommonResult;
import com.wp.common.api.result.ResponseEnum;
import com.wp.common.api.utils.RandomUtils;
import com.wp.common.api.utils.RegexValidateUtils;
import com.wp.jry.sms.client.CoreUserInfoClient;
import com.wp.jry.sms.service.SmsService;
import com.wp.jry.sms.utils.SmsProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Api(tags = "短信管理")
@CrossOrigin //跨域
@Slf4j
@RestController
@RequestMapping("/api/sms")
public class ApiSmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CoreUserInfoClient coreUserInfoClient;

    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public CommonResult send(
            @ApiParam(value = "手机号", required = true)
            @PathVariable String mobile){

        //校验手机号吗不能为空
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        //是否是合法的手机号码
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);
        //校验手机号是否已注册
        boolean result= coreUserInfoClient.checkMobile(mobile);
        Assert.isTrue( result == false,ResponseEnum.MOBILE_EXIST_ERROR);



        String code = RandomUtils.getFourBitRandom();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        //smsService.send(mobile, SmsProperties.TEMPLATE_CODE, map);  阿里账号不可用

        //将验证码存入redis
        redisTemplate.opsForValue().set("srb:sms:code:" + mobile, code, 5, TimeUnit.MINUTES);

        return CommonResult.success().message("短信发送成功");
    }
}
