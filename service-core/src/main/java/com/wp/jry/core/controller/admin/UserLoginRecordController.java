package com.wp.jry.core.controller.admin;


import com.wp.common.api.result.CommonResult;
import com.wp.jry.core.pojo.entity.UserLoginRecord;
import com.wp.jry.core.service.IUserLoginRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户登录记录表 前端控制器
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Api(tags = "用户登录日志管理")
@CrossOrigin //跨域
@Slf4j
@RestController
@RequestMapping("/admin/core/userLoginRecord")
public class UserLoginRecordController {
    @Resource
    IUserLoginRecordService iUserLoginRecordService;
    @ApiOperation("登录日志")
    @GetMapping("/listTpo50/{userId}")
    public CommonResult getuserLoginRecord(@ApiParam("用户ID") @PathVariable("userId") Long userId){

         List<UserLoginRecord> loginRecords =iUserLoginRecordService.listTop50(userId);
         return CommonResult.success().data("loginRecords",loginRecords);
    }
}
