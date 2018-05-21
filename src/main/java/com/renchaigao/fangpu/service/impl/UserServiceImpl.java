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
import com.renchaigao.fangpu.function.dateUse;
import com.renchaigao.fangpu.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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


    public ResponseEntity getUserinfo(Integer userid){
        System.out.println("userid is : " + userid);
        try {
            return new ResponseEntity(RespCode.SUCCESS, userInfoMapper.selectByPrimaryKey(userid));
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity userLogin(UserLogin userLogin) {
        userLogin.setLogindate(dateUse.DateToString(new Date()));//新增用户登录时间信息
        userLoginMapper.insert(userLogin);
        System.out.println("userLogin.userId is : " + userLogin.getUserid());
        return new ResponseEntity(RespCode.NEWUSER, userLogin);
    }

//
    public ResponseEntity userAddressAdd(UserInfo userInfo){
        System.out.println("userInfo.getId is : " + userInfo.getId());
        System.out.println("userInfo.getAddress is : " + userInfo.getAddress());
        try {
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
            return new ResponseEntity(RespCode.SUCCESS, userInfo);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity userAddressUpdate(UserInfo userInfo){
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
