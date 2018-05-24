package com.renchaigao.fangpu.service.impl;

import com.renchaigao.fangpu.dao.MyZan;
import com.renchaigao.fangpu.dao.MyZanWithBLOBs;
import com.renchaigao.fangpu.dao.RecordingInfo;
import com.renchaigao.fangpu.dao.mapper.MyNumMapper;
import com.renchaigao.fangpu.dao.mapper.MyZanMapper;
import com.renchaigao.fangpu.dao.mapper.RecordingInfoMapper;
import com.renchaigao.fangpu.dao.mapper.RecordingNumListMapper;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class NumServiceImpl {

    @Autowired
    MyZanMapper myZanMapper;

    @Autowired
    MyNumMapper myNumMapper;

    @Autowired
    RecordingInfoMapper recordingInfoMapper;

    @Autowired
    RecordingNumListMapper recordingNumListMapper;

    public ResponseEntity numZanControl(Map<String, Object> reqMap) {
        try {
            //        判断用户是否已经赞过该词条，
            Integer userid = Integer.parseInt(reqMap.get("userid").toString());
            Integer recordingid = Integer.parseInt(reqMap.get("recordingid").toString());
            MyZanWithBLOBs myZanWithBLOBs = myZanMapper.selectByUserId(userid);
            if (myZanWithBLOBs != null) {
                String myZanListStr = myZanWithBLOBs.getZanrecordinglist();
                if (myZanListStr != null) {
                    List<String> zanlist = Arrays.asList(myZanListStr.split("-"));
                    if (zanlist.contains(recordingid.toString())) {
//            赞过：1、方言recordingInfo赞-1；
                        RecordingInfo recordingInfo = recordingInfoMapper.selectByPrimaryKey(recordingid);
                        recordingInfo.setZannum(recordingInfo.getZannum() - 1 );
                        recordingInfoMapper.updateByPrimaryKeySelective(recordingInfo);
//                  2，用户my赞删除记录；
                        zanlist.remove(recordingid.toString());
                        String zanlistNew = null;
                        for (String stri : zanlist){
                            if (zanlistNew == null)
                                zanlistNew = stri;
                            else
                                zanlistNew = zanlistNew + "-" + stri;
                        }
                        
//                  3，方言赞list删除用户；
                        System.out.println("recordingid is on");
                    } else {
//            没赞：1、方言recordingInfo赞+1；
//                  2，用户my赞增加记录；
//                  3，方言赞list增加用户；

                        System.out.println("recordingid is off");
                    }
                }
                System.out.println("myZanListStr is null");
            } else {
                System.out.println("myZanWithBLOBs is null");
            }


            return new ResponseEntity(RespCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }

    }

    public ResponseEntity numBadControl(Map<String, Object> reqMap) {
        try {
            return new ResponseEntity(RespCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }


    public ResponseEntity numZanGet(Integer userid, Integer recordingid) {
        try {
            return new ResponseEntity(RespCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity numBadGet(Integer userid, Integer recordingid) {
        try {
            return new ResponseEntity(RespCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }
}
