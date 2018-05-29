package com.renchaigao.fangpu.service;


import com.renchaigao.fangpu.dao.TermInfo;
import com.renchaigao.fangpu.domain.response.ResponseEntity;

import java.util.Map;

public interface TermService {

    ResponseEntity addTerm(TermInfo termInfo);
    ResponseEntity updateTerm(TermInfo termInfo);
    ResponseEntity deleteTerm(Map<String,Object> reqMap);

    ResponseEntity getTermInfo(TermInfo termInfo);

}
