package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.UserLogin;

public interface UserLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    UserLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLogin record);

    int updateByPrimaryKey(UserLogin record);
}