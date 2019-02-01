package com.huangweihan.xweb.core.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 66666666 on 2019/6/18
 */
public class DateUtil {
    private static Log LOG = LogFactory.getLog(DateUtil.class.getName());
    public static final long MINUTES_AGO = 120000; // 系统时间向前推移时间,2分钟
    public static final int MIN_AGO = -5;

    public static String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return formatter.format(date);
    }

    // 时间格式为yyyyMMddHH，并且是当前时间的前1小时
    public static String getCurrentDateHour(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.HOUR_OF_DAY, -1);
        date = calendar.getTime();
        return formatter.format(date);
    }

    // 时间格式为yyyyMMddHH，并且是当前时间小时
    public static String getCurrentDateNowHour(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.HOUR_OF_DAY, 0);
        date = calendar.getTime();
        return formatter.format(date);
    }

    // 时间格式为yyyyMMddHHmm，并且取前十分钟时间段的整五分钟
    public static String getCurrentDateMin(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        long time = ((date.getTime()-600000)/300000)*300000;
        date = new Date(time);
        return formatter.format(date);
    }

    public static String[] getTimeListByMinute(String startTime, String endTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date date1 = null;
        Date date2 = null;
        String result[] = null;
        try {
            date1 = formatter.parse(startTime);
            date2 = formatter.parse(endTime);
            int listLength = (int) (date2.getTime() - date1.getTime()) / 60000 + 1; // 计算开始时间和结束时间相差多少个1min
            result = new String[listLength];
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        Date date = date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Calendar calendarYest = new GregorianCalendar();
        int i = 0;
        while (date.compareTo(date2) <= 0) {
            calendarYest.setTime(date);
            result[i] = formatter.format(calendarYest.getTime()); // 获取时间字符串
            calendar.add(calendar.MINUTE, 1); // 加1min
            date = calendar.getTime();
            i++;
        }
        return result;
    }

    public static String getCurrentTime(){
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
//        Date date = new Date();
//        String result = formatter.format(date.getTime()-MINUTES_AGO);
//        String second = result.substring(result.length()-2, result.length());// 截取最后两位数字，取整00或30
//        if(Integer.parseInt(second)>=30){ // 超过30秒舍为30
//            result = result.substring(0, result.length()-2) + "30";
//        }else{ // 未超过30秒舍为00秒
//            result = result.substring(0, result.length()-2) + "00";
//        }
//        return result;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        String result = "";
        Date date1 = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        calendar.add(calendar.MINUTE, MIN_AGO);//
        date1 = calendar.getTime();
        result = formatter.format(date1);
        return result;
    }

    public static String getCurrentTime(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getCurrentTimeFromHBase()  {
        String time = "";
        try {
            time = HBaseDao.getOneRowRecord("M3_config", "SparkProcessingTime", "t", "TranscodeTime");
        } catch (IOException e) {
            LOG.error("Get current time from hbase error!");
        }
        if (time == null || "".equals(time)) {
            return getCurrentTime();
        } else {
            return getCurrentm(time, -2);
        }
    }

    public static String getLastDelayTimeFromHBase(String sparkProcessingName,String webProcessingName) {
        String time = "";
        double lastWarnTimeDelay = 0;

        try {
            time = HBaseDao.getOneRowRecord("M3_config", "SparkProcessingTime", "t", sparkProcessingName);
            lastWarnTimeDelay = HBaseDao.getConfigFromHBase("M3_config", "WebProcessingTime", webProcessingName);

        } catch (IOException e) {
            LOG.error("Get current time from hbase error!");
        }
        if (time == null || "".equals(time)) {
            return getCurrentTime();
        } else {
            return getCurrentm(time, (int) (-lastWarnTimeDelay));
        }
    }

    public static String getDateFormatted(String date){
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd");
        String result = "";
        try {
            Date date1 = formatter1.parse(date);
            result = formatter2.format(date1);
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        return result;
    }

    // 从前端传到后台的date格式化 yyyy-MM-dd
    public static String getDateFormattedFromFront(String date){
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
        String result = "";
        try {
            Date date1 = formatter1.parse(date);
            result = formatter2.format(date1);
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        return result;
    }

    // 从前端传到后台的date格式化 yyyy-MM-dd HH
    public static String getDateFormattedFromFrontHour(String date){
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHH");
        String result = "";
        try {
            Date date1 = formatter1.parse(date);
            result = formatter2.format(date1);
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        return result;
    }


    // 从前端传到后台的time格式化,前端time格式为 yyyy-MM-dd HH:mm
    public static String getTimeFormattedFromFront(String date){
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmm");
        String result = "";
        try {
            Date date1 = formatter1.parse(date);
            result = formatter2.format(date1);
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        return result;
    }

    // 从前端传到后台的time格式化,前端time格式为 yyyy-MM-dd HH:mm
    public static String getTimeFormattedFromFrontSec(String date){
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmss");
        String result = "";
        try {
            Date date1 = formatter1.parse(date);
            result = formatter2.format(date1);
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        return result;
    }

    // 从前端传到后台的time格式化,前端time格式为"yyyy-MM-dd HH:mm",并取整前面的一个五分钟
    public static String getMinuteFormattedFromFront(String date){
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmm");
        String result = "";
        try {
            Date date1 = formatter1.parse(date);
            long time = (date1.getTime()/300000)*300000;
            date1 = new Date(time);
            result = formatter2.format(date1);
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        return result;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 后端传前端格式化 "yyyy-MM-dd | yyyy-MM-dd HH:mm | yyyy-MM-dd HH"
    public static String getTimeFormattedForFront(String dateStr){
        SimpleDateFormat formatter1;
        SimpleDateFormat formatter2;
        if(dateStr.length() == 8){
            formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            formatter2 = new SimpleDateFormat("yyyyMMdd");
        }
        else if(dateStr.length() == 10){
            formatter1 = new SimpleDateFormat("yyyy-MM-dd HH");
            formatter2 = new SimpleDateFormat("yyyyMMddHH");
        }
        else if(dateStr.length() == 12) {
            formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            formatter2 = new SimpleDateFormat("yyyyMMddHHmm");
        }
        else {
            formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter2 = new SimpleDateFormat("yyyyMMddHHmmss");
        }

        String result = "";
        try {
            Date date = formatter2.parse(dateStr);
            result = formatter1.format(date);
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        return result;
    }

    // 后端传前端格式化 "yyyy-MM-dd | yyyy-MM-dd HH:mm | yyyy-MM-dd HH"
    public static String getTimeFormattedFromFrontNew(String dateStr){
        SimpleDateFormat formatter1;
        SimpleDateFormat formatter2;
        if(dateStr.length() == 10){
            formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            formatter2 = new SimpleDateFormat("yyyyMMdd");
        }
        else if(dateStr.length() == 13){
            formatter1 = new SimpleDateFormat("yyyy-MM-dd HH");
            formatter2 = new SimpleDateFormat("yyyyMMddHH");
        }
        else if(dateStr.length() == 16) {
            formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            formatter2 = new SimpleDateFormat("yyyyMMddHHmm");
        }
        else {
            formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter2 = new SimpleDateFormat("yyyyMMddHHmmss");
        }

        String result = "";
        try {
            Date date = formatter1.parse(dateStr);
            result = formatter2.format(date);
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        return result;
    }

    // 获取前一天日期
    public static String getDateBefore(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String result = "";
        Date date1 = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        calendar.add(Calendar.DATE, -1);// 前一天
        date1 = calendar.getTime();
        result = formatter.format(date1);
        return result;
    }

    // 获取前n天日期
    public static String getDateBefore(int n){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String result = "";
        Date date1 = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        calendar.add(Calendar.DATE, -n);
        date1 = calendar.getTime();
        result = formatter.format(date1);
        return result;
    }

    //dateTime开始获取前i天日期
    public static String getDateBefore(String dateTime, int i){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String result = "";
        try {
            Date date = formatter.parse(dateTime);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -i);// 前一天
            date = calendar.getTime();
            result = formatter.format(date);

        }catch (ParseException e) {
            LOG.error("getDateBefore ParseException ", e);
        }
        return result;
    }

    //将开始时间到结束时间1min间隔的数组,并获取时间数组对应的前7天的时间值，横坐标0为当天数组，7为第6天数组
    public static String[][] getTimeList(String startTime, String endTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date date1 = null;
        Date date2 = null;
        String result[][] = null;
        try {
            date1 = formatter.parse(startTime);
            date2 = formatter.parse(endTime);
            int listLength = (int)(date2.getTime()-date1.getTime())/60000 + 1; // 计算开始时间和结束时间相差多少个1min
            result = new String[8][listLength];
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        Date date = date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Calendar calendarYest = new GregorianCalendar();
        int i = 0;
        while(date.compareTo(date2)<=0){
            calendarYest.setTime(date);
            for(int j=0;j<=7;j++){
                result[j][i] = formatter.format(calendarYest.getTime()); // 获取时间字符串
                calendarYest.add(Calendar.DAY_OF_MONTH, -1);
            }
            calendar.add(Calendar.MINUTE, 1); // 加1min
            date = calendar.getTime();
            i++;
        }
        return result;
    }

    //将开始时间到结束时间5min间隔的数组,并获取时间数组对应的前7天的时间值，横坐标0为当天数组，7为第6天数组
    public static String[][] getTimeListByFiveMinutes(String startTime, String endTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date date1 = null;
        Date date2 = null;
        String result[][] = null;
        try {
            date1 = formatter.parse(startTime);
            long time = (date1.getTime()/300000)*300000;
            date1 = new Date(time);
            date2 = formatter.parse(endTime);
            time = (date2.getTime()/300000)*300000;
            date2 = new Date(time);
            int listLength = (int)(date2.getTime()-date1.getTime())/300000 + 1; // 计算开始时间和结束时间相差多少个5min
            result = new String[8][listLength];
        } catch (ParseException e) {
            LOG.error("Get   ", e);
        }
        Date date = date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Calendar calendarYest = new GregorianCalendar();
        int i = 0;
        while(date.compareTo(date2)<=0){
            calendarYest.setTime(date);
            for(int j=0;j<=7;j++){
                result[j][i] = formatter.format(calendarYest.getTime()); // 获取时间字符串
                calendarYest.add(Calendar.DAY_OF_MONTH, -1);
            }
            calendar.add(calendar.MINUTE, 5); // 加1min
            date = calendar.getTime();
            i++;
        }
        return result;
    }

    //将开始时间到结束时间1h间隔的数组,并获取时间数组对应的前7天的时间值，横坐标0为当天数组，7为第6天数组
    public static String[][] getTimeListByHours(String startTime, String endTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
        Date date1 = null;
        Date date2 = null;
        String result[][] = null;
        try {
            date1 = formatter.parse(startTime);
            date2 = formatter.parse(endTime);
            int listLength = (int)((date2.getTime()-date1.getTime())/(1000*3600)) + 1; // 计算开始时间和结束时间相差多少小时
            result = new String[8][listLength];
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        Date date = date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Calendar calendarYest = new GregorianCalendar();
        int i = 0;
        while(date.compareTo(date2)<=0){
            calendarYest.setTime(date);
            for(int j=0;j<=7;j++){
                result[j][i] = formatter.format(calendarYest.getTime()); // 获取时间字符串
                calendarYest.add(Calendar.DAY_OF_MONTH, -1);
            }
            calendar.add(calendar.HOUR_OF_DAY, 1); // 加1小时
            date = calendar.getTime();
            i++;
        }
        return result;
    }

    public static String[] getTimeListSingleArr(String startTime, String endTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date date1 = null;
        Date date2 = null;
        String result[] = null;
        try {
            date1 = formatter.parse(startTime);
            date2 = formatter.parse(endTime);
            int listLength = (int)(date2.getTime()-date1.getTime())/60000 + 1; // 计算开始时间和结束时间相差多少个1min
            result = new String[listLength];
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        Date date = date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Calendar calendarYest = new GregorianCalendar();
        int i = 0;
        while(date.compareTo(date2)<=0){
            calendarYest.setTime(date);
            result[i] = formatter.format(calendarYest.getTime()); // 获取时间字符串


            calendar.add(calendar.MINUTE, 1); // 加1min
            date = calendar.getTime();
            i++;
        }
        return result;
    }

    public static String[] getTimeListForFront(String startTime, String endTime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = null;
        Date date2 = null;
        String result[] = null;
        try {
            date1 = formatter.parse(startTime);
            date2 = formatter.parse(endTime);
            int listLength = (int)(date2.getTime()-date1.getTime())/60000 + 1; // 计算开始时间和结束时间相差多少个1min
            result = new String[listLength];
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        Date date = date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Calendar calendarYest = new GregorianCalendar();
        int i = 0;
        while(date.compareTo(date2)<=0){
            calendarYest.setTime(date);
            result[i] = formatter1.format(calendarYest.getTime()); // 获取时间字符串


            calendar.add(Calendar.MINUTE, 1); // 加1min
            date = calendar.getTime();
            i++;
        }
        return result;
    }

    // 根据开始日期和结束日期计算出区间的日期数组
    public static String[] getDateList(String startDate, String endDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date1 = null;
        Date date2 = null;
        String result[] = null;
        try {
            date1 = formatter.parse(startDate);
            date2 = formatter.parse(endDate);
            long listLength = (int)((date2.getTime()-date1.getTime())/(1000*3600*24)) + 1; // 计算开始时间和结束时间相差多少天
            result = new String[(int)listLength];
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        Date date = date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int i = 0;
        while(date.compareTo(date2)<=0){
            result[i] = formatter.format(calendar.getTime()); // 获取时间字符串
            calendar.add(calendar.DAY_OF_MONTH, 1); // 加1天
            date = calendar.getTime();
            i++;
        }
        return result;
    }

    // 根据开始日期和结束日期计算出区间的月期数组
    public static String[] getMonthList(String startDate, String endDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        Date startdate = null;
        Date enddate = null;

        List<String> resList = new ArrayList<>();

        try {
            startdate = formatter.parse(startDate);
            enddate = formatter.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);
        while (calendar.getTime().compareTo(enddate)<=0){
            resList.add(formatter.format(calendar.getTime()));
            calendar.add(Calendar.MONTH,1);
        }
        String result[] = new String[resList.size()];
        resList.toArray(result);

        return result;
    }

    // 根据开始日期和结束日期计算出区间的日期数组，每小时一个区间
    public static String[] getTimeListForUrlCost(String startDate, String endDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
        Date date1 = null;
        Date date2 = null;
        String result[] = null;
        try {
            date1 = formatter.parse(startDate);
            date2 = formatter.parse(endDate);
            long listLength = (int)((date2.getTime()-date1.getTime())/(1000*3600)) + 1; // 计算开始时间和结束时间相差多少小时
            result = new String[(int)listLength];
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        Date date = date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int i = 0;
        while(date.compareTo(date2)<=0){
            result[i] = formatter.format(calendar.getTime()); // 获取时间字符串
            calendar.add(calendar.HOUR_OF_DAY, 1); // 加1小时
            date = calendar.getTime();
            i++;
        }
        return result;
    }

    // 根据输入的yyyyMMdd获取前端展示的日志格式
    public static String getDateTimeForFront(String date){
        if(date.length()==8){ // yyyyMMdd 返回MM-dd
            return date.substring(4, 6) + "-" + date.substring(6, 8);
        }else if(date.length()==10){ // yyyyMMddHH 返回MM-dd HH时
            return date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + "时";
        }else if(date.length()==12){ // yyyyMMddHHmm
            return date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + ":" + date.substring(10, 12);
        }else if(date.length()==14){ // yyyyMMddHHmmss
            return date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
        }else{
            return date;
        }
    }

    //返回前端的时间列表
    public static String[] getDateForFrontList(String startDate, String endDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        String result[] = null;
        try {
            date1 = formatter.parse(startDate);
            date2 = formatter.parse(endDate);
            long listLength = (int)((date2.getTime()-date1.getTime())/(1000*3600*24)) + 1; // 计算开始时间和结束时间相差多少天
            result = new String[(int)listLength];
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        Date date = date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int i = 0;
        while(date.compareTo(date2)<=0){
            result[i] = formatter1.format(calendar.getTime()); // 获取时间字符串
            calendar.add(calendar.DAY_OF_MONTH, 1); // 加1天
            date = calendar.getTime();
            i++;
        }
        return result;
    }
 //当前时间前后几分钟
    public static String getCurrentm(int m){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MINUTE, m+MIN_AGO);
        return formatter.format(calendar.getTime());
    }

    //当前日期前后几天
    public static String getCurrentd(int d){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String result = "";
        Date date1 = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        calendar.add(calendar.DATE, d);//
        date1 = calendar.getTime();
        result = formatter.format(date1);
        return result;
    }
    //指定日期前后几几天
    public static String getDayBeforeOrLater(String dateTime,int m){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHH");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmm");
        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            if(dateTime.length()==8){
                date = formatter.parse(dateTime);
            }else if(dateTime.length()==10){
                date = formatter1.parse(dateTime);
            }else if(dateTime.length()==12){
                date = formatter2.parse(dateTime);
            }else if(dateTime.length()==14) {
                date = formatter3.parse(dateTime);
            }
        }catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, m);//
        if(dateTime.length()==8){
            return formatter.format(calendar.getTime());
        }else if(dateTime.length()==10){
            return formatter1.format(calendar.getTime());
        }else if(dateTime.length()==12){
            return formatter2.format(calendar.getTime());
        }else if(dateTime.length()==14){
            return formatter3.format(calendar.getTime());
        }
        return formatter.format(calendar.getTime());
    }

    //指定时间前后几分钟
    public static String getCurrentm(String dateTime ,int m){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            if (dateTime.length() == 12) {
                date = formatter.parse(dateTime);
            } else if (dateTime.length() == 14) {
                date = formatter1.parse(dateTime);
            }
        }catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MINUTE, m);
        if (dateTime.length() == 12) {
            return formatter.format(calendar.getTime());
        } else if (dateTime.length() == 14) {
            return formatter1.format(calendar.getTime());
        }
        return formatter.format(calendar.getTime());
    }

    //dmz区 与当前时间差分钟
    public static int gettoCurrenMins(String beforeTime){
        int mins = 60;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long from = formatter.parse(beforeTime).getTime();
            Date now = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(now);
            calendar.add(calendar.HOUR_OF_DAY, -8);//DMZ时区问题
            now = calendar.getTime();
            long to = now.getTime();
            mins = (int) ((to - from)/(1000 * 60));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mins;
    }

    public static Date str2Date(String dateStr, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public static String getAppTimeByAppName(String appName) {
        String appTime = null;

        try (Table table = HBaseDao.getTable("M3_config")) {
            Get get = new Get(Bytes.toBytes("SparkProcessingTime"));
            get.addColumn(Bytes.toBytes("t"), Bytes.toBytes(appName));
            Result result = table.get(get);
            appTime = Bytes.toString(result.getValue(Bytes.toBytes("t"), Bytes.toBytes(appName)));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

        return appTime;
    }

    /**
     * 判断一个时间是否包含在指定的区间内
     * @param time 所判断时间
     * @param start 起始时间
     * @param end 结束时间
     * @return
     */
    public static boolean isInTheTimeRange(String time,String start,String end){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        boolean res = false;
        try {
            Date timeDate = formatter.parse(time);
            Date startDate = formatter.parse(start);
            Date endDate = formatter.parse(end);
            if (startDate.before(timeDate)&&timeDate.before(endDate)) //start<time<end
                res = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 时间比较 true:start<end
     * @param start 起始时间
     * @param end 结束时间
     * @return
     */
    public static boolean timeCompare(String start,String end){
        SimpleDateFormat formatter = null;
        if (start.length() == 8)
            formatter = new SimpleDateFormat("yyyyMMdd");
        else if (start.length() == 12)
            formatter = new SimpleDateFormat("yyyyMMddHHmm");
        else
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        boolean res = false;
        try {
            Date startDate = formatter.parse(start);
            Date endDate = formatter.parse(end);
            if (startDate.before(endDate)) //start<end
                res = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 时间比较 true:start<=end
     * @param start 起始时间
     * @param end 结束时间
     * @return
     */
    public static boolean isBefore(String start,String end) {
        SimpleDateFormat formatter;
        if (start.length() == 12) {
            formatter = new SimpleDateFormat("yyyyMMddHHmm");
        } else {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = formatter.parse(start);
            endDate = formatter.parse(end);
        } catch (ParseException e) {
            LOG.error(e.getMessage());
        }
        //start<=end
        if (startDate.before(endDate) || start.equals(end)) {
            return true;
        }
        return false;
    }

    /**
     * 获得两个分钟数据的时差
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getTimeRangeByMinute(String startTime, String endTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        int distance = 0;
        try {
            Date fromDate = formatter.parse(startTime);
            Date endDate = formatter.parse(endTime);
            long millisecond = endDate.getTime() - fromDate.getTime();
            long minutes = millisecond / (60 * 1000);
            distance = (int) minutes;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return distance + 1;
    }

    /**
     *  取昨天零点的时间字符串（yyyyMMddHHmm）
     *
     * @return
     */
    public static String getYesterdayZeroTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return formatter.format(calendar.getTime());
    }

    public static int getDateBetween(String start, String end){

        int result = 0;
        if (start.length() > 8) start = start.substring(0, 8);
        if (end.length() > 8) end = end.substring(0, 8);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try {
            long startTime = formatter.parse(start).getTime();
            long endTime = formatter.parse(end).getTime();
            result = (int)(Math.abs(endTime - startTime)/(24*60*60*1000));

        }catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String[] getTimeListByMinuteForFront(String startTime, String endTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
        Date date1 = null;
        Date date2 = null;
        String result[] = null;
        try {
            date1 = formatter.parse(startTime);
            date2 = formatter.parse(endTime);
            int listLength = (int) (date2.getTime() - date1.getTime()) / 60000 + 1; // 计算开始时间和结束时间相差多少个1min
            result = new String[listLength];
        } catch (ParseException e) {
            LOG.error("Get ParseException ", e);
        }
        Date date = date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Calendar calendarYest = new GregorianCalendar();
        int i = 0;
        while (date.compareTo(date2) <= 0) {
            calendarYest.setTime(date);
            result[i] = formatter1.format(calendarYest.getTime()); // 获取时间字符串
            calendar.add(calendar.MINUTE, 1); // 加1min
            date = calendar.getTime();
            i++;
        }
        return result;
    }

}
