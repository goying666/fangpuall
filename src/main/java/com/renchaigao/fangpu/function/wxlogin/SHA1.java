package com.renchaigao.fangpu.function.wxlogin;
/*
 * 微信公众平台(JAVA) SDK
 *
 * Copyright (c) 2016, Ansitech Network Technology Co.,Ltd All rights reserved.
 * http://www.ansitech.com/weixin/sdk/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.alibaba.fastjson.JSONObject;

import java.security.MessageDigest;

/**
 * <p>Title: SHA1算法</p>
 *
 * @author levi
 */
public final class SHA1 {

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    //rawData + session_key
    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) throws Exception{
//        JSONObject json = new JSONObject();
//        String str = "{\"nickName\":\"Band\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Guangzhou\",\"province\":\"Guangdong\",\"country\":\"CN\",\"avatarUrl\":\"http://wx.qlogo.cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0\"}HyVFkGl5F5OQWJZZaNzBBg==";
//        System.out.println(encode(str));
//
////        String appId = "wx5f1755206e7513a2";
////        String encryptedData = "Wtxh8mxFEAm56U8z3Ds+NybcANfRgnFVRqbpaExyz5ABSyEXlCtLHr+m1KyFRxIafytSsop6U1yIF//4bpvm3HcJzBxHdOoMhBcz8XkEwlF+FA/pSdNqeIrTnx7q6vI/NPPE7xx5H/fNlhVvf5cdJdIYLmp1syqZ4bPjqH8ayDch6eM2W/2yluqgNn/kdTeOXSEQ+AGMe4jm2u88l6jPi86hf1EuEeJlZuq2Ye3NpwNoUJ0ACEYXBHBT7fSOVhy15+5YQ5SS2VWJJ5SySZkfPbTJMvKjdO2EDY901oA3bSeqIKkR+DEAiCKjKrkyIbmrJRgorU1Cr7vRdddcIx1kq+kGLFP8qBnDd61YuNk0yYtDdcFG0AMNSy/AaX6ujGUkOfXiLrBLr3kM4uYM14TTy3KMGFO7QQ13Oe9ZJqtn9FSqrUNDoXe96nxLqJcNUxMKRxXWd3hm+KuQ/Zi32rPZLL85KiVHun7SjI7ph12Ew/S7ZQRjUOdYIwAtvv0WNyr2G14k2Rf3EAOkWb4HbXB0zdet4Nqn2vEC+BsK+pkDj2Y=";
////        String sessionKey = "t85xLkY00yNBwtAtatPfTg==";
////        String iv = "f5zyekMzCwED7H5GSsoTlQ==";
////        System.out.println(decrypt(appId, encryptedData, sessionKey, iv));
//    }
}