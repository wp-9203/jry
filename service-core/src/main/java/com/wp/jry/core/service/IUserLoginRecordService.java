package com.wp.jry.core.service;

import com.wp.jry.core.pojo.entity.UserLoginRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户登录记录表 服务类
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
public interface IUserLoginRecordService extends IService<UserLoginRecord> {
    List<UserLoginRecord> listTop50(Long userId);
}
