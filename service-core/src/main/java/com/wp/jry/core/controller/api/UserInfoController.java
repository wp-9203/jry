package com.wp.jry.core.controller.api;


import com.wp.common.api.exception.Assert;
import com.wp.common.api.result.CommonResult;
import com.wp.common.api.result.ResponseEnum;
import com.wp.jry.base.utils.JwtUtils;
import com.wp.jry.core.pojo.vo.LoginVO;
import com.wp.jry.core.pojo.vo.RegisterVO;
import com.wp.jry.core.pojo.vo.UserInfoVO;
import com.wp.jry.core.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户基本信息 前端控制器
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Api(tags = "会员管理")
//@CrossOrigin //跨域
@Slf4j
@RestController
@RequestMapping("/api/core/userInfo")
public class UserInfoController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private IUserInfoService iUserInfoService;
    @ApiOperation("会员注册")
    @PostMapping("/register")
    public CommonResult register(@RequestBody RegisterVO registerVO){
        //参数基本校验
        Assert.notEmpty(registerVO.getMobile(), ResponseEnum.MOBILE_NULL_ERROR);
        Assert.notEmpty(registerVO.getCode(), ResponseEnum.CODE_NULL_ERROR);
        Assert.notEmpty(registerVO.getPassword(), ResponseEnum.PASSWORD_NULL_ERROR);
        //验证码校验
        String codegn  = (String) redisTemplate.opsForValue().get("srb:sms:code:" + registerVO.getMobile());
        log.info(codegn);
        Assert.equals(registerVO.getCode(),codegn, ResponseEnum.CODE_ERROR);
        //注册
        iUserInfoService.register(registerVO);

        return CommonResult.success().message("注册成功");
    }
    @ApiOperation("会员注册")
    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginVO loginVO, HttpServletRequest request){
        //参数基本校验
        log.info("login接口-参数基本校验");
        Assert.notEmpty(loginVO.getMobile(), ResponseEnum.MOBILE_NULL_ERROR);
        Assert.notEmpty(loginVO.getPassword(), ResponseEnum.PASSWORD_NULL_ERROR);

        String ip = request.getRemoteAddr();

        UserInfoVO userInfoVO = iUserInfoService.login(loginVO,ip);

        return CommonResult.success().message("登录成功").data("userInfo",userInfoVO);

    }

    @ApiOperation("校验令牌")
    @GetMapping("/checkToken")
    public CommonResult checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return JwtUtils.checkToken(token) ?
                CommonResult.success()
                :CommonResult.setCommonResult(ResponseEnum.LOGIN_AUTH_ERROR);
    }
    @ApiOperation("校验手机号是否注册")
    @GetMapping("/checkMobile/{mobile}")
    public boolean checkMobile(@PathVariable String mobile){
        return  iUserInfoService.checkMobile(mobile);

    }
}
