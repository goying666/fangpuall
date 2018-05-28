package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.RecordingInfo;
import com.renchaigao.fangpu.dao.TermInfo;
import com.renchaigao.fangpu.dao.mapper.TermInfoMapper;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.domain.search.SearchTerm;
import com.renchaigao.fangpu.service.SearchTermInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchTermInfoServiceImpl implements SearchTermInfoService{
    @Autowired
    TermInfoMapper termInfoMapper;

    public ResponseEntity searchTermInfo(SearchTerm searchTerm){
        try {

            long startTime = System.currentTimeMillis(); //程序开始记录时间
            JSONObject retJson = searchTermInfoFromSearch(searchTerm.getSearchString());

//             JSONObject test = JSON.
            JSONObject hitsJson = JSONObject.parseObject(JSONObject.toJSONString(retJson.get("hits")));
            JSONArray hitsJsonArg = JSONArray.parseArray(JSONArray.toJSONString(hitsJson.get("hits")));

            List<TermInfo> termList = new ArrayList<>();
            List<String> termIdList = new ArrayList<>();
            for(int i = 0 ; i < hitsJsonArg.size() ; i++){
                termIdList.add(i,hitsJsonArg.getJSONObject(i).get("_id").toString());
            }
            Integer recordingnum = 0;
            for(int i = 0 ; i < termIdList.size() ; i++){
                termList.add(i,termInfoMapper.selectByPrimaryKey(
                        Integer.parseInt(termIdList.get(i))));
                recordingnum =+termList.get(i).getRecordingnum();
            }
            long spendTime = System.currentTimeMillis() - startTime;
            retJson.put("termlist",termList);
            retJson.put("spendtime",spendTime);
            retJson.put("termnum",termIdList.size());
            retJson.put("recordingnum",recordingnum);
            return new ResponseEntity(RespCode.SUCCESS,retJson);
        }catch (Exception e){
            JSONObject retJson = new JSONObject();
            retJson.put("termlist",0);
            retJson.put("spendtime","***");
            retJson.put("termnum",0);
            retJson.put("recordingnum",0);
            return new ResponseEntity(RespCode.EXCEPTION,retJson);
        }

    }

    private JSONObject searchTermInfoFromSearch(String searchString){
        RestTemplate template = new RestTemplate();
        String url = "http://47.106.149.105:9200/es/terminfo/_search";
        HttpHeaders headers = new HttpHeaders();
//  也支持中文
        MultiValueMap<String, Object> params= new LinkedMultiValueMap<>();
        headers.set("Accept-Charset", "utf-8");
        headers.set("Content-type", "application/json; charset=utf-8");  //header的规定
        String strMessage = "{\"query\":{\"match\":{\"terminfo\":\" "
                + searchString + "\"}}}";
        HttpEntity<String> formEntity = new HttpEntity<String>(
                strMessage, headers);
        String retStr = template.postForObject(url,formEntity,String.class);
        System.out.println(retStr);
        return JSONObject.parseObject(retStr);
    }

}
