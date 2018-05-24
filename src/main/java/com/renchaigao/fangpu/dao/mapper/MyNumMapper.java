package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.MyNum;

public interface MyNumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyNum record);

    int insertSelective(MyNum record);

    MyNum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyNum record);

    int updateByPrimaryKey(MyNum record);
}