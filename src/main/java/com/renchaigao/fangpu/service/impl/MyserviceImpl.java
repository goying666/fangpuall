package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.*;
import com.renchaigao.fangpu.dao.mapper.*;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.function.normalFunc;
import com.renchaigao.fangpu.service.MyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


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
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    MyShareMapper myShareMapper;
    @Autowired
    MyNumMapper myNumMapper;


    public ResponseEntity addMyTerms(MyTerms myTerms) {
        try {
            mytermsmapper.insert(myTerms);
            return new ResponseEntity(RespCode.SUCCESS, myTerms);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity getMyAllInfo(Integer userid) {
        try {
            return new ResponseEntity(RespCode.SUCCESS,getUserAllNum(userid));
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    private JSONObject getUserAllNum(Integer userid){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userid);
        JSONObject retJson = new JSONObject();
        MyTerms myTerms = mytermsmapper.selectByPrimaryKey(userInfo.getMytermsid());
        MyRecording myRecording = myRecordingMapper.selectByPrimaryKey(userInfo.getMyrecordingid());
        MyShare myShare = myShareMapper.selectByPrimaryKey(userInfo.getMyshareid());
//      首先取出所有term，让所有term的zannum相加，再赋值给user
        List<String> termList = Arrays.asList(myTerms.getAlltermlist().split("-"));
        List<TermInfo> termInfosList = new ArrayList<>();
        for (String str : termList)
            termInfosList.add(termInfoMapper.selectByPrimaryKey(Integer.parseInt(str)));
        Integer allZanNum = 0;
        for (TermInfo infouse : termInfosList)
            allZanNum += infouse.getZannum();
        List<String> recordingList = Arrays.asList(myRecording.getAllrecordinglist().split("-"));
        MyNum myNum = myNumMapper.selectByUserId(userid);
        myNum.setTermnum(termList.size());
        myNum.setRecordingnum(recordingList.size());
        myNum.setZannum(allZanNum);
        myNumMapper.updateByPrimaryKeySelective(myNum);
        retJson.put("mytermnum",termList.size());
        retJson.put("myrecordingnum",recordingList.size());
        retJson.put("mysharenum",0);
        retJson.put("myallzannum",allZanNum);
        return retJson;
    }

    public ResponseEntity getMytermsByUserId(Integer id) {
        System.out.println("id" + id);
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
                    if (myTerms.getAlltermlist() != null)
                        myTerms.setAlltermlist(myTerms.getAlltermlist() + "-" + termId.toString());
                    else
                        myTerms.setAlltermlist(termId.toString());
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
                    if (myRecording.getAllrecordinglist() != null)
                        myRecording.setAllrecordinglist(myRecording.getAllrecordinglist() + "-" + recordingId.toString());
                    else
                        myRecording.setAllrecordinglist(recordingId.toString());
//                    myRecording.setAllrecordinglist(myRecording.getAllrecordinglist() + "-" + recordingId.toString());
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
