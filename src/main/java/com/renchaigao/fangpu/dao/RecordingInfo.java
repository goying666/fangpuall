package com.renchaigao.fangpu.dao;

import java.util.Date;

public class RecordingInfo {
    private Integer id;

    private Integer termid;

    private String terminfo;

    private Integer userid;

    private String path;

    private String address;

    private String filename;

    private Integer tingnum;

    private Integer zannum;

    private Integer badnum;

    private Date addtime;

    private Integer shoucangnum;

    private Integer sharenum;

    private String useravatarurl;

    private String termclass;

    private String usernickname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTermid() {
        return termid;
    }

    public void setTermid(Integer termid) {
        this.termid = termid;
    }

    public String getTerminfo() {
        return terminfo;
    }

    public void setTerminfo(String terminfo) {
        this.terminfo = terminfo == null ? null : terminfo.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Integer getTingnum() {
        return tingnum;
    }

    public void setTingnum(Integer tingnum) {
        this.tingnum = tingnum;
    }

    public Integer getZannum() {
        return zannum;
    }

    public void setZannum(Integer zannum) {
        this.zannum = zannum;
    }

    public Integer getBadnum() {
        return badnum;
    }

    public void setBadnum(Integer badnum) {
        this.badnum = badnum;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getShoucangnum() {
        return shoucangnum;
    }

    public void setShoucangnum(Integer shoucangnum) {
        this.shoucangnum = shoucangnum;
    }

    public Integer getSharenum() {
        return sharenum;
    }

    public void setSharenum(Integer sharenum) {
        this.sharenum = sharenum;
    }

    public String getUseravatarurl() {
        return useravatarurl;
    }

    public void setUseravatarurl(String useravatarurl) {
        this.useravatarurl = useravatarurl == null ? null : useravatarurl.trim();
    }

    public String getTermclass() {
        return termclass;
    }

    public void setTermclass(String termclass) {
        this.termclass = termclass == null ? null : termclass.trim();
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname == null ? null : usernickname.trim();
    }
}