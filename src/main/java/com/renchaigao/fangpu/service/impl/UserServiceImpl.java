package com.renchaigao.fangpu.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.MyRecording;
import com.renchaigao.fangpu.dao.MyTerms;
import com.renchaigao.fangpu.dao.UserInfo;
import com.renchaigao.fangpu.dao.UserLogin;
import com.renchaigao.fangpu.dao.mapper.MyRecordingMapper;
import com.renchaigao.fangpu.dao.mapper.MyTermsMapper;
import com.renchaigao.fangpu.dao.mapper.UserInfoMapper;
import com.renchaigao.fangpu.dao.mapper.UserLoginMapper;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import com.renchaigao.fangpu.domain.wx.WxUserInfo;
import com.renchaigao.fangpu.function.dateUse;
import com.renchaigao.fangpu.function.wxlogin.SHA1;
import com.renchaigao.fangpu.function.wxlogin.WXCore;
import com.renchaigao.fangpu.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
//@Transactional
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;


    @Autowired
    MyTermsMapper mytermsmapper;

    @Autowired
    MyRecordingMapper myRecordingMapper;

    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity userWxLogin(WxUserInfo wxUserInfo) {
        String urlStr = "https://api.weixin.qq.com/sns/jscode2session?"
                + "appid=" + "wx5f1755206e7513a2"
                + "&secret=" + "4522d3ddaadf5914e32a2b3090b170cd"
                + "&js_code=" + wxUserInfo.getJscode()
                + "&grant_type=authorization_code";
        try {
            JSONObject jsonUse = new JSONObject();
            org.springframework.http.ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(urlStr,String.class);
            jsonUse = JSONObject.parseObject(responseEntity.getBody());
//
//            WxUserInfo wxUserInfo1 = JSONObject.parse(jsonUse.to);
            String session_key = jsonUse.getString("session_key");
            String unionid = jsonUse.getString("unionid");
//            判断用户signature 正确性
            String ServiceSignatura = SHA1.encode(jsonUse.getString("rawData") + session_key);
            String wxSignatura = jsonUse.getString("signature");
            String encryptedData = jsonUse.getString("encryptedData");
            String iv = jsonUse.getString("iv");
            String jsondata = WXCore.decrypt(
                    "wx5f1755206e7513a2" , encryptedData , session_key , iv);

            System.out.println(
                    "session_key is : " + session_key +
                            "unionid is : " + unionid +
                            "ServiceSignatura is : " + ServiceSignatura +
                            "wxSignatura is : " + wxSignatura +
                            "encryptedData is : " + encryptedData +
                            "iv is : " + iv +
                            "jsondata is : " + jsondata);
            return new ResponseEntity(RespCode.SUCCESS,JSONObject.parseObject(jsondata));
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    //    //    用户登录
    public ResponseEntity addUser(UserInfo userInfo) {
        System.out.println(JSONObject.toJSONString(userInfo));
        try {
//            UserInfo userUse = userInfoMapper.selectByPrimaryKey(userInfo.getId());//通过用户的unionid查询是否已在系统
            UserInfo userUse = userInfoMapper.selectByUnionID(userInfo.getUnionid());//通过用户的unionid查询是否已在系统
            if (userUse != null) {
                return new ResponseEntity(RespCode.OLDUSER, userUse);
            } else {

                userInfoMapper.insertSelective(userInfo);
//                创建我的系列
                MyTerms myTerms = new MyTerms();
                myTerms.setUserid(userInfo.getId());
                mytermsmapper.insert(myTerms);

                MyRecording myRecording = new MyRecording();
                myRecording.setUserid(userInfo.getId());
                myRecordingMapper.insert(myRecording);

                userInfo.setMytermsid(myTerms.getId());
                userInfo.setMyrecordingid(myRecording.getId());

                userInfoMapper.updateByPrimaryKeySelective(userInfo);

                UserLogin userLogin = new UserLogin();
                userLogin.setLogindate(dateUse.DateToString(new Date()));//新增用户登录时间信息
                userLoginMapper.insert(userLogin);

                return new ResponseEntity(RespCode.NEWUSER, userInfo);
            }
        } catch (Exception e) {
            logger.info("Exception e : " + e);
            return new ResponseEntity(RespCode.WARN, e);
        }
    }


    public ResponseEntity getUserinfo(Integer userid) {
        System.out.println("userid is : " + userid);
        try {
            return new ResponseEntity(RespCode.SUCCESS, userInfoMapper.selectByPrimaryKey(userid));
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }
    public UserInfo getUserinfoTest() {
        System.out.println("run in : getUserinfoTest");
//        List<UserInfo> list = new ArrayList<>();
//        UserInfo userInfo1 = new UserInfo();
//        UserInfo userInfo2 = new UserInfo();
        UserInfo userInfo3 = new UserInfo();
//        userInfo1.setId(1);
//        userInfo2.setId(2);
        userInfo3.setId(3);
//        list.set(0,userInfo1);
//        list.set(1,userInfo2);
//        list.set(2,userInfo3);
        return userInfo3;
    }


    public ResponseEntity userLogin(UserLogin userLogin) {
        userLogin.setLogindate(dateUse.DateToString(new Date()));//新增用户登录时间信息
        userLoginMapper.insert(userLogin);
        System.out.println("userLogin.userId is : " + userLogin.getUserid());
        return new ResponseEntity(RespCode.NEWUSER, userLogin);
    }

    //
    public ResponseEntity userAddressAdd(UserInfo userInfo) {
        System.out.println("userInfo.getId is : " + userInfo.getId());
        System.out.println("userInfo.getAddress is : " + userInfo.getAddress());
        try {
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
            return new ResponseEntity(RespCode.SUCCESS, userInfo);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity userAddressUpdate(UserInfo userInfo) {
        try {
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
            return new ResponseEntity(RespCode.SUCCESS, userInfo);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }
//
//
//    public ResponseEntity userAddressUpdate(UserInfo userInfo){
//        User userUse = null;
//        ResponseEntity userLoginRet = new ResponseEntity(RespCode.WARN, null);
//        try {
//            userUse = userInfoMapper.findUserByNikename(userInfo.getNickName());
//            if (userUse != null) {
//                userInfoMapper.updateUserAddressByID(userUse.getId(),userInfo.getAddress());
//                userUse.setAddress(userInfo.getAddress());
//                userLoginRet = new ResponseEntity(RespCode.SUCCESS, userUse);
//                logger.info("userAddressUpdate finish : user nickname:"
//                        + userInfo.getNickName() + " !");
//            } else {
//                logger.info("user "+ userInfo.getNickName() +" is not in system!");
//            }
//        } catch (Exception e) {
//            logger.info("Exception e : " + e);
//            userLoginRet.setData(e);
//        }
//        return userLoginRet;
//    };
//
//    public ResponseEntity userLoginTest(UserInfo userInfo) {
//        User userUse = null;
//        Integer i = null ,j = null;
//
//        i = userInfoMapper.addUserinfo(userInfo);
//        j = userInfoMapper.addUserLogin(userInfo);
//        logger.info("info i is : " + i);
//        logger.info("info j is : " + j);
//        ResponseEntity userLoginRet = new ResponseEntity(RespCode.WARN, null);
//        try {
//            userUse = userInfoMapper.findUserByNikename(userInfo.getNickName());
//            if (userUse != null) {
//                userLoginRet = new ResponseEntity(RespCode.SUCCESS, userUse);
//                logger.info("SUCCESS: user nickname:" + userInfo.getNickName() + "is find in system!");
//            } else {
////                i = userInfoMapper.addUserinfo(userInfo);
////                j = userInfoMapper.addUserLogin(userInfo);
////                logger.info("i is : " + i);
////                logger.info("j is : " + j);
//                if (i != null &&  j != null) {
//                    logger.info("SUCCESS: add user : " + userInfo.getNickName() + " in system");
//                    userLoginRet = new ResponseEntity(RespCode.SUCCESS, userInfo);
//                } else {
//                    logger.info("fail ：add user : " + userInfo.getNickName() + " in system fail！");
//                }
//            }
//        } catch (Exception e) {
//            logger.info("Exception e : " + e);
//        }
//        return userLoginRet;
//    }
//
//    public String searchUserByNickname(String nickName) {
//        String ret = "";
//        try {
//            ret = userInfoMapper.findUserByNikename(nickName).getId().toString();
//        } catch (Exception e) {
//            logger.info(e);
//        }
//        logger.info("searchUserByNickname ret is : " + ret);
//        return ret;
//    }
//    public String searchUserById(Integer userId) {
//        String ret = "";
//
//        return ret;
//    }

}
