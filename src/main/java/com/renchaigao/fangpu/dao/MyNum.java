package com.renchaigao.fangpu.dao;

public class MyNum {
    private Integer id;

    private String todaydate;

    private Integer zannum;

    private Integer badnum;

    private Integer termnum;

    private Integer recordingnum;

    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTodaydate() {
        return todaydate;
    }

    public void setTodaydate(String todaydate) {
        this.todaydate = todaydate == null ? null : todaydate.trim();
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

    public Integer getTermnum() {
        return termnum;
    }

    public void setTermnum(Integer termnum) {
        this.termnum = termnum;
    }

    public Integer getRecordingnum() {
        return recordingnum;
    }

    public void setRecordingnum(Integer recordingnum) {
        this.recordingnum = recordingnum;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}