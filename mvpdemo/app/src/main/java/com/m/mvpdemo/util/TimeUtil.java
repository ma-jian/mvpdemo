package com.m.mvpdemo.util;

import android.text.Html;
import android.text.Spanned;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class TimeUtil {
    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR   = 3600;
    private static final long ONE_DAY    = 86400;
    private static final long ONE_MONTH  = 2592000;
    private static final long ONE_YEAR   = 31104000;

    public static long getCurrentTime() {
        long timedeta = System.currentTimeMillis() / 1000;
        return timedeta;
    }

    public static String getBetween(long time) {
        long timedeta = System.currentTimeMillis() / 1000 - time;
        if (timedeta < 60) {
            return "刚刚";
        } else if (timedeta > 60 && timedeta < 3600) {
            return Math.round(timedeta / 60) + "分钟前";
        } else if (timedeta < (3600 * 24)) {
            return Math.round(timedeta / 3600) + "小时前";
        } else {
            int n = Math.round(timedeta / (3600 * 24));
            if (n < 3) {
                return n + "天前";
            } else {
                return toDateNoYear(time);
            }
        }
    }

    public static String getCutBetween(long timedeta) {
        //long timedeta = System.currentTimeMillis() / 1000 - time;
        if (timedeta < 60) {
            return "还剩" + timedeta + "秒";
        } else if (timedeta > 60 && timedeta < 3600) {
            return "还剩" + Math.round(timedeta / 60) + "分钟";
        } else if (timedeta < (3600 * 24)) {
            return "还剩" + Math.round(timedeta / 3600) + "小时";
        } else {
            int n = Math.round(timedeta / (3600 * 24));
            if (n < 3) {
                return "还剩" + n + "天";
            } else {
                return "还剩" + n + "天";
            }
        }
    }

    public static String getTimeOver(long timedeta) {
        //long timedeta = System.currentTimeMillis() / 1000 - time;
        if (timedeta > 0 && timedeta < 60) {
            return timedeta + "秒后";
        } else if (timedeta > 60 && timedeta < 3600) {
            return Math.round(timedeta / 60) + "分钟后";
        } else if (timedeta < (3600 * 24)) {
            return Math.round(timedeta / 3600) + "小时后";
        } else if (timedeta <= 0) {
            return "";
        } else {
            int n = Math.round(timedeta / (3600 * 24));
            if (n < 3) {
                return n + "天后";
            } else {
                return n + "天后";
            }
        }
    }

    public static String getTimeOverStr(long time) {
        //long timedeta = System.currentTimeMillis() / 1000 - time;
        long timeDiff = (int) time - Math.round(System.currentTimeMillis() / 1000);
        if (timeDiff <= 0) {
            return "已";
        } else {
            return getTimeOver(timeDiff);
        }
    }

    public static String toDayAndHour(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(new Date(time * 1000)).toString();
    }


    public static String toDateAndTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time * 1000)).toString();
    }


    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);

            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /***
     * 转换倒计时
     **/
    public static String toEndTime(long time) {
        Date endDate = parseDate(toDate(time), "yyyy-MM-dd");
        int days = daysBetween(parseDate(toDate(System.currentTimeMillis() / 1000), "yyyy-MM-dd"), endDate);
        //LogUtils.d("toEndTime days:" + days);
        String overtimestr = toTime(time);
        if (days == 0) {//当天及当天之后，<0就是在日期之前
            overtimestr = "今日" + overtimestr;
        } else if (days == 1) {
            overtimestr = "明日" + overtimestr;
        } else if (days == 2) {
            overtimestr = "后日" + overtimestr;
        } else {
            overtimestr = toDateNoYear(time) + " " + overtimestr;
        }
        overtimestr = overtimestr.replace("明日00:00", "今日24:00");
        overtimestr = overtimestr.replace("后日00:00", "明日24:00");
        return overtimestr;
    }

    public static String toTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date(time * 1000)).toString();
    }

    public static String toDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(time * 1000)).toString();
    }

    public static String toDateNoYear(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(new Date(time * 1000)).toString();
    }

    public static String showTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        return sdf.format(new Date()).toString();
    }

    public static String showTodayHourDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH时");
        return sdf.format(new Date()).toString();
    }

    public static String showDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm月dd日");
        return sdf.format(new Date(time * 1000)).toString();
    }

    public static String toDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date()).toString();
    }

    public static String todayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date()).toString();
    }

    public static String showTomorrowDate() {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        return formatter.format(date);
    }

    public static String showYesterdayDate(long time) {
        Date date = new Date();//取时间
        date.setTime(time * 1000);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        return formatter.format(date);
    }


    public static String tomorrowDate() {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public static String endHourDate(int selection) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, selection * 2);//退后 ** 小时
        date = calendar.getTime(); //这个时间就是日期往后推的结果
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日HH时");
        return formatter.format(date);
    }

    public static String endYearAndHourDate(int selection) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, selection * 2);//退后 ** 小时
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(date);
    }


    public static String afterDayDate(int day) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日HH时");
        return formatter.format(date);
    }


    public static String toYear(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(new Date(time * 1000)).toString();
    }

    public static String toMonth(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        return sdf.format(new Date(time * 1000)).toString();
    }

    public static String toDay(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return sdf.format(new Date(time * 1000)).toString();
    }

    public static String toNDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(time * 1000).toString();
    }

    public static String toMDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(new Date(time * 1000)).toString();
    }

    public static String toWeekAndDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return sdf.format(new Date(time * 1000)).toString();
    }

    public static String toDateWithFormat(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time * 1000)).toString();
    }

    public static int getBetweenDays(long start, long end) {
        return ((int) ((end - start) / (long) (60 * 60 * 24))) + 1;
    }

    /***
     * 倒计时 formatLongToTimeStr(ms, "HH小时mm分钟ss秒")
     *
     * @param l
     * @param format
     * @return
     */
    public static String formatLongToTimeStr(Long l, String format) {
        int day = 0;
        int hour = 0;
        int minute = 0;
        int second = 0;

        second = (int) Math.floor(l / 1000);
        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        if (hour > 24) {
            day = hour / 24;
            hour = hour % 24;
        }
        String str = StringUtils.replaceEach(format, new String[]{"dd", "HH",
                "mm", "ss"}, new String[]{String.format("%0$02d", day),
                String.format("%0$02d", hour), String.format("%0$02d", minute),
                String.format("%0$02d", second)});
        str = str.replaceAll("(?:00天|00小时)", "");
        return str;
    }

    public static String formatToTimeStr(Long l, String format) {
        int day = 0;
        int hour = 0;
        int minute = 0;
        int second = 0;

        second = (int) Math.floor(l / 1000);
        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        if (hour > 24) {
            day = hour / 24;
            hour = hour % 24;
        }
        String str = StringUtils.replaceEach(format, new String[]{"dd", "HH",
                "mm", "ss"}, new String[]{String.format("%0$02d", day),
                String.format("%0$02d", hour), String.format("%0$02d", minute),
                String.format("%0$02d", second)});
        str = str.replaceAll("(?:00天|00小时)", "");
        return str;
    }

    public static boolean isCurday(long time, String date) {
        String curDay = toDate(time);
        return curDay.equals(date);
    }

    /***
     * @param startime
     * @param endtime
     * @return
     * @throws ParseException
     */
    public static List<String> getBetweenCalendar(long startime, long endtime) {
        List<String> list = new ArrayList<String>();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date dateBegin = null;
        Date dateEnd = null;
        try {
            dateBegin = formater.parse(formater
                    .format((startime - 86400) * 1000));
            dateEnd = formater.parse(formater.format((endtime - 86400) * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        if (dateBegin != null && dateEnd != null) {
            while (dateBegin.compareTo(dateEnd) <= 0) {
                calendar.setTime(dateBegin);
                calendar.add(Calendar.DATE, 1);
                dateBegin = calendar.getTime();
                list.add(formater.format(dateBegin));
            }
        }
        return list;
    }

    public static String toDateStrWithFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date).toString();
    }

    public static Date parseDate(String s, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static long getDateSeconds(String s, String format) {
        Date d = parseDate(s, format);
        if (d != null) {
            return d.getTime() / 1000;
        }
        return System.currentTimeMillis() / 1000;

    }

    /**
     * deal time string
     *
     * @param time
     * @return
     */
    public static Spanned getCountdown(long time) {
        Spanned str;
        StringBuffer returnString = new StringBuffer();
        long day = time / (24 * 60 * 60);
        long hours = (time % (24 * 60 * 60)) / (60 * 60);
        long minutes = ((time % (24 * 60 * 60)) % (60 * 60)) / 60;
        long second = ((time % (24 * 60 * 60)) % (60 * 60)) % 60;
        String dayStr = String.valueOf(day);
        String hoursStr = timeStrFormat(String.valueOf(hours));
        String minutesStr = timeStrFormat(String.valueOf(minutes));
        String secondStr = timeStrFormat(String.valueOf(second));
        if (day > 0) {
            returnString.append(dayStr).append("天");
        }
        if (hours > 0) {
            returnString.append(hoursStr).append("时");
        }
        returnString.append(minutesStr).append("分").append(secondStr).append("秒");
        str = Html.fromHtml(returnString.toString());
        // return returnString.toString();
        return str;
    }

    public static String[] getCountdownArray(long time) {
        long day = time / (24 * 60 * 60);
        long hours = (time % (24 * 60 * 60)) / (60 * 60);
        long minutes = ((time % (24 * 60 * 60)) % (60 * 60)) / 60;
        long second = ((time % (24 * 60 * 60)) % (60 * 60)) % 60;
        String[] times = new String[3];
        //times[0] = String.valueOf(day);
        times[0] = timeStrFormat(String.valueOf(hours));
        times[1] = timeStrFormat(String.valueOf(minutes));
        times[2] = timeStrFormat(String.valueOf(second));
        return times;
    }

    /**
     * format time
     *
     * @param timeStr
     * @return
     */
    private static String timeStrFormat(String timeStr) {
        switch (timeStr.length()) {
            case 1:
                timeStr = "0" + timeStr;
                break;
        }
        return timeStr;
    }

    public static String toToday(String datestr) {
        Date d = parseDate(datestr, "yyyy-MM-dd HH:mm:ss");
        return toToday(d);
    }

    /**
     * 距离截止日期还有多长时间
     *
     * @param date
     * @return
     */
    public static String fromDeadline(Date date) {
        long deadline = date.getTime() / 1000;
        long now = (new Date().getTime()) / 1000;
        long remain = deadline - now;
        if (remain <= ONE_HOUR)
            return "只剩下" + remain / ONE_MINUTE + "分钟";
        else if (remain <= ONE_DAY)
            return "只剩下" + remain / ONE_HOUR + "小时"
                    + (remain % ONE_HOUR / ONE_MINUTE) + "分钟";
        else {
            long day = remain / ONE_DAY;
            long hour = remain % ONE_DAY / ONE_HOUR;
            long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return "只剩下" + day + "天" + hour + "小时" + minute + "分钟";
        }

    }

    /**
     * 距离今天的绝对时间
     *
     * @param date
     * @return
     */
    public static String toToday(Date date) {
        long time = date.getTime() / 1000;
        long now = (new Date().getTime()) / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE + "分钟";
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟";
        else if (ago <= ONE_DAY * 2)
            return "昨天" + (ago - ONE_DAY) / ONE_HOUR + "点" + (ago - ONE_DAY)
                    % ONE_HOUR / ONE_MINUTE + "分";
        else if (ago <= ONE_DAY * 3) {
            long hour = ago - ONE_DAY * 2;
            return "前天" + hour / ONE_HOUR + "点" + hour % ONE_HOUR / ONE_MINUTE
                    + "分";
        } else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            long hour = ago % ONE_DAY / ONE_HOUR;
            long minute = ago % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return day + "天前" + hour + "点" + minute + "分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            long hour = ago % ONE_MONTH % ONE_DAY / ONE_HOUR;
            long minute = ago % ONE_MONTH % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return month + "个月" + day + "天" + hour + "点" + minute + "分前";
        } else {
            long year = ago / ONE_YEAR;
            long month = ago % ONE_YEAR / ONE_MONTH;
            long day = ago % ONE_YEAR % ONE_MONTH / ONE_DAY;
            return year + "年前" + month + "月" + day + "天";
        }

    }

    /***
     * 时间文本转时间戳 (包含今日/明日)
     *
     * @param hms
     * @return
     */
    public static long HmsToTime(String hms) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String hmsStr = "";
        if (hms.startsWith("今日")) {
            hms = hms.replace("今日", "");
            hmsStr = TimeUtil.toDate() + " " + hms + ":00";
        } else if (hms.startsWith("明日")) {
            hms = hms.replace("明日", "");
            hmsStr = TimeUtil.tomorrowDate() + " " + hms + ":00";
        } else {
            hmsStr = TimeUtil.toDate() + " " + hms + ":00";
        }
        Date date = null;
        try {
            date = sdf.parse(hmsStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static boolean isShow(String time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            long date = format.parse(time).getTime();
            long nowTime = System.currentTimeMillis();
            if (nowTime <= date) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
