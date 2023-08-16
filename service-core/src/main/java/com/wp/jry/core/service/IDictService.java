package com.wp.jry.core.service;

import com.wp.jry.core.pojo.dto.ExcelDictDTO;
import com.wp.jry.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
public interface IDictService extends IService<Dict> {

    public  void importData(InputStream inputStream);

    List<ExcelDictDTO> listDictData();

    List<Dict> listByParentId(Long parentId);

    boolean batchInsert(List<Dict> list,int batchSize);
}
