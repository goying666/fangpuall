package com.renchaigao.fangpu.dao;

public class MyZanWithBLOBs extends MyZan {
    private String zanrecordinglist;

    private String badrecordinglist;

    public String getZanrecordinglist() {
        return zanrecordinglist;
    }

    public void setZanrecordinglist(String zanrecordinglist) {
        this.zanrecordinglist = zanrecordinglist == null ? null : zanrecordinglist.trim();
    }

    public String getBadrecordinglist() {
        return badrecordinglist;
    }

    public void setBadrecordinglist(String badrecordinglist) {
        this.badrecordinglist = badrecordinglist == null ? null : badrecordinglist.trim();
    }
}