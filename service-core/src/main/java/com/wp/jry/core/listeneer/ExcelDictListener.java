package com.wp.jry.core.listeneer;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.wp.common.api.exception.BusinessException;
import com.wp.common.api.result.ResponseEnum;
import com.wp.jry.core.dao.DictMapper;
import com.wp.jry.core.pojo.dto.ExcelDictDTO;
import com.wp.jry.core.pojo.entity.Dict;
import com.wp.jry.core.service.IDictExcelService;
import com.wp.jry.core.service.IDictService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@NoArgsConstructor
public class ExcelDictListener implements ReadListener<ExcelDictDTO> {

    private DictMapper dictMapper;


    //构造函数注入依赖
    public ExcelDictListener(DictMapper dictMapper){
        log.info("通过构造函数引入dictmapper依赖");
        this.dictMapper = dictMapper;
    }



    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    /**
     * 缓存的数据
     */
    private List<ExcelDictDTO> cachedDataList = new ArrayList<>();

    @Override
    public void invoke(ExcelDictDTO excelDictDTO, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", excelDictDTO);
        //将数据全部封装在LIst中，每5条记录执行一次入库操作  方法①
           cachedDataList.add(excelDictDTO);

          if(cachedDataList.size() >= BATCH_COUNT){
            log.info("集合中保存数据的长度{}",cachedDataList.size());
            saveBatch(cachedDataList);
            cachedDataList.clear();
        }


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("数据解析完成");
        //剩余的数据再执行一次入库操作   方法①
        //saveBatch(cachedDataList);

        log.info("excel解析读取完成");
    }

    @Override
    public void onException(Exception e, AnalysisContext analysisContext) throws Exception {
        //本类中的方法执行出现异常时，调用本方法。
            log.error("数据批量入库失败{}",e.getMessage(),e);
            new BusinessException(ResponseEnum.iMPORT_DATA_ERROE);
    }

    @Override
    public void invokeHead(Map<Integer, CellData> map, AnalysisContext analysisContext) {
       //此处方法第一步执行
        log.info("Excel文件开始解析：{}",map);
        //Excel文件开始解析：{0=id, 1=上级id, 2=名称, 3=值, 4=编码} ,此处保存的数据为Excel标题栏
        log.info("analysisContext:{}",analysisContext);

    }

    @Override
    public boolean hasNext(AnalysisContext analysisContext) {
        log.info("hasNext");
        // 返回值默认为 false 需要改为true 不然无法读取Excel
        return true;
    }

    private  boolean saveBatch(List<ExcelDictDTO> list){
        log.info("数据入库");
       return dictMapper.insertBatch(list);
    }

    /*private boolean batchInsert(List<ExcelDictDTO> list ,int batchSize){
        log.info("使用MybatisPlus提供的批量方法------数据入库");
        List<Dict> dicts = list.stream().
                map(e ->
             new Dict(e.getId(),e.getParentId(),e.getName(),e.getValue(),e.getDictCode()))
                .collect(Collectors.toList());
        log.info("ruku:{},{}",dicts,dicts.size());
        return iDictService.saveBatch(dicts,BATCH_COUNT);
    }*/
}
