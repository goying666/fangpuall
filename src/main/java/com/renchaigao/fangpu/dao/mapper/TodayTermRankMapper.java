package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.TodayTermRank;


public interface TodayTermRankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TodayTermRank record);

    int insertSelective(TodayTermRank record);

    TodayTermRank selectByPrimaryKey(Integer id);

    TodayTermRank selectByDate(String todayDate);

    int updateByPrimaryKeySelective(TodayTermRank record);

    int updateByPrimaryKeyWithBLOBs(TodayTermRank record);

    int updateByPrimaryKey(TodayTermRank record);
}