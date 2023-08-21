package com.wp.jry.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wp.jry.core.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wp.jry.core.pojo.query.UserInfoQuery;
import com.wp.jry.core.pojo.vo.LoginVO;
import com.wp.jry.core.pojo.vo.RegisterVO;
import com.wp.jry.core.pojo.vo.UserInfoVO;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
public interface IUserInfoService extends IService<UserInfo> {

    void register(RegisterVO registerVO);

    UserInfoVO login(LoginVO loginVO, String ip);

    IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery);

    void lock(Long id, Integer status);

    boolean checkMobile(String mobile);
}
