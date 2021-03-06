package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.TermInfo;

import java.util.List;

public interface TermInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TermInfo record);

    int insertSelective(TermInfo record);

    TermInfo selectByPrimaryKey(Integer id);
    TermInfo selectByUseridAndDate(Integer id);

    List<TermInfo> selectAllUserTermByUserId(Integer userid);

    List<TermInfo> selectAll();


    int updateByPrimaryKeySelective(TermInfo record);

    int updateByPrimaryKey(TermInfo record);
}