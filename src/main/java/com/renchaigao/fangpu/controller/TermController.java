package com.renchaigao.fangpu.controller;

import com.renchaigao.fangpu.dao.TermInfo;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.service.impl.TermServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/term/")
public class TermController {

    @Autowired
    private TermServiceImpl termserviceimpl;

    @PostMapping(value = "/add" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity addTerm(@RequestBody TermInfo termInfo){
        return termserviceimpl.addTerm(termInfo);
    }

    @PostMapping(value = "/info" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity getTermInfo(@RequestBody TermInfo termInfo){
        return termserviceimpl.getTermInfo(termInfo);
    }

    @PostMapping(value = "/update" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateTermInfo(@RequestBody TermInfo termInfo){
        return termserviceimpl.updateTerm(termInfo);
    }
    @PostMapping(value = "/delete" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity deleteTermInfo(@RequestBody TermInfo termInfo){
        return termserviceimpl.deleteTerm(termInfo);
    }

}
