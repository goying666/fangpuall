package com.renchaigao.fangpu.service;


import com.renchaigao.fangpu.dao.MyRecording;
import com.renchaigao.fangpu.dao.MyTerms;
import com.renchaigao.fangpu.domain.response.ResponseEntity;

public interface MyService {

    ResponseEntity addMyTerms(MyTerms myTerms);
    ResponseEntity getMytermsByUserId(Integer id);
    ResponseEntity updateMyTermsByTermId(Integer id,Integer termId, String flagStr);


    ResponseEntity addMyRecordings(MyRecording myRecording);
    ResponseEntity getMyRecordingsByUserId(Integer id);
    ResponseEntity updateMyRecordings(Integer id,Integer recordingId, String flagStr);


}
