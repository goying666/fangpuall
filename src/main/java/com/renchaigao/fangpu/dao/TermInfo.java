package com.renchaigao.fangpu.dao;

import java.util.Date;

public class TermInfo {
    private Integer id;

    private Integer ownerid;

    private String content;

    private String address;

    private String introduce;

    private Integer zannum;

    private Integer tingnum;

    private Integer recordingnum;

    private Date addtime;

    private String recordingids;

    private String usernickname;

    private String termclass;

    private String useravatarurl;

    private Integer sharenum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getZannum() {
        return zannum;
    }

    public void setZannum(Integer zannum) {
        this.zannum = zannum;
    }

    public Integer getTingnum() {
        return tingnum;
    }

    public void setTingnum(Integer tingnum) {
        this.tingnum = tingnum;
    }

    public Integer getRecordingnum() {
        return recordingnum;
    }

    public void setRecordingnum(Integer recordingnum) {
        this.recordingnum = recordingnum;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getRecordingids() {
        return recordingids;
    }

    public void setRecordingids(String recordingids) {
        this.recordingids = recordingids == null ? null : recordingids.trim();
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname == null ? null : usernickname.trim();
    }

    public String getTermclass() {
        return termclass;
    }

    public void setTermclass(String termclass) {
        this.termclass = termclass == null ? null : termclass.trim();
    }

    public String getUseravatarurl() {
        return useravatarurl;
    }

    public void setUseravatarurl(String useravatarurl) {
        this.useravatarurl = useravatarurl == null ? null : useravatarurl.trim();
    }

    public Integer getSharenum() {
        return sharenum;
    }

    public void setSharenum(Integer sharenum) {
        this.sharenum = sharenum;
    }
}