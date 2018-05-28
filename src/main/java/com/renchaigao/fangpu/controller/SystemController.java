package com.renchaigao.fangpu.controller;

import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.domain.search.SearchTerm;
import com.renchaigao.fangpu.service.impl.RankServiceImpl;
import com.renchaigao.fangpu.service.impl.SystemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/system")
public class SystemController {

    @Autowired
    RankServiceImpl rankServiceImpl;
    @Autowired
    SystemServiceImpl systemServiceImpl;

    @GetMapping(value = "/todayrankadd", consumes = "application/json")
    @ResponseBody
    public ResponseEntity todayRankAdd() {
        return rankServiceImpl.addTermsRank();
    }

    @GetMapping(value = "/todaytermget", consumes = "application/json")
    @ResponseBody
    public ResponseEntity todayTermGet() {
        return systemServiceImpl.todayTermGet();
    }
}
