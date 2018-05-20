package com.renchaigao.fangpu.service;


import com.renchaigao.fangpu.dao.MyTerms;
import com.renchaigao.fangpu.domain.response.ResponseEntity;

public interface MyService {

    ResponseEntity addMyTerms(MyTerms myTerms);
    ResponseEntity updateMyTermsByTermId(Integer termId, String flag);
    ResponseEntity getMytermsByUserId(Integer userId);
}
