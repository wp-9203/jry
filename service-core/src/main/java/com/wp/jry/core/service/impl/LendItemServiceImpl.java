package com.wp.jry.core.service.impl;

import com.wp.jry.core.dao.LendItemMapper;
import com.wp.jry.core.pojo.entity.LendItem;
import com.wp.jry.core.service.ILendItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标的出借记录表 服务实现类
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Service
public class LendItemServiceImpl extends ServiceImpl<LendItemMapper, LendItem> implements ILendItemService {

}
