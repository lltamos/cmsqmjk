package com.quanmin.test;

import com.alibaba.fastjson.JSONObject;
import com.quanmin.model.custom.PositionInfo;
import com.quanmin.util.Commons;

import java.net.URL;

public class TestMap {
    public static void main(String[] args) {
        String add = getAdd("116.45054696427663", "40.00439256944189");
        JSONObject jsonObject = JSONObject.parseObject(add);
        JSONObject parseObject = JSONObject.parseObject(jsonObject.get("result").toString());
        String addressComponent = parseObject.get("addressComponent").toString();
        PositionInfo positionInfo = jsonObject.parseObject(addressComponent, PositionInfo.class);

        System.out.println(positionInfo);
        System.out.println(addressComponent);
    }


    public static String getAdd(String log, String lat) {
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
