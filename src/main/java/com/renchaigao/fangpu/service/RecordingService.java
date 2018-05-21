package com.renchaigao.fangpu.service;

import com.renchaigao.fangpu.dao.RecordingInfo;
import com.renchaigao.fangpu.dao.RecordingList;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface RecordingService {

    ResponseEntity addRecording(RecordingInfo recordingInfo);

    ResponseEntity getRecordingInfo(RecordingInfo recordingInfo);

    ResponseEntity addRecordingFile(MultipartFile file, Integer userId, Integer recordingId);

    ResponseEntity downloadRecordingFile(HttpServletResponse res, @PathVariable("path") String path, @PathVariable("filename") String filename);

    ResponseEntity addRecordingList(RecordingList recordingList);

    ResponseEntity updateRecordingList(Integer listId, Integer newRecordingId, String flagStr);

}
