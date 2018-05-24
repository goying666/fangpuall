package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.RecordingNumList;
import com.renchaigao.fangpu.dao.RecordingNumListWithBLOBs;

public interface RecordingNumListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecordingNumListWithBLOBs record);

    int insertSelective(RecordingNumListWithBLOBs record);

    RecordingNumListWithBLOBs selectByPrimaryKey(Integer id);

    RecordingNumListWithBLOBs selectByRecordingId(Integer recordingid);

    int updateByPrimaryKeySelective(RecordingNumListWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(RecordingNumListWithBLOBs record);

    int updateByPrimaryKey(RecordingNumList record);
}