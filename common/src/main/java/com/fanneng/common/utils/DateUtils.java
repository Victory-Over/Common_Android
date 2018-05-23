package com.fanneng.common.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * 得到几天前的时间
     * @param d
     * @param day  正为之后的日期  负为之前的日期
     * @return
     */
    public static Date getDateOffset(Date d, int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return now.getTime();
    }

    /**
     * 日期转字符串
     *
     * @param dateDate Date
     * @param format   格式化
     * */
    public static String dateToStr(Date dateDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(dateDate);
    }

    /**
     * 字符串转日期
     *
     * @param strDate Date
     * @param format   格式化
     * */
    public static Date strToDate(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }
}
