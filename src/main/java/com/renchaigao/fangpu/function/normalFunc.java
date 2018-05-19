package com.renchaigao.fangpu.function;

public class normalFunc {

    public static String deleteStrArg(String strArg,String deleteStr){
         return    strArg.replaceAll(deleteStr,"").replaceAll("--","");
        }


}
