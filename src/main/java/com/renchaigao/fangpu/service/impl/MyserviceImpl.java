package com.renchaigao.fangpu.service.impl;

import com.renchaigao.fangpu.dao.MyTerms;
import com.renchaigao.fangpu.dao.mapper.MyTermsMapper;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.service.MyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MyserviceImpl implements MyService {

    private static Logger logger = Logger.getLogger(MyserviceImpl.class);

    @Autowired
    MyTermsMapper mytermsmapper;

    public ResponseEntity addMyTerms(MyTerms myTerms){
        mytermsmapper.insert(myTerms);
        return new ResponseEntity(RespCode.WARN);
    }

    public ResponseEntity updateMyTermsByTermId(Integer termId,String flag){
        if(flag.equals("add")){

        }else if(flag.equals("delete")){

        }else {

        }
        return new ResponseEntity(RespCode.WARN);
    }


    public ResponseEntity getMytermsByUserId(Integer userId){

        return new ResponseEntity(RespCode.WARN);
    }

}
