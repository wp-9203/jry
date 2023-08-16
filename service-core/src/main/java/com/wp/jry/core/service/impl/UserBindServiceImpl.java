package com.wp.jry.core.service.impl;

import com.wp.jry.core.dao.UserBindMapper;
import com.wp.jry.core.pojo.entity.UserBind;
import com.wp.jry.core.service.IUserBindService;
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
