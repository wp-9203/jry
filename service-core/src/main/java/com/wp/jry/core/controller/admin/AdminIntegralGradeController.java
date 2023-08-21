package com.wp.jry.core.controller.admin;


import com.wp.common.api.exception.Assert;
import com.wp.common.api.result.CommonResult;
import com.wp.common.api.result.ResponseEnum;
import com.wp.jry.core.pojo.entity.IntegralGrade;
import com.wp.jry.core.service.IIntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Api(tags = "积分等级管理")
//@CrossOrigin //解决跨域
@RestController
@Slf4j
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {
    @Resource
    private IIntegralGradeService iIntegralGradeService;
    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public CommonResult listAll(){
        List<IntegralGrade> list = iIntegralGradeService.list();
        return CommonResult.success().data("list",list);
    }
    @ApiOperation(value = "根据id删除数据记录", notes="逻辑删除数据记录")
    @DeleteMapping("/delete/{id}")
    public CommonResult removeById(@PathVariable Long id){
        boolean b = iIntegralGradeService.removeById(id);
        if(b){
            return CommonResult.success().message("删除");
        } else {
            return CommonResult.fail();
        }

    }
    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public CommonResult save(@RequestBody IntegralGrade integralGrade){
        /*if(integralGrade.getBorrowAmount() == null){
            throw new BusinessException(ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        }*/
        Assert.notNull(integralGrade.getBorrowAmount(),ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        boolean result = iIntegralGradeService.save(integralGrade);
        return result?CommonResult.success().message("新增积分等级成功")
                :CommonResult.fail().message("新增积分等级失败");
    }
    @ApiOperation("根据id查询积分等级")
    @GetMapping("/get/{id}")
    public CommonResult getById(@PathVariable Long id){
        IntegralGrade integralGrade = iIntegralGradeService.getById(id);
        return integralGrade != null?
                CommonResult.success().message("根据id查询积分等级成功").data("integralGrade",integralGrade)
                :CommonResult.fail().message("根据id查询积分等级失败");
    }

    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public CommonResult updateById(@RequestBody IntegralGrade integralGrade){
        boolean result = iIntegralGradeService.updateById(integralGrade);
        return result?CommonResult.success().message("更新查询积分等级成功")
                :CommonResult.fail().message("更新查询积分等级失败");
    }
}
