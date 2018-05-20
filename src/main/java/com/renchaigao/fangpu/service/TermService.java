package com.renchaigao.fangpu.service;


import com.renchaigao.fangpu.dao.TermInfo;
import com.renchaigao.fangpu.domain.response.ResponseEntity;

public interface TermService {

    ResponseEntity addTerm(TermInfo termInfo);
    ResponseEntity updateTerm(TermInfo termInfo);
    ResponseEntity deleteTerm(TermInfo termInfo);

    ResponseEntity getTermInfo(TermInfo termInfo);

}
