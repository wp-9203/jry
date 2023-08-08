package com.wp.jry.core.dao;

import com.wp.jry.core.entity.LendItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 标的出借记录表 Mapper 接口
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Mapper
public interface LendItemMapper extends BaseMapper<LendItem> {

}
