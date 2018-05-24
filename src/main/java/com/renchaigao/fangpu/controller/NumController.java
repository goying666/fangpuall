package com.renchaigao.fangpu.controller;

import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.service.impl.NumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/num")
public class NumController {

    @Autowired
    NumServiceImpl numServiceImpl;

    @PostMapping(value = "/zan",consumes = "application/json")
    @ResponseBody
    public ResponseEntity numZanControl(@RequestBody Map<String,Object> reqMap){
        return numServiceImpl.numZanControl(reqMap);
    }
    @PostMapping(value = "/bad",consumes = "application/json")
    @ResponseBody
    public ResponseEntity numBadControl(@RequestBody Map<String,Object> reqMap){
        return numServiceImpl.numBadControl(reqMap);
    }
    @GetMapping(value = "/{userid}/info/{recordingid}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity numControlGet(@PathVariable("userid") Integer userid,@PathVariable("recordingid") Integer recordingid){
        return numServiceImpl.numControlGet(userid , recordingid);
    }
}
