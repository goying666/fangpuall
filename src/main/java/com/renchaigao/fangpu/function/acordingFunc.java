package com.renchaigao.fangpu.function;

import java.io.File;

public class acordingFunc {


    /**********************************************
     * 功能：生成系统下 各用户对应的recording 目录
     * 入参：用户id  userId
     **********************************************/
    public String creatRecodingPathOnservice(Integer userId){
        File file = new File("/fpfolder/recording/users/"+userId.toString() );
        if(!file.exists())
            file.mkdirs();
        return "/fpfolder/recording/users/"+userId.toString() + "/";
    }
}
