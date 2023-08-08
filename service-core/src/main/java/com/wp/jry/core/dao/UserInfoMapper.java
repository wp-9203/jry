package com.wp.jry.core.dao;

import com.wp.jry.core.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户基本信息 Mapper 接口
 * </p>
 *
 * @author wp
 * @since 2023-08-08
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
