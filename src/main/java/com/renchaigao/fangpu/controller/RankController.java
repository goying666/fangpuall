package com.renchaigao.fangpu.controller;

import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.service.impl.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rank")
public class RankController {
    @Autowired
    RankServiceImpl rankServiceImpl;
    @GetMapping(value = "/addtermrank",consumes = "application/json")
    @ResponseBody
    public ResponseEntity addTermsRank(){
        return rankServiceImpl.addTermsRank();
    }
    @GetMapping(value = "/gettermrank/{endnum}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity getTermsRank(@PathVariable("endnum") Integer endnum){
        return rankServiceImpl.getTermsRank(endnum);
    }

    @GetMapping(value = "/addrecordingrank",consumes = "application/json")
    @ResponseBody
    public ResponseEntity addRecordingRank(){
        return rankServiceImpl.addRecordingRank();
    }
    @GetMapping(value = "/getrecordingrank/{endnum}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity getRecordingRank(@PathVariable("endnum") Integer endnum){
        return rankServiceImpl.getRecordingRank(endnum);
    }

}
