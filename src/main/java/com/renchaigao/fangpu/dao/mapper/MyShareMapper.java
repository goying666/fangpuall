package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.MyShare;

public interface MyShareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyShare record);

    int insertSelective(MyShare record);

    MyShare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyShare record);

    int updateByPrimaryKey(MyShare record);
}