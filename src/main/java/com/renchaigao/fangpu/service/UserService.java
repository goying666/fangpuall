package com.renchaigao.fangpu.service;

import com.renchaigao.fangpu.dao.UserInfo;
import com.renchaigao.fangpu.dao.UserLogin;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.domain.wx.WxUserInfo;

import java.util.List;


public interface UserService {

//    用户登录
//    ResponseEntity addUser(UserInfo userInfo);
    ResponseEntity getUserinfo(Integer userid);
    UserInfo getUserinfoTest();
    ResponseEntity userLogin(UserLogin userLogin);

    ResponseEntity userAddressAdd(UserInfo userInfo);

    ResponseEntity userAddressUpdate(UserInfo userInfo);

    ResponseEntity userWxLogin(WxUserInfo wxUserInfo);
//
//    public String searchUserByNickname(String nickName);
//
//    public String searchUserById(Integer userId);
//


}
