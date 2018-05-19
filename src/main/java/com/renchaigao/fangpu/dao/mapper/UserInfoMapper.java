package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    UserInfo selectByUnionID(Integer unionid);



    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}