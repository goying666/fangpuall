package com.renchaigao.fangpu.service.impl;
import com.renchaigao.fangpu.dao.RecordingInfo;
import com.renchaigao.fangpu.dao.RecordingList;
import com.renchaigao.fangpu.dao.mapper.RecordingInfoMapper;
import com.renchaigao.fangpu.dao.mapper.RecordingListMapper;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.function.normalFunc;
import com.renchaigao.fangpu.service.RecordingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

@Service
public class RecordingServiceImpl implements RecordingService{

    private static Logger logger = Logger.getLogger(RecordingServiceImpl.class);

    @Autowired
    RecordingInfoMapper recordingInfoMapper;

    @Autowired
    RecordingListMapper recordingListMapper;

    public ResponseEntity addRecording(RecordingInfo recordingInfo) {
        try {
            recordingInfo.setAddtime(new Date());
            recordingInfoMapper.insert(recordingInfo);
            return new ResponseEntity(RespCode.SUCCESS , recordingInfo);
        }catch (Exception e){
            return new ResponseEntity(RespCode.EXCEPTION,e);
        }
    }

    public ResponseEntity addRecordingFile(MultipartFile file,Integer userId,Integer recordingId) {

        String filePathOnService = creatRecodingPathOnservice(userId);
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
                        new File(filePathOnService + file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                logger.warn(e);
                return new ResponseEntity(RespCode.WARN,e.getMessage());
            } catch (IOException eIO) {
                logger.warn(eIO);
                return new ResponseEntity(RespCode.WARN,eIO.getMessage());
            }
//            acordingdao.updateAcoPathById(acordingId,filePathOnService);
//            acordingdao.updateAcoFileNameById(acordingId,file.getOriginalFilename());
            RecordingInfo recordingInfo = new RecordingInfo();
            recordingInfo.setId(recordingId);
            recordingInfo.setFilename(file.getOriginalFilename());
            recordingInfoMapper.updateByPrimaryKeySelective(recordingInfo);
//            acordingdao.updateAcoFileNameAndPathById(recordingId,file.getOriginalFilename(),filePathOnService);
            //待测试：更新后返回的形参recordingInfo是否涵盖了所有值需要测试；
            return new ResponseEntity(RespCode.SUCCESS,recordingInfo);
        } else {
            return new ResponseEntity(RespCode.FILENONE);
        }
    }

    /**********************************************
     * 功能：生成系统下 各用户对应的recording 目录
     * 入参：用户id  userId
     **********************************************/
    private String creatRecodingPathOnservice(Integer userId) {
        File file = new File("/fpfolder/recording/users/" + userId.toString());
        if (!file.exists())
            file.mkdirs();
        return "/fpfolder/recording/users/" + userId.toString() + "/";
    }

    public ResponseEntity getRecordingInfo(RecordingInfo recordingInfo) {
        try {
            recordingInfoMapper.selectByPrimaryKey(recordingInfo.getId());
            return new ResponseEntity(RespCode.SUCCESS , recordingInfo);
        }catch (Exception e){
            return new ResponseEntity(RespCode.EXCEPTION,e);
        }
    }

    public ResponseEntity addRecordingList(RecordingList recordingList){
        try {
            recordingListMapper.insert(recordingList);
            return new ResponseEntity(RespCode.SUCCESS , recordingList);
        }catch (Exception e){
            return new ResponseEntity(RespCode.EXCEPTION,e);
        }

    }

    public ResponseEntity updateRecordingList(Integer listId,
                                                Integer newRecordingId,
                                                String flagStr){
        try {
            RecordingList recordingList = recordingListMapper.selectByPrimaryKey(listId);
            if (flagStr.equals("add")){
                //将最新的id拼接进原有string的末尾；通过"-"
                recordingList.setRecordingliststr(recordingList.getRecordingliststr()
                        + "-" + newRecordingId.toString());
                recordingListMapper.updateByPrimaryKeySelective(recordingList);
                return new ResponseEntity(RespCode.ADDSUCCES,recordingList);
            }else if (flagStr.equals("delete")){
                recordingList.setRecordingliststr(normalFunc.deleteStrArg(
                        recordingList.getRecordingliststr(),
                        newRecordingId.toString()));
                recordingListMapper.updateByPrimaryKeySelective(recordingList);
                return new ResponseEntity(RespCode.DELETSUCCES,recordingList);
            }else {
                return new ResponseEntity(RespCode.WRONGINFO);
            }

        }catch (Exception e){
            return new ResponseEntity(RespCode.EXCEPTION);
        }
    }




}
