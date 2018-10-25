package com.huangweihan.xweb.core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 时间工具类
 *
 * @author: Administrator
 * @date: 2018/10/22 0022
 */
public class DateTimeUtil {

    public static final DateTimeFormatter _yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter _yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter _yyyyMMddHH = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    public static final DateTimeFormatter _yyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    public static final DateTimeFormatter yyyyMMddHH = DateTimeFormatter.ofPattern("yyyyMMddHH");
    public static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 默认获取5分钟前的数据，因为HBase更新没那么快
     */
    private static final int MIN_AGO = -5;

    private static LocalDateTime parseDateTimeSource(String source) {
        //不支持8
        if (source.length() == 8) {
            return LocalDateTime.parse(source, yyyyMMdd);
        } else if (source.length() == 10) {
            return LocalDateTime.parse(source, yyyyMMddHH);
        } else if (source.length() == 12) {
            return LocalDateTime.parse(source, yyyyMMddHHmm);
        } else if (source.length() == 14) {
            return LocalDateTime.parse(source, yyyyMMddHHmmss);
        }
        return null;
    }

    private static LocalDateTime parseDateTimeSourceFromFront(String frontSource) {
        //不支持13
        if (frontSource.length() == 10) {
            return LocalDateTime.parse(frontSource, _yyyyMMdd);
        } else if (frontSource.length() == 13) {
            return LocalDateTime.parse(frontSource, _yyyyMMddHH);
        } else if (frontSource.length() == 16) {
            return LocalDateTime.parse(frontSource, _yyyyMMddHHmm);
        } else if (frontSource.length() == 19) {
            return LocalDateTime.parse(frontSource, _yyyyMMddHHmmss);
        }
        return null;
    }

    /**
     * 获得当前日期
     * @return
     */
    public static String getCurrentDay() {
        LocalDateTime dateTime = LocalDateTime.now();
        String date = dateTime.format(yyyyMMdd);
        return date;
    }

    /**
     * 获得当前小时
     * @return
     */
    public static String getCurrentHour() {
        LocalDateTime dateTime = LocalDateTime.now();
        String date = dateTime.format(yyyyMMddHH);
        return date;
    }

    /**
     * 获得当前分钟
     * @return
     */
    public static String getCurrentMinute() {
        LocalDateTime dateTime = LocalDateTime.now();
        String date = dateTime.format(yyyyMMddHHmm);
        return date;
    }

    /**
     * 获得当前时间（通用模板）
     * @param targetFormatter 自定义返回时间格式
     * @return
     */
    public static String getCurrentTime(DateTimeFormatter targetFormatter) {
        LocalDateTime dateTime = LocalDateTime.now();
        String date = dateTime.format(targetFormatter);
        return date;
    }

    /**
     * 当前时间增加 interval 分钟
     *
     * @param interval 时间间隔
     * @return
     */
    public static String addMinute(int interval) {
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.plusMinutes(interval).format(yyyyMMddHHmm);
    }

    /**
     * 当前时间增加 interval 分钟，并指定期望返回格式
     *
     * @param interval        时间数
     * @param targetFormatter 期望返回格式
     * @return
     */
    public static String addMinute(int interval, DateTimeFormatter targetFormatter) {
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.plusMinutes(interval).format(targetFormatter);
    }

    /**
     * 指定时间增加 interval 分钟
     *
     * @param interval +/- interval
     * @param source   源时间字符串
     * @return
     */
    public static String addMinute(int interval, String source) {
        LocalDateTime dateTime = parseDateTimeSource(source);
        return dateTime.plusMinutes(interval).format(yyyyMMddHHmm);
    }

    /**
     * 指定时间增加 interval 分钟，并指定返回格式
     *
     * @param interval        时间数
     * @param source          源时间字符串
     * @param targetFormatter 期望返回格式
     * @return
     */
    public static String addMinute(int interval, String source, DateTimeFormatter targetFormatter) {
        LocalDateTime dateTime = parseDateTimeSource(source);
        return dateTime.plusMinutes(interval).format(targetFormatter);
    }

    /**
     * 比较源时间是否晚于目标时间
     *
     * @param source 源时间
     * @param target 目标时间
     * @return
     */
    public static boolean isAfter(String source, String target) {
        LocalDateTime sourceDateTime = parseDateTimeSource(source);
        LocalDateTime targetDateTime = parseDateTimeSource(target);
        return sourceDateTime.isAfter(targetDateTime);
    }

    /**
     * 比较源时间是否早于目标时间
     *
     * @param source 源时间
     * @param target 目标时间
     * @return
     */
    public static boolean isBefore(String source, String target) {
        LocalDateTime sourceDateTime = parseDateTimeSource(source);
        LocalDateTime targetDateTime = parseDateTimeSource(target);
        return sourceDateTime.isBefore(targetDateTime);
    }

    /**
     * 解析前端时间，并指定返回格式
     * @param frontSource 源时间
     * @param targetFormatter 期望返回格式
     * @return
     */
    public static String formattedFromFront(String frontSource, DateTimeFormatter targetFormatter) {
        LocalDateTime sourceDateTime = parseDateTimeSourceFromFront(frontSource);
        return sourceDateTime.format(targetFormatter);
    }

    /**
     * 解析后端时间，并指定返回格式
     * @param Source 源时间
     * @param targetFormatter 期望返回格式
     * @return
     */
    public static String formattedForFront(String Source, DateTimeFormatter targetFormatter) {
        LocalDateTime sourceDateTime = parseDateTimeSource(Source);
        return sourceDateTime.format(targetFormatter);
    }

    /**
     * 获得开始时间到结束时间内的n分钟间隔的时间列表
     *
     * @param startTime 起始时间
     * @param endTime 结束时间
     */
    public static List<String> getTimeListByMinute(String startTime, String endTime, int interval) {
        LocalDateTime startDateTime = parseDateTimeSource(startTime);
        LocalDateTime endDateTime = parseDateTimeSource(endTime);
        List<String> list = new ArrayList<>();
        while (startDateTime.isBefore(endDateTime)) {
            list.add(startDateTime.format(yyyyMMddHHmm));
            startDateTime = startDateTime.plusMinutes(interval);
        }
        return list;
    }

}
