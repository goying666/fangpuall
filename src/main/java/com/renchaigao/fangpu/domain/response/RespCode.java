package com.renchaigao.fangpu.domain.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum  RespCode {
    BADNEWFINISH(9,"点坏成功"),
    BADOLDFINISH(-9,"取消坏成功"),
    ZANNEWFINISH(8,"点赞成功"),
    ZANOLDFINISH(-8,"取消赞成功"),
    RANKGETNEW(7, "获取新rank成功"),
    RANKALLGET(-7, "rank已全被取完"),
    WXWRONG(-6, "认证失败"),
    WXPASS(6, "认证通过"),
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
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
