package com.renchaigao.fangpu.function;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateUse {

    public static String DateToString(Date dateValue){
        if (dateValue != null){
            DateFormat mediumDateFormat = DateFormat.getDateTimeInstance
                    (DateFormat.MEDIUM,DateFormat.MEDIUM);
            return mediumDateFormat.format(dateValue);
        }else {
            return null;
        }
    }
    public static Date StringToDate(String dateStr){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateret = formatter.parse(dateStr);
            return dateret;
        }catch (ParseException e){
            System.out.println(e);
        }
        return null;
    }
    public static String GetStringDateNow(){
        return DateToString(new Date());
    }
//    public static Date GetFormatDateNow(){
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    }
}
