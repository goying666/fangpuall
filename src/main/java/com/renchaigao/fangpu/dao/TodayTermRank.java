package com.renchaigao.fangpu.dao;

public class TodayTermRank {
    private Integer id;

    private String todaydate;

    private String termranks;

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

    public String getTermranks() {
        return termranks;
    }

    public void setTermranks(String termranks) {
        this.termranks = termranks == null ? null : termranks.trim();
    }
}