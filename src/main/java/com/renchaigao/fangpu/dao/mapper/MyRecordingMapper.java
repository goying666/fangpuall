package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.MyRecording;

public interface MyRecordingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyRecording record);

    int insertSelective(MyRecording record);

    MyRecording selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyRecording record);

    int updateByPrimaryKey(MyRecording record);
}