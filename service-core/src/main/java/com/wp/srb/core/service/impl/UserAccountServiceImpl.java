package com.wp.srb.core.service.impl;

import com.wp.srb.core.entity.UserAccount;
import com.wp.srb.core.dao.UserAccountMapper;
import com.wp.srb.core.service.IUserAccountService;
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
