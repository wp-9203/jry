package com.wp.srb.core.service.impl;

import com.wp.srb.core.entity.BorrowInfo;
import com.wp.srb.core.dao.BorrowInfoMapper;
import com.wp.srb.core.service.IBorrowInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 借款信息表 服务实现类
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Service
public class BorrowInfoServiceImpl extends ServiceImpl<BorrowInfoMapper, BorrowInfo> implements IBorrowInfoService {

}
