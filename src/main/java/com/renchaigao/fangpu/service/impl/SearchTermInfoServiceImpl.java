package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.TermInfo;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.domain.search.SearchTerm;
import com.renchaigao.fangpu.service.SearchTermInfoService;
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


    public ResponseEntity searchTermInfo(SearchTerm searchTerm){
        try {
            JSONObject retJson = searchTermInfoFromSearch(searchTerm.getSearchString());
            List<String> termIdList = new ArrayList<>();
            List<TermInfo> termList = new ArrayList<>();
            return new ResponseEntity(RespCode.SUCCESS                     );
        }catch (Exception e){
            return new ResponseEntity(RespCode.EXCEPTION,e);
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
//        System.out.println(retStr);
        return JSONObject.parseObject(retStr);
    }

}
