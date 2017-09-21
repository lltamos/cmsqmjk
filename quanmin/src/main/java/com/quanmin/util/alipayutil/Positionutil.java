package com.quanmin.util.alipayutil;

import com.quanmin.util.Commons;

import java.net.URL;

/**
 * Created by yang on 2017/6/22.
 * 获取百度地图api
 */
public class Positionutil {

    public static String getPosition(String log, String lat) {
        // lat 小 log 大
        // 参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)

        String urlString = Commons.BAIDU_MAP +
                lat + "," + log;
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(
                    new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        System.out.println(res);
        return res;
    }
}
