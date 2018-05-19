package com.renchaigao.fangpu.domain.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResponseEntity {
    private int code;
    private String msg;
    private Object data;

    public ResponseEntity(RespCode respCode){
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public  ResponseEntity(RespCode respCode,Object data){
        this(respCode);
        this.data = data;
    }
}
