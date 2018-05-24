package com.renchaigao.fangpu.service;

import com.renchaigao.fangpu.domain.response.ResponseEntity;

import java.util.Map;

public interface NumService {
    ResponseEntity numZanControl(Map<String,Object> reqMap);
    ResponseEntity numBadControl(Map<String,Object> reqMap);
    ResponseEntity numZanGet(Integer userid, Integer recordingid);
    ResponseEntity numBadGet(Integer userid, Integer recordingid);
}
