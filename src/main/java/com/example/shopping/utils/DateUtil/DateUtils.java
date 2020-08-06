package com.example.shopping.utils.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //将日期转换为字符串
    public static String dateToString(Date date,String patt){

        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }

    //将字符串转为日期
    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(patt);
        Date date = sdf.parse(str);
        return date;
    }
}
