package com.renchaigao.fangpu.dao;

public class RecordingList {
    private Integer id;

    private Integer termid;

    private String recordingliststr;

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

    public String getRecordingliststr() {
        return recordingliststr;
    }

    public void setRecordingliststr(String recordingliststr) {
        this.recordingliststr = recordingliststr == null ? null : recordingliststr.trim();
    }
}