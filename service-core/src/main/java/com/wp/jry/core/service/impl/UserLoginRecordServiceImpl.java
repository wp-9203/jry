package com.wp.jry.core.service.impl;

import com.wp.jry.core.dao.UserLoginRecordMapper;
import com.wp.jry.core.entity.UserLoginRecord;
import com.wp.jry.core.service.IUserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements IUserLoginRecordService {

}
