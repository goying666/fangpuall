package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.RecordingInfo;

public interface RecordingInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecordingInfo record);

    int insertSelective(RecordingInfo record);

    RecordingInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecordingInfo record);

    int updateByPrimaryKey(RecordingInfo record);
}