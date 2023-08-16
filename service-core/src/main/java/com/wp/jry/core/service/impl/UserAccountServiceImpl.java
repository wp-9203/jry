package com.wp.jry.core.service.impl;

import com.wp.jry.core.dao.UserAccountMapper;
import com.wp.jry.core.pojo.entity.UserAccount;
import com.wp.jry.core.service.IUserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements IUserAccountService {

}
