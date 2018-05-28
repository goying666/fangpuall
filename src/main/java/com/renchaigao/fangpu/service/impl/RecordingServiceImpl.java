package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.*;
import com.renchaigao.fangpu.dao.mapper.*;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.function.normalFunc;
import com.renchaigao.fangpu.service.RecordingService;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RecordingServiceImpl implements RecordingService {

    private static Logger logger = Logger.getLogger(RecordingServiceImpl.class);

    @Autowired
    RecordingInfoMapper recordingInfoMapper;

    @Autowired
    RecordingListMapper recordingListMapper;

    @Autowired
    MyserviceImpl myservice;

    @Autowired
    MyRecordingMapper myRecordingMapper;

    @Autowired
    RecordingNumListMapper recordingNumListMapper;

    @Autowired
    TermInfoMapper termInfoMapper;

    public ResponseEntity addRecording(RecordingInfo recordingInfo) {
        try {
            recordingInfo.setAddtime(new Date());
            recordingInfoMapper.insert(recordingInfo);
//            增加recording的zanlist
            RecordingNumListWithBLOBs recordingNumListWithBLOBs = new RecordingNumListWithBLOBs();
            recordingNumListWithBLOBs.setRecordingid(recordingInfo.getId());
            recordingNumListMapper.insertSelective(recordingNumListWithBLOBs);

//            增加terms的recordingnum数
            TermInfo termInfo = termInfoMapper.selectByPrimaryKey(recordingInfo.getTermid());
            termInfo.setRecordingnum(termInfo.getRecordingnum() + 1);
            termInfoMapper.updateByPrimaryKeySelective(termInfo);

            return new ResponseEntity(RespCode.SUCCESS, recordingInfo);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity addRecordingFile(MultipartFile file, Integer userId, Integer recordingId) {

        String filePathOnService = creatRecodingPathOnservice(userId);
        System.out.println("filePathOnService is : " + filePathOnService);
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
                        new File(filePathOnService + file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                logger.warn(e);
                return new ResponseEntity(RespCode.WARN, e.getMessage());
            } catch (IOException eIO) {
                logger.warn(eIO);
                return new ResponseEntity(RespCode.WARN, eIO.getMessage());
            }
//            acordingdao.updateAcoPathById(acordingId,filePathOnService);
//            acordingdao.updateAcoFileNameById(acordingId,file.getOriginalFilename());
            RecordingInfo recordingInfo = new RecordingInfo();
            recordingInfo.setId(recordingId);
            recordingInfo.setFilename(file.getOriginalFilename());
            recordingInfo.setPath(filePathOnService);
            recordingInfoMapper.updateByPrimaryKeySelective(recordingInfo);
//            acordingdao.updateAcoFileNameAndPathById(recordingId,file.getOriginalFilename(),filePathOnService);
            //待测试：更新后返回的形参recordingInfo是否涵盖了所有值需要测试；
            return new ResponseEntity(RespCode.SUCCESS, recordingInfo);
        } else {
            return new ResponseEntity(RespCode.FILENONE);
        }
    }




    public void downloadRecordingFile(HttpServletResponse response, Integer recordingid,HttpServletRequest request) {
        System.out.println("enter in downloadRecordingFile");
        RecordingInfo recordingInfo = recordingInfoMapper.selectByPrimaryKey(recordingid);
        String path = recordingInfo.getPath();
        String filename = recordingInfo.getFilename();
        File downloadFile = new File(path+filename);
        ServletContext context = request.getServletContext();

        String mimeType = context.getMimeType(path+filename);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
            System.out.println("context getMimeType is null");
        }

        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // Copy the stream to the response's output stream.
        try {
            InputStream myStream = new FileInputStream(path+filename);
            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
//    public void downloadRecordingFile(HttpServletResponse res, Integer recordingid) {
//        System.out.println("enter in downloadRecordingFile");
//        RecordingInfo recordingInfo = recordingInfoMapper.selectByPrimaryKey(recordingid);
//        String path = recordingInfo.getPath();
//        String filename = recordingInfo.getFilename();
//
//        res.setHeader("Content-type", "audio/mp3");
////        res.setContentType("application/octet-stream");
//        res.setHeader("Content-Disposition", "attachment;filename=" + filename);
//        byte[] buff = new byte[1024];
//        BufferedInputStream bis = null;
//        OutputStream os = null;
//        try {
//            os = res.getOutputStream();
//            bis = new BufferedInputStream(new FileInputStream(
//                    new File(path + filename)));
//            int i = bis.read(buff);
//            while (i != -1) {
//                os.write(buff, 0, buff.length);
//                os.flush();
//                i = bis.read(buff);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (bis != null) {
//                try {
//                    bis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

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
            return new ResponseEntity(RespCode.SUCCESS, recordingInfoMapper.selectByPrimaryKey(recordingInfo.getId()));
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity addRecordingList(RecordingList recordingList) {
        try {
            recordingListMapper.insert(recordingList);
            return new ResponseEntity(RespCode.SUCCESS, recordingList);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }

    }

    public ResponseEntity updateRecordingList(Integer listId,
                                              Integer newRecordingId,
                                              String flagStr) {
        try {
            RecordingList recordingList = recordingListMapper.selectByPrimaryKey(listId);
            if (flagStr.equals("add")) {
                //将最新的id拼接进原有string的末尾；通过"-"

                if (recordingList.getRecordingliststr() != null)
                    recordingList.setRecordingliststr(recordingList.getRecordingliststr() + "-" + newRecordingId.toString());
                else
                    recordingList.setRecordingliststr(newRecordingId.toString());

                recordingListMapper.updateByPrimaryKeySelective(recordingList);
                return new ResponseEntity(RespCode.ADDSUCCES, recordingList);
            } else if (flagStr.equals("delete")) {
                recordingList.setRecordingliststr(normalFunc.deleteStrArg(
                        recordingList.getRecordingliststr(),
                        newRecordingId.toString()));
                recordingListMapper.updateByPrimaryKeySelective(recordingList);
                return new ResponseEntity(RespCode.DELETSUCCES, recordingList);
            } else {
                return new ResponseEntity(RespCode.WRONGINFO);
            }

        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION);
        }
    }

    public ResponseEntity getRecordingList(Integer id) {
        try {
            RecordingList recordingList = recordingListMapper.selectByPrimaryKey(id);
            List<String> termrecordingList = Arrays.asList(recordingList.getRecordingliststr().split("-"));
            List<RecordingInfo> recordingInfos = new ArrayList<>();
            for (String str : termrecordingList) {
                recordingInfos.add(recordingInfoMapper.selectByPrimaryKey(Integer.parseInt(str)));
            }
            return new ResponseEntity(RespCode.SUCCESS, recordingInfos);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }


}
