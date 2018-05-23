package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.UserInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
//import com.renchaigao.fangpu.domain.response.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.soap.Text;
import java.util.HashMap;
import java.util.List;

@Service
public class ServiceTestImpl {

    RestTemplate restTemplate = new RestTemplate();

    public void test1() {
        System.out.println("run in : test1");

//        ResponseEntity<String> responseEntity =
//                restTemplate.getForEntity("https://api.weixin.qq.com/sns/jscode2session?appid=wx5f1755206e7513a2&secret=4522d3ddaadf5914e32a2b3090b170cd&js_code=011lA6Be1cxhLr0eW7Ce10adBe1lA6Bo&grant_type=authorization_code",
//                        String.class);

//        String list = restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session?appid=wx5f1755206e7513a2&secret=4522d3ddaadf5914e32a2b3090b170cd&js_code=011lA6Be1cxhLr0eW7Ce10adBe1lA6Bo&grant_type=authorization_code",
//                String.class);
//
//        System.out.println(list);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json");
//        requestHeaders.add("key2", "ddd");
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

        HashMap<String, String> map = new HashMap<>();
        map.put("Content-Type","application/json");
        UserInfo list = restTemplate.getForObject(
                "http://127.0.0.1:7899/user/getinfotest", UserInfo.class,map);
        System.out.println(JSONObject.toJSONString(list));


//                System.out.println(JSONObject.toJSONString(responseEntity.getBody()));


//        List<UserInfo> list = restTemplate.getForObject("http://127.0.0.1:7899/user/getinfotest/{id}", List.class,67);
//        System.out.println(JSONObject.toJSONString(list));
    }
//    public void test2() {
//        List<UserInfo> list = restTemplate.getForObject(
//                "http://127.0.0.1:7899/user/getinfo/67", List.class);
//        System.out.println(JSONObject.toJSONString(list));
//    }
//    public void test2(){
//        ResponseEntity<UserInfo> responseEntity =
//                restTemplate.getForEntity("http://127.0.0.1:7899/user/getinfo/67",
//                        UserInfo.class);
////            org.springframework.http.ResponseEntity responseEntity = ;
//        System.out.println(JSONObject.toJSONString(responseEntity.getBody()));
//        System.out.println(responseEntity.getBody().getAddress());
//    }
//    public void test5() {
////        ResponseEntity<UserInfo> responseEntity =
////                restTemplate.getForEntity("http://127.0.0.1:7899/user/getinfo/67",
////                        UserInfo.class);
//        ResponseEntity<UserInfo> responseEntity =
//                restTemplate.getForEntity("http://127.0.0.1:7899/user/getinfotest/67",
//                        UserInfo.class);
////            org.springframework.http.ResponseEntity responseEntity = ;
////    System.out.println(JSONObject.toJSONString(responseEntity.getData()));
//        System.out.println(JSONObject.toJSONString(responseEntity));
//        System.out.println(JSONObject.toJSONString(responseEntity.getBody()));
////        System.out.println(responseEntity.getBody().getAddress());
//    }
//    public void test4() {
//        ResponseEntity<String> responseEntity =
//                restTemplate.getForEntity("https://api.weixin.qq.com/sns/jscode2session?appid=wx5f1755206e7513a2&secret=4522d3ddaadf5914e32a2b3090b170cd&js_code=011lA6Be1cxhLr0eW7Ce10adBe1lA6Bo&grant_type=authorization_code",
//                        String.class);
////            org.springframework.http.ResponseEntity responseEntity = ;
////    System.out.println(JSONObject.toJSONString(responseEntity.getData()));
//        System.out.println(JSONObject.toJSONString(responseEntity));
//        System.out.println(JSONObject.toJSONString(responseEntity.getBody()));
////        System.out.println(responseEntity.getBody().getAddress());
//    }
//public void test4(){
//        ResponseEntity responseEntity =
//                restTemplate.getForEntity("https://api.weixin.qq.com/sns/jscode2session?appid=wx5f1755206e7513a2&secret=4522d3ddaadf5914e32a2b3090b170cd&js_code=011lA6Be1cxhLr0eW7Ce10adBe1lA6Bo&grant_type=authorization_code",
//                        ResponseEntity.class);
////            org.springframework.http.ResponseEntity responseEntity = ;
//        System.out.println(JSONObject.toJSONString(responseEntity.getBody()));
//        System.out.println(JSONObject.toJSONString(responseEntity));
////        System.out.println(JSONObject.toJSONString(responseEntity.getBody()));
////        System.out.println(responseEntity.getBody().getAddress());
//    }

}
