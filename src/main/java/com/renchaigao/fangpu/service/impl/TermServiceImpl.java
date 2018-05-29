package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.MyTerms;
import com.renchaigao.fangpu.dao.RecordingList;
import com.renchaigao.fangpu.dao.TermInfo;
import com.renchaigao.fangpu.dao.mapper.MyTermsMapper;
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

import java.util.*;

@Service
public class TermServiceImpl implements TermService {

    private static Logger logger = Logger.getLogger(TermServiceImpl.class);
    @Autowired
    TermInfoMapper termInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RecordingListMapper recordingListMapper;
    @Autowired
    MyTermsMapper myTermsMapper;

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

    public ResponseEntity deleteTerm(Map<String,Object> reqMap) {
        try {
            JSONObject retJson = new JSONObject();
//            删除terminfo
             List<String> idlist = Arrays.asList(reqMap.get("deleteidlist").toString().split("-"));
            for (String id : idlist){
                termInfoMapper.deleteByPrimaryKey(Integer.parseInt(id));
            }
//            删除myterm中对应terms的信息；
            MyTerms myTerms = myTermsMapper.selectByPrimaryKey(Integer.parseInt(reqMap.get("userid").toString()));
            List<String> termList = Arrays.asList(myTerms.getAlltermlist().split("-"));
            String newTermString = null;
            boolean strDeleteFlag = false;
            for (String str:termList){
                for (String strid: idlist){
                    if (str == strid){
                        strDeleteFlag = true;
                        break;
                    }
                }
                if (!strDeleteFlag){
                    if (newTermString != null)
                        newTermString = newTermString + "-" +str;
                    else
                        newTermString = str;
                }
            }
            myTerms.setAlltermlist(newTermString);
            myTermsMapper.updateByPrimaryKeySelective(myTerms);
            return new ResponseEntity(RespCode.SUCCESS,myTerms);
        }catch (Exception e){
            return new ResponseEntity(RespCode.EXCEPTION,e);
        }
    }
}
