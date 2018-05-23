package com.renchaigao.fangpu.dao;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private Integer id;

    private String nickname;

    private String avatarurl;

    private String gender;

    private String city;

    private String province;

    private String country;

    private String language;

    private Integer unionid;

    private String address;

    private String vip;

    private Integer mytermsid;

    private Integer myrecordingid;

    private Integer myrankid;

    private Integer myshareid;

    public UserInfo(){

    }

    public UserInfo(Integer id,String nickname,String avatarurl,String gender,String city,String province,String country,String language,Integer unionid,String address,String vip,Integer mytermsid,Integer myrecordingid,Integer myrankid,Integer myshareid){
        this.id = id;
        this.nickname = nickname;
        this.avatarurl = avatarurl;
        this.gender = gender;
        this.city = city;
        this.province = province;
        this.country = country;
        this.language = language;
        this.unionid = unionid;
        this.address = address;
        this.vip = vip;
        this.mytermsid = mytermsid;
        this.myrecordingid = myrecordingid;
        this.myrankid = myrankid;
        this.myshareid = myshareid;
    }

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

    public Integer getUnionid() {
        return unionid;
    }

    public void setUnionid(Integer unionid) {
        this.unionid = unionid;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id+ '\'' +
                ", nickname='" + nickname+ '\'' +
                ", avatarurl='" + avatarurl+ '\'' +
                ", gender='" + gender+ '\'' +
                ", city='" + city+ '\'' +
                ", province='" + province+ '\'' +
                ", country='" + country+ '\'' +
                ", language='" + language+ '\'' +
                ", unionid='" + unionid+ '\'' +
                ", address='" + address+ '\'' +
                ", vip='" + vip+ '\'' +
                ", mytermsid='" + mytermsid+ '\'' +
                ", myrecordingid='" + myrecordingid+ '\'' +
                ", myrankid='" + myrankid+ '\'' +
                ", myshareid='" + myshareid+ '\'' +
                '}';
    }
}