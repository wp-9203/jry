package com.wp.srb.core.service.impl;

import com.wp.srb.core.entity.UserBind;
import com.wp.srb.core.dao.UserBindMapper;
import com.wp.srb.core.service.IUserBindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户绑定表 服务实现类
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Service
public class UserBindServiceImpl extends ServiceImpl<UserBindMapper, UserBind> implements IUserBindService {

}
