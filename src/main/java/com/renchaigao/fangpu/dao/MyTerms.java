package com.renchaigao.fangpu.dao;

public class MyTerms {
    private Integer id;

    private Integer userid;

    private String alltermlist;

    private Integer termnum;

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

    public String getAlltermlist() {
        return alltermlist;
    }

    public void setAlltermlist(String alltermlist) {
        this.alltermlist = alltermlist == null ? null : alltermlist.trim();
    }

    public Integer getTermnum() {
        return termnum;
    }

    public void setTermnum(Integer termnum) {
        this.termnum = termnum;
    }
}