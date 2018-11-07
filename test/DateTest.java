import com.huangweihan.xweb.core.utils.DateTimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateTest {

    @org.junit.Test
    public void test() {
        getCurrentTime();
        addTime();
        addDay();
        transfer();
        getTimeList();
        //other();
    }

    /**
     * 获得当前时间
     */
    public void getCurrentTime() {
        //获得当前日期
        String day = DateTimeUtil.getCurrentDay();
        System.out.println("获得当前日期：" + day);
        //获得当前小时
        String hour = DateTimeUtil.getCurrentHour();
        System.out.println("获得当前小时：" + hour);
        //获得当前分钟
        String minute = DateTimeUtil.getCurrentMinute();
        System.out.println("获得当前分钟：" + minute);
        //获得当前时间，并指定返回格式（通用模板）
        String time = DateTimeUtil.getCurrentTime(DateTimeUtil._yyyyMMddHHmm_);
        System.out.println("获得当前时间，并指定返回格式（通用模板）：" + time);
        System.out.println("=========================================");
    }

    /**
     * 时间增加减少
     */
    public void addTime() {
        //当前时间增加5分钟
        String t = DateTimeUtil.addMinute(5);
        System.out.println("当前时间增加5分钟：" + t);
        //当前时间增加5分钟，并指定期望返回格式（通用模板）
        String t1 = DateTimeUtil.addMinute(-(24 * 60), DateTimeUtil.yyyyMMdd);
        System.out.println("当前时间增加5分钟，并指定期望返回格式（通用模板）：" + t1);
        //指定时间增加5分钟
        String t3 = DateTimeUtil.addMinute(5, "20170821000000");
        System.out.println("指定时间增加5分钟：" + t3);
        //指定时间增加5分钟，并指定期望返回格式（通用模板）
        String t4 = DateTimeUtil.addMinute(5, "2017082100", DateTimeUtil._yyyyMMddHHmmss_);
        System.out.println("指定时间增加5分钟，并指定期望返回格式（通用模板）：" + t4);
        System.out.println("=========================================");
    }

    /**
     * 日期增加减少
     */
    public void addDay() {
        String date = DateTimeUtil.addDay(5);
        System.out.println("当前日期增加5天：" + date);
        String date1 = DateTimeUtil.addDay(5, DateTimeUtil._yyyyMMdd_);
        System.out.println("当前日期增加5天，并指定期望返回格式（通用模板）：" + date1);
        String date2 = DateTimeUtil.addDay(5, "20170821");
        System.out.println("指定日期增加5天：" + date2);
        String date3 = DateTimeUtil.addDay(5, "20170821", DateTimeUtil._yyyyMMdd_);
        System.out.println("指定时间增加5天，并指定期望返回格式（通用模板）：" + date3);
        System.out.println("=========================================");
    }

    /**
     * 前后端时间转化（仅支持到时钟秒，不支持日期）
     */
    public void transfer() {
        String t1 = DateTimeUtil.formattedDateTimeFromFront("2017-08-21 00", DateTimeUtil.yyyyMMddHH);
        System.out.println("前端DateTime转后端：" + t1);
        String t2 = DateTimeUtil.formattedDateTimeForFront("2017082100", DateTimeUtil._yyyyMMddHH_);
        System.out.println("后端DateTime转前端：" + t2);
        String t3 = DateTimeUtil.formattedDateFromFront("2017-08-21", DateTimeUtil.yyyyMMdd);
        System.out.println("前端Date转后端：" + t3);
        String t4 = DateTimeUtil.formattedDateForFront("20170821", DateTimeUtil._yyyyMMdd_);
        System.out.println("后端Date转前端：" + t4);
        System.out.println("=========================================");
    }

    /**
     * 时间列表(不包括最后的时间)，增加时间格式选择，最好返回数组
     */
    public void getTimeList() {
        List<String> timeList = DateTimeUtil.getTimeListByMinute("201708210000", "201708210010", 1);
        System.out.println("时间列表：" + timeList.toString());
        System.out.println("=========================================");
    }

    public void other() {
        String[][] arr = getTimeList("201708210000", "201708210010");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


    // 十分钟开外的第一个整五分钟
    public String getCurrentDateMin() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        long time = ((date.getTime() - 600000) / 300000) * 300000;
        date = new Date(time);
        return formatter.format(date);
    }

    //将开始时间到结束时间1min间隔的数组，并获取事件数组对应的前7天的时间值，横坐标0为当天数组，6为第7天数组
    public static String[][] getTimeList(String startTime, String endTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date date1 = null;
        Date date2 = null;
        String result[][] = null;
        try {
            date1 = formatter.parse(startTime);
            date2 = formatter.parse(endTime);
            int listLength = (int) (date2.getTime() - date1.getTime()) / 600000 + 1; //计算开始和结束时间相差多少个1min
            result = new String[8][listLength];
        } catch (ParseException e) {

        }
        Date date = date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Calendar calendarYest = new GregorianCalendar();
        int i = 0;
        while (date.compareTo(date2) <= 0) {
            calendarYest.setTime(date);
            for (int j = 0; j <= 7; j++) {
                result[j][i] = formatter.format(calendarYest.getTime()); //获取时间字符串
            }
            calendar.add(Calendar.MINUTE, 1);
            date = calendar.getTime();
            i++;
        }
        return result;
    }

}
