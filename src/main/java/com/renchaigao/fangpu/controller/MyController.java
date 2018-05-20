package com.renchaigao.fangpu.controller;


import com.renchaigao.fangpu.dao.MyTerms;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.service.impl.MyserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/my")
public class MyController {

    @Autowired
    MyserviceImpl myserviceImpl;

    @PostMapping(value = "/myterms/add/",consumes = "application/json")
    @ResponseBody
    public ResponseEntity addRecordingInfo(@RequestBody MyTerms myTerms){
        return myserviceImpl.addMyTerms(myTerms);
    }

    @GetMapping(value = "/info/{userId}",consumes = "application/json")
    @ResponseBody
    public ResponseEntity getMyTermsInfo(@PathVariable("userId") Integer userId){
        return myserviceImpl.getMytermsByUserId(userId);
    }

}
