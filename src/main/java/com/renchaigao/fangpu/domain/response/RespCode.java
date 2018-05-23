package com.renchaigao.fangpu.domain.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum  RespCode {

    SUCCESS(0, "请求成功"),
    OLDUSER(1,"旧用户"),
    NEWUSER(2,"新用户,已添加"),
    ADDSUCCES(3,"添加成功"),
    DELETSUCCES(4,"删除成功"),
    TERMSEND(5,"词条刷完咯"),
    WRONGINFO(-4,"错误指令"),
    EXCEPTION(-2, "抛出异常"),
    FILENONE(-3,"文件为空"),
    WARN(-1, "网络异常，请稍后重试");


    private int code;
    private String msg;

    RespCode(int code, String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
