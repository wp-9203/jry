package com.wp.srb.core.service.impl;

import com.wp.srb.core.entity.UserInfo;
import com.wp.srb.core.dao.UserInfoMapper;
import com.wp.srb.core.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
