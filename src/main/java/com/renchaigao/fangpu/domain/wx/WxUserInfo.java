package com.renchaigao.fangpu.domain.wx;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WxUserInfo {
    private String jscode;
    private String encryptedData;
    private String errMsg;
    private String iv;
    private String rawData;
    private String signature;
}
