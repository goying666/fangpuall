package com.renchaigao.fangpu.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.*;
import com.renchaigao.fangpu.dao.mapper.*;
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

    @Autowired
    MyNumMapper myNumMapper;

    @Autowired
    MyZanMapper myZanMapper;


    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity userWxLogin(WxUserInfo wxUserInfo) {
        String urlStr = "https://api.weixin.qq.com/sns/jscode2session?"
                + "appid=" + "wx5f1755206e7513a2"
                + "&secret=" + "4522d3ddaadf5914e32a2b3090b170cd"
                + "&js_code=" + wxUserInfo.getJscode()
                + "&grant_type=authorization_code";
        try {
            org.springframework.http.ResponseEntity<String> responseEntity =
                    restTemplate.getForEntity(urlStr, String.class);
            JSONObject jsonUse = JSONObject.parseObject(responseEntity.getBody());
            String session_key = jsonUse.getString("session_key");
            String openid = jsonUse.getString("openid");
//            判断用户signature 正确性
            String ServiceSignatura = SHA1.encode(wxUserInfo.getRawData() + session_key);
            String wxSignatura = wxUserInfo.getSignature();
            String encryptedData = wxUserInfo.getEncryptedData();
            String iv = wxUserInfo.getIv();
            String jsondata = WXCore.decrypt(
                    "wx5f1755206e7513a2", encryptedData, session_key, iv);
            System.out.println(
                    "session_key is : " + session_key +
                            "unionid is : " + openid +
                            "ServiceSignatura is : " + ServiceSignatura +
                            "wxSignatura is : " + wxSignatura +
                            "encryptedData is : " + encryptedData +
                            "iv is : " + iv +
                            "jsondata is : " + jsondata);
            if (ServiceSignatura.equals(wxSignatura))
                return new ResponseEntity(RespCode.WXPASS, JSONObject.parseObject(jsondata));
            else
                return new ResponseEntity(RespCode.WXWRONG);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    //    //    用户登录
    public ResponseEntity addUser(UserInfo userInfo) {
        System.out.println(JSONObject.toJSONString(userInfo));
        try {
            UserInfo userUse = userInfoMapper.selectByUnionID(userInfo.getUnionid());//通过用户的unionid查询是否已在系统
            if (userUse != null) {
                return new ResponseEntity(RespCode.OLDUSER, userUse);
            } else {
                userInfoMapper.insertSelective(userInfo);
//                创建我的词条myterms
                MyTerms myTerms = new MyTerms();
                myTerms.setUserid(userInfo.getId());
                myTerms.setTermnum(0);
                mytermsmapper.insert(myTerms);

//                创建我的方言recording
                MyRecording myRecording = new MyRecording();
                myRecording.setUserid(userInfo.getId());
                myRecording.setRecordingnum(0);
                myRecordingMapper.insert(myRecording);

//                创建我的num：mynum
                MyNum myNum = new MyNum();
                myNum.setUserid(userInfo.getId());
                myNum.setRecordingnum(0);
                myNum.setZannum(0);
                myNum.setTermnum(0);
                myNum.setBadnum(0);
                myNumMapper.insertSelective(myNum);

                MyZanWithBLOBs myZanWithBLOBs = new MyZanWithBLOBs();
                myZanWithBLOBs.setUserid(userInfo.getId());
                myZanMapper.insertSelective(myZanWithBLOBs);

                userInfo.setMytermsid(myTerms.getId());
                userInfo.setMyrecordingid(myRecording.getId());


//                 更新userinfo数据
                userInfoMapper.updateByPrimaryKeySelective(userInfo);



//                户登录时间信息
                UserLogin userLogin = new UserLogin();
                userLogin.setLogindate(dateUse.DateToString(new Date()));
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
