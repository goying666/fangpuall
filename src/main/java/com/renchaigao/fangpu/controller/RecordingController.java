package com.renchaigao.fangpu.controller;


import com.renchaigao.fangpu.dao.RecordingInfo;
import com.renchaigao.fangpu.dao.RecordingList;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.service.impl.RecordingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping(value = "/recording/")
public class RecordingController {


    @Autowired
    private RecordingServiceImpl recordingServiceImpl;

    @PostMapping(value = "/info/add", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addRecordingInfo(@RequestBody RecordingInfo recordingInfo) {
        return recordingServiceImpl.addRecording(recordingInfo);
    }

    @PostMapping(value = "/info/get", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getRecordingInfo(@RequestBody RecordingInfo recordingInfo) {
        return recordingServiceImpl.getRecordingInfo(recordingInfo);
    }

    @PostMapping(value = "/file/add/{userId}/recId/{recordingId}", consumes = "multipart/form-data")
    @ResponseBody
    public ResponseEntity addRecordingFile(@RequestParam("file") MultipartFile file,
                                           @PathVariable("userId") Integer userId,
                                           @PathVariable("recordingId") Integer recordingId) {
        return recordingServiceImpl.addRecordingFile(file, userId, recordingId);
    }



    @GetMapping(value = "/file/get/{recordingid}")
    @ResponseBody
    public void downloadRecordingFile(HttpServletResponse res,
                                                @PathVariable("recordingid") Integer recordingid) {
        recordingServiceImpl.downloadRecordingFile(res,recordingid);
    }



//    @PostMapping(value = "/recordinglist/add" , consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity addRecordingList(@RequestBody RecordingList recordingList){
//        return recordingServiceImpl.addRecordingList(recordingList);
//    }

    @GetMapping(value = "/recordinglist/{listId}/recordingId/{recordingId}/flag/{flag}", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateRecordingList(
            @PathVariable("listId") Integer listId,
            @PathVariable("recordingId") Integer recordingId,
            @PathVariable("flag") String flag) {
        return recordingServiceImpl.updateRecordingList(listId, recordingId, flag);
    }

    @GetMapping(value = "/recordinglist/get/{listId}", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getRecordingList(@PathVariable("listId") Integer listId) {
        return recordingServiceImpl.getRecordingList(listId);
    }
}
