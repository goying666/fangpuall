package com.renchaigao.fangpu.controller;


import com.renchaigao.fangpu.dao.UserInfo;
import com.renchaigao.fangpu.dao.UserLogin;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user/")
public class UserController {
    @Autowired
    private UserServiceImpl usrserviceimpl;

    @PostMapping(value = "/addinfo" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity userAddInfo(@RequestBody UserInfo userInfo){
        return usrserviceimpl.addUser(userInfo);
    }

    @PostMapping(value = "/login" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity userLogin(@RequestBody UserLogin userLogin){
        return usrserviceimpl.userLogin(userLogin);
    }

//    @PostMapping(value = "/address/add" , consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity userAddressAdd(@RequestBody UserInfo userInfo){
//        return usrserviceimpl.userAddressAdd(userInfo);
//    }
//    @PostMapping(value = "/address/update" , consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity userAddressUpdate(@RequestBody UserInfo userInfo){
//        return usrserviceimpl.userAddressUpdate(userInfo);
//    }

//    @PostMapping(value = "/logintest1" , consumes = "application/json")
//    @ResponseBody
//    public ResponseEntity testUse1(@RequestBody UserInfo userInfo){
//        return usrserviceimpl.userLoginTest(userInfo);
//    }
}
