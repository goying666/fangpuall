package com.renchaigao.fangpu.controller;


import com.renchaigao.fangpu.dao.MyRecording;
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

    //    @PostMapping(value = "/myterms/add/",consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity addMyTermsInfo(@RequestBody MyTerms myTerms){
//        return myserviceImpl.addMyTerms(myTerms);
//    }
    @GetMapping(value = "/all/{userid}", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getMyAllInfo(@PathVariable("userid") Integer userid) {
        return myserviceImpl.getMyAllInfo(userid);
    }

    @GetMapping(value = "/myterms/get/{id}", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getMyTermsInfo(@PathVariable("id") Integer id) {
        return myserviceImpl.getMytermsByUserId(id);
    }


    @PostMapping(value = "/myterms/{id}/termId/{termId}/flag/{flagStr}", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateMyTerms(
            @PathVariable("id") Integer id,
            @PathVariable("termId") Integer termId,
            @PathVariable("flagStr") String flagStr) {
        return myserviceImpl.updateMyTermsByTermId(id, termId, flagStr);
    }
//    @PostMapping(value = "/myrecording/add/",consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity addMyRecordingInfo(@RequestBody MyRecording myRecording){
//        return myserviceImpl.addMyRecordings(myRecording);
//    }

    @GetMapping(value = "/myrecording/get/{id}", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getMyRecordingInfo(@PathVariable("id") Integer id) {
        return myserviceImpl.getMyRecordingsByUserId(id);
    }

    @GetMapping(value = "/myrecording/{id}/recordinId/{recordinId}/flag/{flagStr}", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateMyRecording(
            @PathVariable("id") Integer id,
            @PathVariable("recordinId") Integer recordinId,
            @PathVariable("flagStr") String flagStr) {
        return myserviceImpl.updateMyRecordings(id, recordinId, flagStr);
    }
}
