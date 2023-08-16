package com.wp.jry.core.listeneer;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.wp.common.api.exception.BusinessException;
import com.wp.common.api.result.ResponseEnum;
import com.wp.jry.core.dao.DictMapper;
import com.wp.jry.core.pojo.dto.ExcelDictDTO;
import com.wp.jry.core.pojo.entity.Dict;
import com.wp.jry.core.service.IDictService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
public class ExcelDictListener2 implements ReadListener<ExcelDictDTO> {

    private IDictService iDictService;

    public ExcelDictListener2(IDictService iDictService){
        this.iDictService = iDictService;
    }
    //解析到的数据，暂时保存容器
    private List<ExcelDictDTO> cacheList = new ArrayList<>();
    //数据入库时，每次提交的条数
    private static final int BATCH_SIZE = 5;

    @Override
    public void onException(Exception e, AnalysisContext analysisContext) throws Exception {
        log.info("解析Excel文件失败,异常信息:{}",e.getMessage(),e);
        throw new BusinessException(ResponseEnum.iMPORT_DATA_ERROE);
    }

    @Override
    public void invokeHead(Map<Integer, CellData> map, AnalysisContext analysisContext) {
        log.info("开始解析Excel文件");
    }

    @Override
    public void invoke(ExcelDictDTO excelDictDTO, AnalysisContext analysisContext) {
        log.info("解析到一条数据-{}",excelDictDTO);
        //将数据暂存在list中
        cacheList.add(excelDictDTO);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("导入的Excel文件解析结束");
        //调用入库方法
        batchInsert(cacheList,BATCH_SIZE);
    }

    @Override
    public boolean hasNext(AnalysisContext analysisContext) {
        log.info("解析每条数据前执行，将返回值false->true");
        return true;
    }
    public boolean batchInsert(List<ExcelDictDTO> data,int batchSize){
        log.info("执行批量入库方法{}",data);
        List<Dict> dicts = data.stream()
                .map(e -> new Dict(e.getId(),e.getParentId(), e.getName(), e.getValue(),e.getDictCode()))
                        .collect(Collectors.toList());
        return iDictService.batchInsert(dicts,batchSize);
    }
}
