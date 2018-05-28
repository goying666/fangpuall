package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.TodayTenTerm;

public interface TodayTenTermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TodayTenTerm record);

    int insertSelective(TodayTenTerm record);

    TodayTenTerm selectByPrimaryKey(Integer id);
    TodayTenTerm selectByDate(String date);

    int updateByPrimaryKeySelective(TodayTenTerm record);

    int updateByPrimaryKey(TodayTenTerm record);
}