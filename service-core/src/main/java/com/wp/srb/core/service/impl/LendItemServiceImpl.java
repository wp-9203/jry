package com.wp.srb.core.service.impl;

import com.wp.srb.core.entity.LendItem;
import com.wp.srb.core.dao.LendItemMapper;
import com.wp.srb.core.service.ILendItemService;
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
