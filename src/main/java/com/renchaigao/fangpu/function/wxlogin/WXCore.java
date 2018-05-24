package com.renchaigao.fangpu.function.wxlogin;


import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * 封装对外访问方法
 * @author liuyazhuang
 *
 */
public class WXCore {

    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";
    /**
     * 解密数据
     * @return
     * @throws Exception
     */
    public static String decrypt(String appId, String encryptedData, String sessionKey, String iv){
        String result = "";
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSONObject.parseObject(result);
                String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);
                if(!appId.equals(decryptAppid)){
                    result = "";
                }
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) throws Exception{
//        String appId = "wx5f1755206e7513a2";
//        String encryptedData = "Wtxh8mxFEAm56U8z3Ds+NybcANfRgnFVRqbpaExyz5ABSyEXlCtLHr+m1KyFRxIafytSsop6U1yIF//4bpvm3HcJzBxHdOoMhBcz8XkEwlF+FA/pSdNqeIrTnx7q6vI/NPPE7xx5H/fNlhVvf5cdJdIYLmp1syqZ4bPjqH8ayDch6eM2W/2yluqgNn/kdTeOXSEQ+AGMe4jm2u88l6jPi86hf1EuEeJlZuq2Ye3NpwNoUJ0ACEYXBHBT7fSOVhy15+5YQ5SS2VWJJ5SySZkfPbTJMvKjdO2EDY901oA3bSeqIKkR+DEAiCKjKrkyIbmrJRgorU1Cr7vRdddcIx1kq+kGLFP8qBnDd61YuNk0yYtDdcFG0AMNSy/AaX6ujGUkOfXiLrBLr3kM4uYM14TTy3KMGFO7QQ13Oe9ZJqtn9FSqrUNDoXe96nxLqJcNUxMKRxXWd3hm+KuQ/Zi32rPZLL85KiVHun7SjI7ph12Ew/S7ZQRjUOdYIwAtvv0WNyr2G14k2Rf3EAOkWb4HbXB0zdet4Nqn2vEC+BsK+pkDj2Y=";
//        String sessionKey = "t85xLkY00yNBwtAtatPfTg==";
//        String iv = "f5zyekMzCwED7H5GSsoTlQ==";
//        System.out.println(decrypt(appId, encryptedData, sessionKey, iv));
//    }
}