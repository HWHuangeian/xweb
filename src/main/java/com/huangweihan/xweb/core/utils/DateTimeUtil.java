package com.huangweihan.xweb.core.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 时间工具类
 *
 * @author: 80234536
 * @date: 2018/10/22
 */
public class DateTimeUtil {

    public static final DateTimeFormatter _yyyyMMddHHmmss_ = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter _yyyyMMddHHmm_ = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter _yyyyMMddHH_ = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    public static final DateTimeFormatter _yyyyMMdd_ = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter _yyyyMM_ = DateTimeFormatter.ofPattern("yyyy-MM");
    public static final DateTimeFormatter _MMdd_ = DateTimeFormatter.ofPattern("MM-dd");
    public static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    public static final DateTimeFormatter yyyyMMddHH = DateTimeFormatter.ofPattern("yyyyMMddHH");
    public static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter yyyyMM = DateTimeFormatter.ofPattern("yyyyMM");
    public static final DateTimeFormatter yyyy = DateTimeFormatter.ofPattern("yyyy");

    /**
     * 解析后端日期字符串
     *
     * @param source yyyy yyyyMM yyyyMMdd
     * @return
     */
    private static LocalDate parseDateSource(String source) {
        if (source.length() == 4) {
            return LocalDate.parse(source, yyyy);
        }
        if (source.length() == 6) {
            return LocalDate.parse(source, yyyyMM);
        } else if (source.length() == 8) {
            return LocalDate.parse(source, yyyyMMdd);
        }
        return null;
    }

    /**
     * 解析后端时间字符串
     *
     * @param source yyyyMMddHH yyyyMMddHHmm yyyyMMddHHmmss
     * @return
     */
    private static LocalDateTime parseDateTimeSource(String source) {
        if (source.length() == 10) {
            return LocalDateTime.parse(source, yyyyMMddHH);
        } else if (source.length() == 12) {
            return LocalDateTime.parse(source, yyyyMMddHHmm);
        } else if (source.length() == 14) {
            return LocalDateTime.parse(source, yyyyMMddHHmmss);
        }
        return null;
    }

    /**
     * 解析前端日期字符串
     *
     * @param frontSource MM-dd yyyy-MM yyyy-MM-dd
     * @return
     */
    private static LocalDate parseDateSourceFromFront(String frontSource) {
        if (frontSource.length() == 5) {
            return LocalDate.parse(frontSource, _MMdd_);
        } else if (frontSource.length() == 7) {
            return LocalDate.parse(frontSource, _yyyyMM_);
        } else if (frontSource.length() == 10) {
            return LocalDate.parse(frontSource, _yyyyMMdd_);
        }
        return null;
    }

    /**
     * 解析前端时间字符串
     *
     * @param frontSource yyyy-MM-dd HH   yyyy-MM-dd HH:mm   yyyy-MM-dd HH:mm:ss
     * @return
     */
    private static LocalDateTime parseDateTimeSourceFromFront(String frontSource) {
        if (frontSource.length() == 13) {
            return LocalDateTime.parse(frontSource, _yyyyMMddHH_);
        } else if (frontSource.length() == 16) {
            return LocalDateTime.parse(frontSource, _yyyyMMddHHmm_);
        } else if (frontSource.length() == 19) {
            return LocalDateTime.parse(frontSource, _yyyyMMddHHmmss_);
        }
        return null;
    }

    /**
     * 获得当前日期
     *
     * @return
     */
    public static String getCurrentDay() {
        LocalDateTime dateTime = LocalDateTime.now();
        String date = dateTime.format(yyyyMMdd);
        return date;
    }

    /**
     * 获得当前小时
     *
     * @return
     */
    public static String getCurrentHour() {
        LocalDateTime dateTime = LocalDateTime.now();
        String date = dateTime.format(yyyyMMddHH);
        return date;
    }

    /**
     * 获得当前分钟
     *
     * @return
     */
    public static String getCurrentMinute() {
        LocalDateTime dateTime = LocalDateTime.now();
        String date = dateTime.format(yyyyMMddHHmm);
        return date;
    }

