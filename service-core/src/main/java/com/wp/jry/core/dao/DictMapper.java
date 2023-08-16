package com.wp.jry.core.dao;

import com.wp.jry.core.pojo.dto.ExcelDictDTO;
import com.wp.jry.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */

@Mapper
public interface DictMapper extends BaseMapper<Dict> {

    boolean insertBatch(List<ExcelDictDTO> list);

    int save(Dict dict);

}
