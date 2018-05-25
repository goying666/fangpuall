package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.RecordingList;
import com.renchaigao.fangpu.dao.TermInfo;
import com.renchaigao.fangpu.dao.mapper.RecordingListMapper;
import com.renchaigao.fangpu.dao.mapper.TermInfoMapper;
import com.renchaigao.fangpu.dao.mapper.UserInfoMapper;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.service.TermService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TermServiceImpl implements TermService {

    private static Logger logger = Logger.getLogger(TermServiceImpl.class);
    @Autowired
    TermInfoMapper termInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RecordingListMapper recordingListMapper;

    public ResponseEntity addTerm(TermInfo termInfo) {
        System.out.println(JSONObject.toJSONString(termInfo));
        try{
            termInfo.setAddtime(new Date());//小程序端没有添加创建时间，由后台填写该字段；
            termInfoMapper.insertSelective(termInfo);
//            创建term的recordinglist
            RecordingList recordingList = new RecordingList();
            recordingList.setTermid(termInfo.getId());
            recordingListMapper.insertSelective(recordingList);

            termInfo.setRecordingids(recordingList.getId().toString());
            termInfoMapper.updateByPrimaryKeySelective(termInfo);

//            添加term信息至搜索库
            addTermInfoToSearch(termInfo);

            return new ResponseEntity(RespCode.SUCCESS, termInfo);
        }catch (Exception e){
            logger.info(e);
            return new ResponseEntity(RespCode.EXCEPTION);
        }
    }


    private JSONObject addTermInfoToSearch(TermInfo termInfo){
        JSONObject retJson = new JSONObject();
        RestTemplate template = new RestTemplate();
        Map<String , Object> paras = new HashMap<>();
//        paras.put("content",termInfo.getContent());
        paras.put("id",termInfo.getId());
        String url = "http://47.106.149.105:9200/es/terminfo/{id}";

//        String url = "http://localhost/mirana-ee/app/login";
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_JSON);
//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
//  也支持中文
        params.add("termid",termInfo.getId().toString());
        params.add("terminfo",termInfo.getContent());
//        params.add("username", "用户名");
//        params.add("password", "123456");
        HttpEntity<MultiValueMap<String, String>> requestEntity =
                new HttpEntity<MultiValueMap<String, String>>(params, headers);

        String retStr = template.postForObject(url,requestEntity,String.class,paras);
        System.out.println(retStr);
        retJson = JSONObject.parseObject(retStr);
        return retJson;
    }

    public ResponseEntity getTermInfo(TermInfo termInfo) {
        long startTime = System.currentTimeMillis(); //程序开始记录时间
        try {
            return new ResponseEntity(RespCode.SUCCESS ,
                    termInfoMapper.selectByPrimaryKey(termInfo.getId()));
        }catch (Exception e){
            return new ResponseEntity(RespCode.EXCEPTION,e);
        }
    }

    public ResponseEntity updateTerm(TermInfo termInfo) {

        return new ResponseEntity(RespCode.SUCCESS);
    }



    public ResponseEntity deleteTerm(TermInfo termInfo) {

        return new ResponseEntity(RespCode.SUCCESS);
    }

    ;


}
