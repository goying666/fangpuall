package com.renchaigao.fangpu.dao;

public class MyShare {
    private Integer id;

    private Integer userid;

    private String termsharelist;

    private String recordingsharelist;

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

    public String getTermsharelist() {
        return termsharelist;
    }

    public void setTermsharelist(String termsharelist) {
        this.termsharelist = termsharelist == null ? null : termsharelist.trim();
    }

    public String getRecordingsharelist() {
        return recordingsharelist;
    }

    public void setRecordingsharelist(String recordingsharelist) {
        this.recordingsharelist = recordingsharelist == null ? null : recordingsharelist.trim();
    }
}