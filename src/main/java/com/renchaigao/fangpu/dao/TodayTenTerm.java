package com.renchaigao.fangpu.dao;

public class TodayTenTerm {
    private Integer id;

    private String todaydate;

    private String termidlist;

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

    public String getTermidlist() {
        return termidlist;
    }

    public void setTermidlist(String termidlist) {
        this.termidlist = termidlist == null ? null : termidlist.trim();
    }
}