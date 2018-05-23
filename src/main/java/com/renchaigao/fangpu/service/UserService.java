package com.renchaigao.fangpu.service;

import com.renchaigao.fangpu.dao.UserInfo;
import com.renchaigao.fangpu.dao.UserLogin;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.domain.wx.WxUserInfo;


public interface UserService {

//    用户登录
//    ResponseEntity addUser(UserInfo userInfo);
    ResponseEntity getUserinfo(Integer userid);

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
