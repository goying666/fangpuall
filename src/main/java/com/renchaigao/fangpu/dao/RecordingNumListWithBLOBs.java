package com.renchaigao.fangpu.dao;

public class RecordingNumListWithBLOBs extends RecordingNumList {
    private String zanuseridlist;

    private String baduseridlist;

    public String getZanuseridlist() {
        return zanuseridlist;
    }

    public void setZanuseridlist(String zanuseridlist) {
        this.zanuseridlist = zanuseridlist == null ? null : zanuseridlist.trim();
    }

    public String getBaduseridlist() {
        return baduseridlist;
    }

    public void setBaduseridlist(String baduseridlist) {
        this.baduseridlist = baduseridlist == null ? null : baduseridlist.trim();
    }
}