package com.wp.jry.core.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.wp.common.api.exception.BusinessException;
import com.wp.common.api.result.CommonResult;
import com.wp.common.api.result.ResponseEnum;
import com.wp.jry.core.listeneer.ExcelDictListener;
import com.wp.jry.core.listeneer.ExcelDictListener2;
import com.wp.jry.core.pojo.dto.ExcelDictDTO;
import com.wp.jry.core.pojo.entity.Dict;
import com.wp.jry.core.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

@Api(tags = "数据字典管理")
//@CrossOrigin //解决跨域
@RestController
@Slf4j
@RequestMapping("/admin/core/dict")
public class AdminDictController {

    @Resource
    private IDictService dictService;

    @ApiOperation("字典批量导入")
    @PostMapping("/import")
    public CommonResult BatchImport(@RequestParam("file") MultipartFile file){
        try {
            //dictService.importData(file.getInputStream());
            //此方法不可用 id 自增，不会使用传入的ID 后续修改业务层代码
            EasyExcel.read(file.getInputStream(),
                            ExcelDictDTO.class,
                            new ExcelDictListener2(dictService)).sheet().doRead();
            return CommonResult.success().message("文件上传成功");
        } catch (Exception e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR,e);
        }
    }

    @ApiOperation("Excel数据导出")
    @GetMapping("/export")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("dict", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class)
                .sheet("数据字典")
                .doWrite(dictService.listDictData());
    }

    @ApiOperation("根据上级id获取子节点数据列表")
    @GetMapping("/listByParentId/{parentId}")
    public CommonResult listByParentId(@ApiParam(value = "上级节点id" ,required = true)
                                           @PathVariable Long parentId ){
        List<Dict> dicts = dictService.listByParentId(parentId);
        return CommonResult.success().data("list",dicts);
    }
}
