package com.renchaigao.fangpu.service;

import com.renchaigao.fangpu.domain.response.ResponseEntity;

import java.util.Map;

public interface NumService {
    ResponseEntity numZanControl(Map<String,Object> reqMap);
    ResponseEntity numBadControl(Map<String,Object> reqMap);
    ResponseEntity numControlGet(Integer userid, Integer recordingid);
    ResponseEntity deleteTerm(Map<String,Object> reqMap);
}
