package com.wp.jry.core.controller.admin;


import com.wp.jry.core.entity.IntegralGrade;
import com.wp.jry.core.service.IIntegralGradeService;
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
@CrossOrigin //解决跨域
@RestController
@RequestMapping("/core/admin/integralGrade")
public class AdminIntegralGradeController {
    @Resource
    private IIntegralGradeService iIntegralGradeService;
    @GetMapping("/list")
    public List<IntegralGrade> listAll(){
        return iIntegralGradeService.list();
    }

    @DeleteMapping("/delete/{id}")
    public boolean removeById(@PathVariable Long id){
        return iIntegralGradeService.removeById(id);
    }
}
