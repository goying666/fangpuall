package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.RecordingInfo;
import com.renchaigao.fangpu.dao.TermInfo;
import com.renchaigao.fangpu.dao.TodayRecordingRank;
import com.renchaigao.fangpu.dao.TodayTermRank;
import com.renchaigao.fangpu.dao.mapper.RecordingInfoMapper;
import com.renchaigao.fangpu.dao.mapper.TermInfoMapper;
import com.renchaigao.fangpu.dao.mapper.TodayRecordingRankMapper;
import com.renchaigao.fangpu.dao.mapper.TodayTermRankMapper;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.function.dateUse;
import com.renchaigao.fangpu.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    TodayTermRankMapper todayTermRankMapper;
    @Autowired
    TodayRecordingRankMapper todayRecordingRankMapper;
    @Autowired
    TermInfoMapper termInfoMapper;
    @Autowired
    RecordingInfoMapper recordingInfoMapper;


    public ResponseEntity addTermsRank() {
        try {
            List<TermInfo> allterminfos = termInfoMapper.selectAll();
            Collections.sort(allterminfos, new Comparator<TermInfo>() {
                @Override
                public int compare(TermInfo o1, TermInfo o2) {
//                    从小到大
//                    return o1.getZannum() - o2.getZannum();
//                    从大到小
                    return o2.getZannum() - o1.getZannum();
                }
            });
            String allTermStrIdStr = null;
            for (TermInfo i : allterminfos) {
                if (allTermStrIdStr != null) {
                    allTermStrIdStr = allTermStrIdStr + "-" + i.getId().toString();
                } else
                    allTermStrIdStr = i.getId().toString();
            }
            TodayTermRank todayTermRank = new TodayTermRank();
            todayTermRank.setTermranks(allTermStrIdStr);
            todayTermRank.setTodaydate(dateUse.getTodayDate());
            todayTermRankMapper.insertSelective(todayTermRank);
            System.out.println(JSONObject.toJSONString(allterminfos));
            return new ResponseEntity(RespCode.SUCCESS, allterminfos);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity getTermsRank(Integer endnum) {
        try {
            String todayTermRanks = todayTermRankMapper.selectByDate(dateUse.getTodayDate()).getTermranks();
            List<String> termRanksList = Arrays.asList(todayTermRanks.split("-"));
            List<TermInfo> termInfoList = new ArrayList<>();
            if (endnum > termRanksList.size())
                endnum = termRanksList.size();
            for (int i = endnum - 1; i < endnum + 10 && i < termRanksList.size(); i++)
                termInfoList.add(termInfoMapper.selectByPrimaryKey(Integer.parseInt(termRanksList.get(i))));
            return new ResponseEntity(RespCode.SUCCESS, termInfoList);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity addRecordingRank() {
        try {
            List<RecordingInfo> allrecordings = recordingInfoMapper.selectAll();
            Collections.sort(allrecordings, new Comparator<RecordingInfo>() {
                @Override
                public int compare(RecordingInfo o1, RecordingInfo o2) {
//                    从小到大
//                    return o1.getZannum() - o2.getZannum();
//                    从大到小
                    return o2.getZannum() - o1.getZannum();
                }
            });
            String allTermStrIdStr = null;
            for (RecordingInfo i : allrecordings) {
                if (allTermStrIdStr != null) {
                    allTermStrIdStr = allTermStrIdStr + "-" + i.getId().toString();
                } else
                    allTermStrIdStr = i.getId().toString();
            }
            TodayRecordingRank todayRecordingRank = new TodayRecordingRank();
            todayRecordingRank.setRecordingranks(allTermStrIdStr);
            todayRecordingRank.setTodaydate(dateUse.getTodayDate());
            todayRecordingRankMapper.insertSelective(todayRecordingRank);
            System.out.println(JSONObject.toJSONString(allrecordings));
            return new ResponseEntity(RespCode.SUCCESS, allrecordings);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity getRecordingRank(Integer endnum) {
        try {
            String todayRecordingRanks =
                    todayRecordingRankMapper.selectByDate(dateUse.getTodayDate()).getRecordingranks();
            List<String> recordingRanksList = Arrays.asList(todayRecordingRanks.split("-"));
            List<TermInfo> recordingInfoList = new ArrayList<>();
            if (endnum > recordingRanksList.size())
                endnum = recordingRanksList.size();
            for (int i = endnum - 1; i < endnum + 10 && i < recordingRanksList.size(); i++)
                recordingInfoList.add(termInfoMapper.selectByPrimaryKey(Integer.parseInt(recordingRanksList.get(i))));
            return new ResponseEntity(RespCode.SUCCESS, recordingInfoList);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }

    }
}