    /**
     * 获得当前时间/日期（通用模板）
     *
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
     * 当前日期增加 interval 天
     *
     * @param interval
     * @return
     */
    public static String addDay(int interval) {
        LocalDate date = LocalDate.now();
        return date.plusDays(interval).format(yyyyMMdd);
    }

    /**
     * 当前日期增加 interval 天，并指定期望返回格式
     *
     * @param interval        时间数
     * @param targetFormatter 期望返回格式
     * @return
     */
    public static String addDay(int interval, DateTimeFormatter targetFormatter) {
        LocalDate date = LocalDate.now();
        return date.plusDays(interval).format(targetFormatter);
    }

    /**
     * 指定日期增加 interval 天
     *
     * @param interval +/- interval
     * @param source   源时间字符串
     * @return
     */
    public static String addDay(int interval, String source) {
        LocalDate date = parseDateSource(source);
        return date.plusDays(interval).format(yyyyMMdd);
    }

    /**
     * 指定日期增加 interval 天，并指定返回格式
     *
     * @param interval        时间数
     * @param source          源时间字符串
     * @param targetFormatter 期望返回格式
     * @return
     */
    public static String addDay(int interval, String source, DateTimeFormatter targetFormatter) {
        LocalDate date = parseDateSource(source);
        return date.plusDays(interval).format(targetFormatter);
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
     *
     * @param frontSource     源时间
     * @param targetFormatter 期望返回格式
     * @return
     */
    public static String formattedDateTimeFromFront(String frontSource, DateTimeFormatter targetFormatter) {
        LocalDateTime sourceDateTime = parseDateTimeSourceFromFront(frontSource);
        return sourceDateTime.format(targetFormatter);
    }

    /**
     * 解析前端日期，并指定返回格式
     *
     * @param frontSource     源时间
     * @param targetFormatter 期望返回格式
     * @return
     */
    public static String formattedDateFromFront(String frontSource, DateTimeFormatter targetFormatter) {
        LocalDate sourceDate = parseDateSourceFromFront(frontSource);
        return sourceDate.format(targetFormatter);
    }

    /**
     * 解析后端时间，并指定返回格式
     *
     * @param source          源时间
     * @param targetFormatter 期望返回格式
     * @return
     */
    public static String formattedDateTimeForFront(String source, DateTimeFormatter targetFormatter) {
        LocalDateTime sourceDateTime = parseDateTimeSource(source);
        return sourceDateTime.format(targetFormatter);
    }

    /**
     * 解析后端日期，并指定返回格式
     *
     * @param source          源时间
     * @param targetFormatter 期望返回格式
     * @return
     */
    public static String formattedDateForFront(String source, DateTimeFormatter targetFormatter) {
        LocalDate sourceDate = parseDateSource(source);
        return sourceDate.format(targetFormatter);
    }

    /**
     * 获得开始时间到结束时间内的n分钟间隔的时间列表
     *
     * @param startTime 起始时间
     * @param endTime   结束时间（不包括)
     * @param interval  时间间隔
     * @return
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

    /**
     * 指定时间是否在两个时间之间
     *
     * @param startDateTime
     * @param endDateTime
     * @param target
     * @return
     */
    public static boolean isDateTimeBetween(String startDateTime, String endDateTime, String target) {
        LocalDateTime s = parseDateTimeSource(startDateTime);
        LocalDateTime e = parseDateTimeSource(endDateTime);
        LocalDateTime t = parseDateTimeSource(target);
        if (t.isAfter(s) && t.isBefore(e)) {
            return true;
        }
        return false;
    }

    /**
     * 指定日期是否在两个日期之间
     *
     * @param startDate
     * @param endDate
     * @param target
     * @return
     */
    public static boolean isDateBetween(String startDate, String endDate, String target) {
        LocalDate s = parseDateSource(startDate);
        LocalDate e = parseDateSource(endDate);
        LocalDate t = parseDateSource(target);
        if (t.isAfter(s) && t.isBefore(e)) {
            return true;
        }
        return false;
    }

}
