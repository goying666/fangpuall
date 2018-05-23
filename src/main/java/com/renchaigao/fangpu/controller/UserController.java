package com.renchaigao.fangpu.controller;


import com.renchaigao.fangpu.dao.UserInfo;
import com.renchaigao.fangpu.dao.UserLogin;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.domain.wx.WxUserInfo;
import com.renchaigao.fangpu.service.impl.ServiceTestImpl;
import com.renchaigao.fangpu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserServiceImpl usrserviceimpl;
    @Autowired
    private ServiceTestImpl serviceTest;

    @PostMapping(value = "/wxlogin" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity userWxLogin(@RequestBody WxUserInfo wxUserInfo){
        return  usrserviceimpl.userWxLogin(wxUserInfo);
    }

    @PostMapping(value = "/addinfo" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity userAddInfo(@RequestBody UserInfo userInfo){
        return usrserviceimpl.addUser(userInfo);
    }

    @GetMapping(value = "/getinfo/{userid}" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity userAddInfo(@PathVariable("userid") Integer userid){
        return usrserviceimpl.getUserinfo(userid);
    }

    @PostMapping(value = "/login" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity userLogin(@RequestBody UserLogin userLogin){
        return usrserviceimpl.userLogin(userLogin);
    }

    @PostMapping(value = "/address/add" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity userAddressAdd(@RequestBody UserInfo userInfo){
        return usrserviceimpl.userAddressAdd(userInfo);
    }
    @PostMapping(value = "/address/update" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity userAddressUpdate(@RequestBody UserInfo userInfo){
        return usrserviceimpl.userAddressUpdate(userInfo);
    }

//    @PostMapping(value = "/logintest1" , consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity testUse1(@RequestBody UserInfo userInfo){
//        return usrserviceimpl.userLoginTest(userInfo);
//    }
}
