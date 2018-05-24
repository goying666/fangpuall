package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.MyZan;
import com.renchaigao.fangpu.dao.MyZanWithBLOBs;

public interface MyZanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyZanWithBLOBs record);

    int insertSelective(MyZanWithBLOBs record);

    MyZanWithBLOBs selectByPrimaryKey(Integer id);

    MyZanWithBLOBs selectByUserId(Integer userid);

    int updateByPrimaryKeySelective(MyZanWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MyZanWithBLOBs record);

    int updateByPrimaryKey(MyZan record);
    int updateZanByPrimaryKey(MyZanWithBLOBs record);
    int updateBadByPrimaryKey(MyZanWithBLOBs record);
}