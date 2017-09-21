package com.quanmin.util;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateConvertUtils {

    public static java.util.Date parse(String dateString,String dateFormat) {
        return parse(dateString, dateFormat,java.util.Date.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends java.util.Date> T parse(String dateString,String dateFormat,Class<T> targetResultType) {
        if(StringUtils.isEmpty(dateString))
            return null;
        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            long time = df.parse(dateString).getTime();
            java.util.Date t = targetResultType.getConstructor(long.class).newInstance(time);
            return (T)t;
        } catch (ParseException e) {
            String errorInfo = "cannot use dateformat:"+dateFormat+" parse datestring:"+dateString;
            throw new IllegalArgumentException(errorInfo,e);
        } catch (Exception e) {
            throw new IllegalArgumentException("error targetResultType:"+targetResultType.getName(),e);
        }
    }
    /* 格式化日期 */
    public static String format(java.util.Date date,String dateFormat) {
        if(date == null)
            return null;
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    /* 格式化日期 */
    public static String getDateFormatStr(String formart) {
        return new SimpleDateFormat(formart).format(new java.util.Date());
    }

    /* 格式化字符串为Date */
    public static Date formatStrToDate(String formart,String str)  {

        Date date=null;
        try {
            date=new SimpleDateFormat(formart).parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /* 格式化Date为 字符串*/
    public static String formatDateToStr(String formart,Date date) {
        if(date==null)
            return "";
        else
            return new SimpleDateFormat(formart).format(date);
    }
    /**
     *
     * @param format
     * @param dateStr
     * @return
     */
    public static String getDateFormatStr(String format,String dateStr){
        return new SimpleDateFormat(format).format(dateStr);
    }

    /* 获得当前的日期时间分秒 yyyy-MM-dd HH:mm:ss*/
    public static String getCurrentTimeWithFormat(String format){
        return getDateFormatStr(format);
    }

    /* 获得当前的日期时间分秒 yyyy-MM-dd HH:mm:ss*/
    public static String getCurrentTime(){
        return getDateFormatStr("yyyy-MM-dd HH:mm:ss");
    }

    /* 获得当前日期 yyyy-MM-dd*/
    public static String getCurrentDate(){
        return getDateFormatStr("yyyy-MM-dd");
    }

    /* 生成目录名称 格式20061111*/
    public static String  getDirName(){
        return getDateFormatStr("yyyyMMdd");
    }

    /* 生成文件名称 格式20061111111111*/
    public static String getFileName(){
        return getDateFormatStr("yyyyMMddhhmmss");
    }

    public static String getMultiFileName(){
        Random rnd = new Random();//Random类
        return getDateFormatStr("yyyyMMddhhmmss"+String.valueOf(rnd.nextInt(100)));
    }

    /* 获得某一时间的极度起始日期*/
    public static String[] getQuarterDate(String tradeDate) {
        String[] array = new String[2];

        try {
            java.util.Date date = new java.text.SimpleDateFormat("yyyy-MM-dd")
                    .parse(tradeDate);
            Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(date);
            if ((cal.get(cal.MONTH) + 1) % 3 == 0)// 季度结束月
            {
                array[1] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + cal.getActualMaximum(cal.DATE);// 结束日期
                cal.add(cal.MONTH, -2);
                array[0] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + cal.getActualMinimum(cal.DATE);// 开始日期
            } else if ((cal.get(cal.MONTH) + 2) % 3 == 0)// 季度中间月
            {
                cal.add(cal.MONTH, -1);
                array[0] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + cal.getActualMinimum(cal.DATE);// 开始日期
                cal.add(cal.MONTH, +2);
                array[1] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + cal.getActualMaximum(cal.DATE);// 结束日期
            } else if ((cal.get(cal.MONTH) + 3) % 3 == 0)// 季度起始月
            {
                array[0] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + cal.getActualMinimum(cal.DATE);// 开始日期
                cal.add(cal.MONTH, +2);
                array[1] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + cal.getActualMaximum(cal.DATE);// 结束日期
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return array;
    }
    /**
     * 获取旬
     * @param tradeDate
     * @return
     */
    public static String[] getXunDate(String tradeDate) {
        String[] array = new String[2];

        try {
            java.util.Date date = new java.text.SimpleDateFormat("yyyy-MM-dd")
                    .parse(tradeDate);
            Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(date);
            if (cal.get(cal.DATE)<= 10)// 上旬
            {
                array[1] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + "10";// 结束日期

                array[0] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + "1";// 开始日期
            }
            else if (cal.get(cal.DATE)>10&&cal.get(cal.DATE)<=20)//中旬
            {
                array[0] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + "11"; // 开始日期
                array[1] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + "20"; // 结束日期
            }
            else if (cal.get(cal.DATE)>20)// 下旬
            {
                array[0] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" +"21";// 开始日期
                array[1] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + cal.getActualMaximum(cal.DATE);// 结束日期
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return array;
    }

    /**
     * 获取半月
     * @param tradeDate
     * @return
     */
    public static String[] getBanyueDate(String tradeDate) {
        String[] array = new String[2];

        try {
            java.util.Date date = new java.text.SimpleDateFormat("yyyy-MM-dd")
                    .parse(tradeDate);
            Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(date);
            if (cal.get(cal.DATE)<= 15)		// 上半月
            {
                array[0] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + "1";// 开始日期

                array[1] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1)  + "-" + "15";// 结束日期
            }
            else if (cal.get(cal.DATE)>15)	// 下半月
            {
                array[0] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" +"16";// 开始日期

                array[1] = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + cal.getActualMaximum(cal.DATE);// 结束日期
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return array;
    }

    /**
     * 获取月
     * @param tradeDate
     * @return
     */
    public static String[] getYueDate(String tradeDate) {
        String[] array = new String[2];
        try {
            java.util.Date date = new java.text.SimpleDateFormat("yyyy-MM-dd")
                    .parse(tradeDate);
            Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(date);
            String datestr[]=DateConvertUtils.formatDateToStr("yyyy-MM-dd", date).split("-");
            int firstDay=cal.getActualMinimum(cal.DATE);
            int lastDay=cal.getActualMaximum(cal.DATE);
            array[0]=datestr[0]+"-"+datestr[1]+"-"+firstDay;
            array[1]=datestr[0]+"-"+datestr[1]+"-"+lastDay;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return array;
    }


    /**
     * 计算两日期之间差的天数
     * @param time1
     * @param time2
     * @return
     */
    public static long getQuot(String time1,String time2){
        long quot=0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date1=sdf.parse(time1);
            Date date2=sdf.parse(time2);
            quot=date1.getTime()-date2.getTime();
            quot=quot/1000/60/60/24;
        }catch(Exception e){
            e.printStackTrace();
        }
        return quot;
    }

    /**
     * @Title: getMonth
     * @Description: 获取两个时间相差的月份
     * @param  start 起始时间
     * @param  end   结束时间
     * @param @return
     * @return int
     * @throws
     */
    public static int getMonth(Date start,Date end){
        try{
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(start);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(end);
            Calendar temp = Calendar.getInstance();
            temp.setTime(end);
            temp.add(Calendar.DATE, 1);

            int year = endCalendar.get(Calendar.YEAR)- startCalendar.get(Calendar.YEAR);
            int month = endCalendar.get(Calendar.MONTH)- startCalendar.get(Calendar.MONTH);

            if ((startCalendar.get(Calendar.DATE) == 1)&& (temp.get(Calendar.DATE) == 1)) {
                return year * 12 + month + 1;
            } else if ((startCalendar.get(Calendar.DATE) != 1)&& (temp.get(Calendar.DATE) == 1)) {
                return year * 12 + month;
            } else if ((startCalendar.get(Calendar.DATE) == 1)&& (temp.get(Calendar.DATE) != 1)) {
                return year * 12 + month;
            } else {
                return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }


    public static void main(String[] args) {
        String time = new Date().getTime()+"";
        String time1 = new Date().getTime()+"";

    }
}

