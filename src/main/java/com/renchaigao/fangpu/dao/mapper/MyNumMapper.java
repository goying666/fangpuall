package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.MyNum;

import java.util.List;

public interface MyNumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyNum record);

    int insertSelective(MyNum record);

    MyNum selectByPrimaryKey(Integer id);

    MyNum selectByUserId(Integer userid);

    List<MyNum> selectAll();

    int updateByPrimaryKeySelective(MyNum record);

    int updateByPrimaryKey(MyNum record);
}