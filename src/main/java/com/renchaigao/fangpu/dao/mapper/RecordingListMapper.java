package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.RecordingList;

public interface RecordingListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecordingList record);

    int insertSelective(RecordingList record);

    RecordingList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecordingList record);

    int updateByPrimaryKey(RecordingList record);
}