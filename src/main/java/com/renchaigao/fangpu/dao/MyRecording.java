package com.renchaigao.fangpu.dao;

public class MyRecording {
    private Integer id;

    private Integer userid;

    private String allrecordinglist;

    private Integer recordingnum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAllrecordinglist() {
        return allrecordinglist;
    }

    public void setAllrecordinglist(String allrecordinglist) {
        this.allrecordinglist = allrecordinglist == null ? null : allrecordinglist.trim();
    }

    public Integer getRecordingnum() {
        return recordingnum;
    }

    public void setRecordingnum(Integer recordingnum) {
        this.recordingnum = recordingnum;
    }
}