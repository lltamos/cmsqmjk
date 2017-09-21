package com.quanmin.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
    private static String formatyyyyMMdd = "yyyy-MM-dd";
    private static String formatyyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    private static DateFormat dateFormatyyyyMMdd = new SimpleDateFormat(formatyyyyMMdd);

    /**
     * 字符串转换成日期
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date StringToDateyyyyMMdd(String dateStr) throws ParseException {
        return dateFormatyyyyMMdd.parse(dateStr);
    }
    public static Date StringToDateformatyyyyMMddHHmmss(String dateStr) throws ParseException {
        return dateFormatyyyyMMdd.parse(dateStr);
    }

    public static Date datePlusOne(Date date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 日期转换成指定格式
     *
     * @param date
     * @return
     */
    public static String dataFormatString(Date date) throws ParseException {
        String substring = dateFormatyyyyMMdd.format(date);
        if (substring.contains(".")) {
            return substring.substring(0, substring.lastIndexOf("."));
        }
        return substring;

    }

    /**
     * 时间显示
     * <p>
     * 1、今天：60分钟以内显示XX分钟以前，如“36分钟以前”，超过60分钟显示具体时间如“今天10:22”；
     * <p>
     * 2、昨天：显示昨天+具体时间，如“昨天10:22”，前天显示前天+具体时间，如“前天10:22”；
     * <p>
     * 3、超过3天只显示具体日期，如“2016-11-25”
     *
     * @param create
     * @return
     * @throws ParseException
     */
    public static String millisecondFormatTime(Date create) throws ParseException {
        Long ms = new Date().getTime() - create.getTime();
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;
        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;
        StringBuffer sb = new StringBuffer();
        switch (day.intValue()) {
            case 2:
                return sb.append("前天" + hour + ":" + minute).toString();
            case 1:
                return sb.append("昨天" + hour + ":" + minute).toString();
            case 0:
                if (minute < 60) {
                    return sb.append(minute + "分钟以前").toString();
                }
                return sb.append("今天" + hour + ":" + minute).toString();

            default:
                return dataFormatString(create);
        }
    }

//    public static void main(String[] args) throws ParseException, InterruptedException {
        // Date date = new Date();
        // String formatTime2 = DateFormatUtil.millisecondFormatTime(date);
//		String formatTime2 = "2017-06-06 11:36:54.0";
        // if(formatTime2.contains(".")){
        // System.out.println(11111);
        // }
//		String string = formatTime2.substring(0, formatTime2.lastIndexOf("."));
//		System.out.println(string);
//        for (int i = 1; i < 100; i++) {
//            long l = System.currentTimeMillis();
//            System.out.println(l);
//            Thread.sleep(1000L);
//        }
//        String dirName = DateConvertUtils.getDirName();
//        String substring = dirName.substring(2);
//
//        System.out.println(substring);
//    }

    public static void main(String[] args) {


        int currentMaxDays = getCurrentMonthDay();

        int maxDaysByDate = getDaysByYearMonth(2013, 2);

        String week = getDayOfWeekByDate("2012-12-25");

        System.out.println("本月天数：" + currentMaxDays);
        System.out.println("2012年11月天数：" + maxDaysByDate);
        System.out.println("2012-12-25是：" + week);
    }

    /**
     * 获取当月的 天数
     * */
    public static int getCurrentMonthDay() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     * */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据日期 找到对应日期的 星期
     */
    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            dayOfweek = str;

        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfweek;
    }
}
