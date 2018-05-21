package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.MyRecording;
import com.renchaigao.fangpu.dao.MyTerms;
import com.renchaigao.fangpu.dao.RecordingInfo;
import com.renchaigao.fangpu.dao.TermInfo;
import com.renchaigao.fangpu.dao.mapper.MyRecordingMapper;
import com.renchaigao.fangpu.dao.mapper.MyTermsMapper;
import com.renchaigao.fangpu.dao.mapper.RecordingInfoMapper;
import com.renchaigao.fangpu.dao.mapper.TermInfoMapper;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.function.normalFunc;
import com.renchaigao.fangpu.service.MyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class MyserviceImpl implements MyService {

    private static Logger logger = Logger.getLogger(MyserviceImpl.class);

    @Autowired
    MyTermsMapper mytermsmapper;
    @Autowired
    TermInfoMapper termInfoMapper;

    @Autowired
    MyRecordingMapper myRecordingMapper;
    @Autowired
    RecordingInfoMapper recordingInfoMapper;


    public ResponseEntity addMyTerms(MyTerms myTerms) {
        try {
            mytermsmapper.insert(myTerms);
            return new ResponseEntity(RespCode.SUCCESS, myTerms);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }


    public ResponseEntity getMytermsByUserId(Integer id) {
        try {
            MyTerms myTerms = mytermsmapper.selectByPrimaryKey(id);
            List<String> termList = Arrays.asList(myTerms.getAlltermlist().split("-"));
            List<TermInfo> termInfosList = new ArrayList<>();
            for (String str : termList)
                termInfosList.add(termInfoMapper.selectByPrimaryKey(Integer.parseInt(str)));
            return new ResponseEntity(RespCode.SUCCESS, termInfosList);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity updateMyTermsByTermId(Integer id, Integer termId, String flagStr) {
        try {
            MyTerms myTerms = mytermsmapper.selectByPrimaryKey(id);
            switch (flagStr) {
                case "add": {
                    //将最新的id拼接进原有string的末尾；通过"-"
                    myTerms.setAlltermlist(myTerms.getAlltermlist() + "-" + termId.toString());
                    mytermsmapper.updateByPrimaryKeySelective(myTerms);
                    return new ResponseEntity(RespCode.ADDSUCCES, myTerms);
                }
                case "delete": {
                    myTerms.setAlltermlist(normalFunc.deleteStrArg(myTerms.getAlltermlist(), termId.toString()));
                    return new ResponseEntity(RespCode.DELETSUCCES, mytermsmapper.updateByPrimaryKeySelective(myTerms));
                }
            }
            return new ResponseEntity(RespCode.WRONGINFO);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity addMyRecordings(MyRecording myRecording) {
        try {
            myRecordingMapper.insert(myRecording);
            return new ResponseEntity(RespCode.SUCCESS, myRecording);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity getMyRecordingsByUserId(Integer id) {
        try {
            MyRecording myRecording = myRecordingMapper.selectByPrimaryKey(id);
            List<String> recordingList = Arrays.asList(myRecording.getAllrecordinglist().split("-"));
            List<RecordingInfo> recordingInfos = new ArrayList<>();
            for (String str : recordingList) {
                recordingInfos.add(recordingInfoMapper.selectByPrimaryKey(Integer.parseInt(str)));
            }
            return new ResponseEntity(RespCode.SUCCESS, recordingInfos);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity updateMyRecordings(Integer id, Integer recordingId, String flagStr) {
        try {
            MyRecording myRecording = myRecordingMapper.selectByPrimaryKey(id);
            switch (flagStr) {
                case "add": {
                    //将最新的id拼接进原有string的末尾；通过"-"
                    myRecording.setAllrecordinglist(myRecording.getAllrecordinglist() + "-" + recordingId.toString());
                    myRecordingMapper.updateByPrimaryKeySelective(myRecording);
                    return new ResponseEntity(RespCode.ADDSUCCES, myRecording);
                }
                case "delete": {
                    myRecording.setAllrecordinglist(normalFunc.deleteStrArg(myRecording.getAllrecordinglist(), recordingId.toString()));
                    return new ResponseEntity(RespCode.DELETSUCCES, myRecordingMapper.updateByPrimaryKeySelective(myRecording));
                }
            }
            return new ResponseEntity(RespCode.WRONGINFO);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }
}
