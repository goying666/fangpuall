package com.renchaigao.fangpu.dao.mapper;

import com.renchaigao.fangpu.dao.MyTerms;

public interface MyTermsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyTerms record);

    int insertSelective(MyTerms record);

    MyTerms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyTerms record);

    int updateByPrimaryKey(MyTerms record);
}