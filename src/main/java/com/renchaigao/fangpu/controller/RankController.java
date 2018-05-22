package com.renchaigao.fangpu.controller;

import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.service.impl.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rank")
public class RankController {
    @Autowired
    RankServiceImpl rankServiceImpl;

    @GetMapping(value = "/get",consumes = "application/json")
    @ResponseBody
    public ResponseEntity getMyTermsInfo(){
        return rankServiceImpl.getTodayRank("");
    }

}
