package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.TermInfo;
import com.renchaigao.fangpu.dao.TodayRankWithBLOBs;
import com.renchaigao.fangpu.dao.mapper.TermInfoMapper;
import com.renchaigao.fangpu.dao.mapper.TodayRankMapper;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class RankServiceImpl  implements RankService{

    @Autowired
    TodayRankMapper todayRankMapper;
    @Autowired
    TermInfoMapper termInfoMapper;
    public ResponseEntity getTodayRank(String adminStr){
        try {
            List<TermInfo> allterminfos = termInfoMapper.selectAll();
            System.out.println(JSONObject.toJSONString(allterminfos));
//            拿所有的terms
//
            return new ResponseEntity(RespCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }
}
