package com.wp.jry.core.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wp.common.api.result.CommonResult;
import com.wp.jry.core.pojo.entity.UserInfo;
import com.wp.jry.core.pojo.query.UserInfoQuery;
import com.wp.jry.core.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/**
 * <p>
 * 用户基本信息 前端控制器
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Api(tags = "会员管理")
@CrossOrigin //跨域
@Slf4j
@RestController
@RequestMapping("/admin/core/userInfo")
public class AdminUserInfoController {



    @Resource
    private IUserInfoService iUserInfoService;

    @ApiOperation("会员列表")
    @GetMapping("/list/{page}/{limit}")
    public CommonResult listPage(
                                  @ApiParam(value = "当前页",required = true) @PathVariable Long page ,
                                  @ApiParam(value = "每页记录数",required = true) @PathVariable Long limit ,
                                  @ApiParam(value = "查询对象") UserInfoQuery userInfoQuery){
        log.info("查询会员列表");

        Page<UserInfo> pageParam = new Page<>(page, limit);
        IPage<UserInfo> userInfoIPage =iUserInfoService.listPage(pageParam,userInfoQuery);
        return CommonResult.success().data("userInfoPage",userInfoIPage);
    }
    @ApiOperation("锁定与解锁")
    @PutMapping("/locked/{id}/{status}")
    public CommonResult locked(
            @ApiParam(value = "用户id",required = true)@PathVariable Long id ,
            @ApiParam(value = "锁定状态",required = true) @PathVariable Integer status){
        iUserInfoService.lock(id,status);
        return CommonResult.success().message(status == 1?"解锁成功":"锁定");
    }

}
