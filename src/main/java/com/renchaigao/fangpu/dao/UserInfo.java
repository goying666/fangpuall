package com.renchaigao.fangpu.dao;

public class UserInfo {
    private Integer id;

    private String nickname;

    private String avatarurl;

    private String gender;

    private String city;

    private String province;

    private String country;

    private String language;

    private String unionid;

    private String address;

    private String vip;

    private Integer mytermsid;

    private Integer myrecordingid;

    private Integer myrankid;

    private Integer myshareid;

    private String openid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip == null ? null : vip.trim();
    }

    public Integer getMytermsid() {
        return mytermsid;
    }

    public void setMytermsid(Integer mytermsid) {
        this.mytermsid = mytermsid;
    }

    public Integer getMyrecordingid() {
        return myrecordingid;
    }

    public void setMyrecordingid(Integer myrecordingid) {
        this.myrecordingid = myrecordingid;
    }

    public Integer getMyrankid() {
        return myrankid;
    }

    public void setMyrankid(Integer myrankid) {
        this.myrankid = myrankid;
    }

    public Integer getMyshareid() {
        return myshareid;
    }

    public void setMyshareid(Integer myshareid) {
        this.myshareid = myshareid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }
}