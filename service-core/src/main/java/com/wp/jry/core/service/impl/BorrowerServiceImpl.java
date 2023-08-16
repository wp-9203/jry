package com.wp.jry.core.service.impl;

import com.wp.jry.core.pojo.entity.Borrower;
import com.wp.jry.core.dao.BorrowerMapper;
import com.wp.jry.core.service.IBorrowerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 借款人 服务实现类
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Service
public class BorrowerServiceImpl extends ServiceImpl<BorrowerMapper, Borrower> implements IBorrowerService {

}
