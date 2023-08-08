package com.wp.srb.core.service.impl;

import com.wp.srb.core.entity.Dict;
import com.wp.srb.core.dao.DictMapper;
import com.wp.srb.core.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

}
