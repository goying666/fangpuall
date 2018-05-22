package com.renchaigao.fangpu.dao;

public class TodayRecordingRank {
    private Integer id;

    private String todaydate;

    private String recordingranks;

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

    public String getRecordingranks() {
        return recordingranks;
    }

    public void setRecordingranks(String recordingranks) {
        this.recordingranks = recordingranks == null ? null : recordingranks.trim();
    }
}