package com.renchaigao.fangpu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.renchaigao.fangpu.dao.*;
import com.renchaigao.fangpu.dao.mapper.*;
import com.renchaigao.fangpu.domain.response.RespCode;
import com.renchaigao.fangpu.domain.response.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NumServiceImpl {

    @Autowired
    MyZanMapper myZanMapper;

    @Autowired
    MyNumMapper myNumMapper;

    @Autowired
    RecordingInfoMapper recordingInfoMapper;

    @Autowired
    RecordingNumListMapper recordingNumListMapper;

    @Autowired
    TermInfoMapper termInfoMapper;

    public ResponseEntity numZanControl(Map<String, Object> reqMap) {
        try {
            //        判断用户是否已经赞过该词条，
            Integer userid = Integer.parseInt(reqMap.get("userid").toString());
            Integer recordingid = Integer.parseInt(reqMap.get("recordingid").toString());
            Integer termid = Integer.parseInt(reqMap.get("termid").toString());
            TermInfo termInfo = termInfoMapper.selectByPrimaryKey(termid);
            MyZanWithBLOBs myZanWithBLOBs = myZanMapper.selectByUserId(userid);
            if (myZanWithBLOBs != null) {
                String myZanListStr = myZanWithBLOBs.getZanrecordinglist();
                if (myZanListStr != null) {
//                  1、用户myzan记录修改
                    String[] zanstringlist = myZanListStr.split("-");
                    List<String> zanlist = new ArrayList<String>();
                    Collections.addAll(zanlist, zanstringlist);

                    boolean YesOrNo = zanlist.contains(recordingid.toString());
//                  赞过：用户my赞删除记录；
                    if (YesOrNo)
                        zanlist.remove(recordingid.toString());
//                  没赞：用户my赞增加记录；
                    else
                        zanlist.add(recordingid.toString());
                    String zanlistNew = null;
                    for (String stri : zanlist) {
                        if (zanlistNew == null)
                            zanlistNew = stri;
                        else
                            zanlistNew = zanlistNew + "-" + stri;
                    }
                    myZanWithBLOBs.setZanrecordinglist(zanlistNew);
                    myZanMapper.updateZanByPrimaryKey(myZanWithBLOBs);

//                  2、方言zannum修改
                    RecordingInfo recordingInfo = recordingInfoMapper.selectByPrimaryKey(recordingid);
//                  赞过：方言recordingInfo赞-1；
                    if (YesOrNo) {
                        termInfo.setZannum(termInfo.getZannum() - 1);
                        recordingInfo.setZannum(recordingInfo.getZannum() - 1);
                    }
//                  没赞：方言recordingInfo赞+1；
                    else {
                        termInfo.setZannum(termInfo.getZannum() + 1);
                        recordingInfo.setZannum(recordingInfo.getZannum() + 1);
                    }
                    termInfoMapper.updateByPrimaryKeySelective(termInfo);
                    recordingInfoMapper.updateByPrimaryKeySelective(recordingInfo);

//                   3、 方言赞list用户操作
                    RecordingNumListWithBLOBs recordingNumListWithBLOBs = recordingNumListMapper.selectByRecordingId(recordingid);
                    String recordingZanUserListStr = recordingNumListWithBLOBs.getZanuseridlist();
                    if (recordingZanUserListStr != null) {
                        String[] userStrListUse = recordingZanUserListStr.split("-");
                        List<String> userlist = new ArrayList<String>();
                        Collections.addAll(userlist, userStrListUse);
                        if (YesOrNo)
                            userlist.remove(userid.toString());//赞过:方言赞list删除用户；
                        else
                            userlist.add(userid.toString());//没赞:方言赞list增加用户；
                        String userlistNew = null;
                        for (String strj : userlist) {
                            if (userlistNew == null)
                                userlistNew = strj;
                            else userlistNew = userlistNew + "-" + strj;
                        }
                        recordingNumListWithBLOBs.setZanuseridlist(userlistNew);
                    } else
                        recordingNumListWithBLOBs.setZanuseridlist(userid.toString());
                    recordingNumListMapper.updateZanByPrimaryKeySelective(recordingNumListWithBLOBs);
                    JSONObject retJson = new JSONObject();
                    retJson.put("zannum", recordingInfo.getZannum());
                    if (YesOrNo)
//                  赞过:
                        return new ResponseEntity(RespCode.ZANOLDFINISH, retJson);
                    else
//                  没赞:
                        return new ResponseEntity(RespCode.ZANNEWFINISH, retJson);
                } else {//myzanlist为空说明没有赞过
                    myZanWithBLOBs.setZanrecordinglist(recordingid.toString());
                    myZanMapper.updateZanByPrimaryKey(myZanWithBLOBs);

//                  2、方言zannum修改
                    RecordingInfo recordingInfo = recordingInfoMapper.selectByPrimaryKey(recordingid);
//

                    termInfo.setZannum(termInfo.getZannum() + 1);
                    recordingInfo.setZannum(recordingInfo.getZannum() + 1);
                    termInfoMapper.updateByPrimaryKeySelective(termInfo);
                    recordingInfoMapper.updateByPrimaryKeySelective(recordingInfo);

//                   3、 方言赞list用户操作
                    RecordingNumListWithBLOBs recordingNumListWithBLOBs = recordingNumListMapper.selectByRecordingId(recordingid);
                    String recordingZanUserListStr = recordingNumListWithBLOBs.getZanuseridlist();
                    if (recordingZanUserListStr != null) {
                        String[] userStrListUse = recordingZanUserListStr.split("-");
                        List<String> userlist = new ArrayList<String>();
                        Collections.addAll(userlist, userStrListUse);
                        userlist.add(userid.toString());//没赞:方言赞list增加用户；
                        String userlistNew = null;
                        for (String strj : userlist) {
                            if (userlistNew == null)
                                userlistNew = strj;
                            else userlistNew = userlistNew + "-" + strj;
                        }
                        recordingNumListWithBLOBs.setZanuseridlist(userlistNew);
                    } else
                        recordingNumListWithBLOBs.setZanuseridlist(userid.toString());
                    recordingNumListMapper.updateZanByPrimaryKeySelective(recordingNumListWithBLOBs);
                    JSONObject retJson = new JSONObject();
                    retJson.put("zannum", recordingInfo.getZannum());
                    return new ResponseEntity(RespCode.ZANNEWFINISH, retJson);
                }
            } else {
                System.out.println("myZanWithBLOBs is null");
                return new ResponseEntity(RespCode.WRONGINFO);
            }
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

    public ResponseEntity numBadControl(Map<String, Object> reqMap) {
        try {
            //        判断用户是否已经赞过该词条，
            Integer userid = Integer.parseInt(reqMap.get("userid").toString());
            Integer recordingid = Integer.parseInt(reqMap.get("recordingid").toString());
            MyZanWithBLOBs myZanWithBLOBs = myZanMapper.selectByUserId(userid);
            if (myZanWithBLOBs != null) {
                String myBadListStr = myZanWithBLOBs.getBadrecordinglist();
//                判断 我的badlist是否为空
                if (myBadListStr != null) {
                    // 1、用户mybad记录修改
                    String[] badstringlist = myBadListStr.split("-");
                    List<String> badlist = new ArrayList<String>();
                    Collections.addAll(badlist, badstringlist);
                    boolean YesOrNo = badlist.contains(recordingid.toString());
//                  赞过：用户my赞删除记录；
                    if (YesOrNo)
                        badlist.remove(recordingid.toString());
//                  没赞：用户my赞增加记录；
                    else
                        badlist.add(recordingid.toString());
                    String badlistNew = null;
                    for (String stri : badlist) {
                        if (badlistNew == null)
                            badlistNew = stri;
                        else
                            badlistNew = badlistNew + "-" + stri;
                    }
                    myZanWithBLOBs.setBadrecordinglist(badlistNew);
                    myZanMapper.updateBadByPrimaryKey(myZanWithBLOBs);
//                  2、方言badnum修改
                    RecordingInfo recordingInfo = recordingInfoMapper.selectByPrimaryKey(recordingid);
//                  赞过：方言recordingInfo赞-1；
                    if (YesOrNo)
                        recordingInfo.setBadnum(recordingInfo.getBadnum() - 1);
//                  没赞：方言recordingInfo赞+1；
                    else
                        recordingInfo.setBadnum(recordingInfo.getBadnum() + 1);
                    recordingInfoMapper.updateByPrimaryKeySelective(recordingInfo);
//                   3、 方言赞list用户操作
                    RecordingNumListWithBLOBs recordingNumListWithBLOBs = recordingNumListMapper.selectByRecordingId(recordingid);
                    String recordingBadUserListStr = recordingNumListWithBLOBs.getBaduseridlist();
                    if (recordingBadUserListStr != null) {
                        String[] userStrListUse = recordingBadUserListStr.split("-");
                        List<String> userbadlist = new ArrayList<String>();
                        Collections.addAll(userbadlist, userStrListUse);
                        if (YesOrNo)
//                  赞过:方言赞list删除用户；
                            userbadlist.remove(userid.toString());
                        else
                            userbadlist.add(userid.toString());//没赞:方言赞list增加用户；
                        String userlistNew = null;
                        for (String strj : userbadlist) {
                            if (userlistNew == null)
                                userlistNew = strj;
                            else userlistNew = userlistNew + "-" + strj;
                        }
                        recordingNumListWithBLOBs.setBaduseridlist(userlistNew);
                    } else
                        recordingNumListWithBLOBs.setBaduseridlist(userid.toString());
                    recordingNumListMapper.updateBadByPrimaryKeySelective(recordingNumListWithBLOBs);
                    JSONObject retJson = new JSONObject();
                    retJson.put("badnum", recordingInfo.getBadnum());
                    if (YesOrNo)
//                  赞过:
                        return new ResponseEntity(RespCode.BADOLDFINISH, retJson);
                    else
//                  没赞:
                        return new ResponseEntity(RespCode.BADNEWFINISH, retJson);

                } else {//mybadlist为空说明没有赞过  1、用户mybad记录修改
                    myZanWithBLOBs.setBadrecordinglist(recordingid.toString());
                    myZanMapper.updateBadByPrimaryKey(myZanWithBLOBs);
//                  2、方言badnum修改  没赞：方言recordingInfo赞+1；
                    RecordingInfo recordingInfo = recordingInfoMapper.selectByPrimaryKey(recordingid);
                    recordingInfo.setBadnum(recordingInfo.getBadnum() + 1);
                    recordingInfoMapper.updateByPrimaryKeySelective(recordingInfo);
//                   3、 方言badlist用户操作
                    RecordingNumListWithBLOBs recordingNumListWithBLOBs = recordingNumListMapper.selectByRecordingId(recordingid);
                    String recordingBadUserListStr = recordingNumListWithBLOBs.getBaduseridlist();
                    if (recordingBadUserListStr != null) {
                        String[] userStrList = recordingBadUserListStr.split("-");
                        List<String> userlist = new ArrayList<String>();
                        Collections.addAll(userlist, userStrList);
//                  没赞:方言赞list增加用户；
                        userlist.add(userid.toString());
                        String userlistNew = null;
                        for (String strj : userlist) {
                            if (userlistNew == null)
                                userlistNew = strj;
                            else userlistNew = userlistNew + "-" + strj;
                        }
                        recordingNumListWithBLOBs.setBaduseridlist(userlistNew);
                    } else
                        recordingNumListWithBLOBs.setBaduseridlist(userid.toString());
                    recordingNumListMapper.updateBadByPrimaryKeySelective(recordingNumListWithBLOBs);
                    JSONObject retJson = new JSONObject();
                    retJson.put("badnum", recordingInfo.getBadnum());
                    return new ResponseEntity(RespCode.BADNEWFINISH, retJson);
                }
            } else {
                System.out.println("myZanWithBLOBs is null");
                return new ResponseEntity(RespCode.WRONGINFO);
            }
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }


    public ResponseEntity numControlGet(Integer userid, Integer recordingid) {
        try {
            //        判断用户是否已经赞过该词条，
            MyZanWithBLOBs myZanWithBLOBs = myZanMapper.selectByUserId(userid);
            if (myZanWithBLOBs != null) {
                String myBadListStr = myZanWithBLOBs.getBadrecordinglist();
                String myZanListStr = myZanWithBLOBs.getZanrecordinglist();
                if (myBadListStr != null) {
//                  1、方言zannum修改
                    List<String> badlist = Arrays.asList(myBadListStr.split("-"));
                    boolean badYesOrNo = badlist.contains(recordingid.toString());
                    List<String> zanlist = Arrays.asList(myZanListStr.split("-"));
                    boolean zanYesOrNo = zanlist.contains(recordingid.toString());
                    JSONObject retJson = new JSONObject();
                    retJson.put("zan", zanYesOrNo);
                    retJson.put("bad", badYesOrNo);
                    return new ResponseEntity(RespCode.SUCCESS, retJson);
                }
            }
            return new ResponseEntity(RespCode.SUCCESS);
        } catch (Exception e) {
            return new ResponseEntity(RespCode.EXCEPTION, e);
        }
    }

}
