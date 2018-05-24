package com.renchaigao.fangpu.controller;

import com.renchaigao.fangpu.domain.response.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/num")
public class NumController {

    @GetMapping(value = "/zan/add/{userId}/",consumes = "application/json")
    @ResponseBody
    public ResponseEntity getMyTermsInfo(@PathVariable("id") Integer id){
        return myserviceImpl.getMytermsByUserId(id);
    }
}
