package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.TodayRecordingRank;

import java.util.List;

public interface TodayRecordingRankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TodayRecordingRank record);

    int insertSelective(TodayRecordingRank record);

    TodayRecordingRank selectByPrimaryKey(Integer id);
    TodayRecordingRank selectByDate(String todayDate);

    int updateByPrimaryKeySelective(TodayRecordingRank record);

    int updateByPrimaryKeyWithBLOBs(TodayRecordingRank record);

    int updateByPrimaryKey(TodayRecordingRank record);
}