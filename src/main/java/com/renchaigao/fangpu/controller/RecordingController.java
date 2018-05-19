package com.renchaigao.fangpu.controller;


import com.renchaigao.fangpu.dao.RecordingInfo;
import com.renchaigao.fangpu.dao.RecordingList;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.service.impl.RecordingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/recording/")
public class RecordingController {


    @Autowired
    private RecordingServiceImpl recordingServiceImpl;

    @PostMapping(value = "/add/info" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity addRecordingInfo(@RequestBody RecordingInfo recordingInfo){
        return recordingServiceImpl.addRecording(recordingInfo);
    }

    @PostMapping(value = "/add/file/{userId}/aid/{recordingId}" , consumes = "multipart/form-data")
    @ResponseBody
    public ResponseEntity addRecordingFile(@RequestParam("file") MultipartFile file,
                                          @PathVariable("userId") Integer userId,
                                          @PathVariable("recordingId") Integer recordingId){
        return recordingServiceImpl.addRecordingFile(file,userId,recordingId);
    }

    @PostMapping(value = "/get/info" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity getRecordingInfo(@RequestBody RecordingInfo recordingInfo){
        return recordingServiceImpl.getRecordingInfo(recordingInfo);
    }


    @PostMapping(value = "/RecordingOfTerm/add" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity addRecordingOfTerm(@RequestBody RecordingList recordingList){
        return recordingServiceImpl.addRecordingList(recordingList);
    }

    @PostMapping(value = "/RecordingOfTerm/{listId}/recordingId/{recordingId}/flag/{flag}" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateRecordingOfTerm(
            @PathVariable("listId") Integer listId,
            @PathVariable("recordingId") Integer recordingId,
            @PathVariable("flag") String flag){
        return recordingServiceImpl.updateRecordingList(listId,recordingId,flag);
    }

//    @GetMapping(value = "/get/file/{acordingId}")
//    @ResponseBody
//    public



}
