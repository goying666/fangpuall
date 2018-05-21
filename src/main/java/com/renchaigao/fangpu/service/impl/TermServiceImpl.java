package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.RecordingList;
import com.renchaigao.fangpu.dao.TermInfo;
import com.renchaigao.fangpu.dao.mapper.RecordingListMapper;
import com.renchaigao.fangpu.dao.mapper.TermInfoMapper;
import com.renchaigao.fangpu.dao.mapper.UserInfoMapper;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.service.TermService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TermServiceImpl implements TermService {

    private static Logger logger = Logger.getLogger(TermServiceImpl.class);
    @Autowired
    TermInfoMapper termInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RecordingListMapper recordingListMapper;

    public ResponseEntity addTerm(TermInfo termInfo) {
        try{
            termInfo.setAddtime(new Date());//小程序端没有添加创建时间，由后台填写该字段；
            termInfoMapper.insert(termInfo);
            RecordingList recordingList = new RecordingList();
            recordingList.setTermid(termInfo.getId());
            recordingListMapper.insert(recordingList);
            termInfo.setRecordingids(recordingList.getId().toString());
            return new ResponseEntity(RespCode.SUCCESS, termInfo);
        }catch (Exception e){
            logger.info(e);
            return new ResponseEntity(RespCode.EXCEPTION);
        }
    }

    public ResponseEntity getTermInfo(TermInfo termInfo) {
        long startTime = System.currentTimeMillis(); //程序开始记录时间
        try {
            return new ResponseEntity(RespCode.SUCCESS ,
                    termInfoMapper.selectByPrimaryKey(termInfo.getId()));
        }catch (Exception e){
            return new ResponseEntity(RespCode.EXCEPTION,e);
        }
    }

    public ResponseEntity updateTerm(TermInfo termInfo) {

        return new ResponseEntity(RespCode.SUCCESS);
    }



    public ResponseEntity deleteTerm(TermInfo termInfo) {

        return new ResponseEntity(RespCode.SUCCESS);
    }

    ;


}
