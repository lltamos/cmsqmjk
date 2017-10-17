package com.quanmin.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang on 2017/6/27.
 */
public class TestJson {
    public static void main(String[] args) throws ParseException {
        Date date=new Date(Long.parseLong("1508284800000"));
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date parse=simpleDateFormat.parse("1970-01-01 08:00:00");
        System.out.println(simpleDateFormat.format("1508284800000"));


    }
}
