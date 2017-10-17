package com.quanmin.call;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quanmin.util.Commons;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/6/27.
 */


@SuppressWarnings("ALL")
public class UserReportPDFInfo {

    /**
     * 根据手机号和身份证号。获取用户报告
     *
     * @param phone
     * @param cardId
     * @return
     */
    public static List<Map<String, String>> getUserReportPDFInfoByPhoneAndIdNo(String phone, String cardId) {
        try {
            String url = Commons.REPORTPDFINFO;
            HttpPost httppost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            List<Map<String, String>> list = new ArrayList<>();
            if (cardId != null && cardId != "") {
                params.add(new BasicNameValuePair("idCard", cardId));
            } else {
                params.add(new BasicNameValuePair("phone", phone));
            }

            httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse response = new DefaultHttpClient().execute(httppost);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 如果状态码为200,就是正常返回
                String result = EntityUtils.toString(response.getEntity());
                // // 解析数据
                JSONObject rootjsonBean = JSONObject.parseObject(result);
                Object rescode = rootjsonBean.get("code");
                if ("200".equals(rescode.toString())) {
                    Object value = rootjsonBean.get("value");
                    if (null != value && !"".equals(value)) {
                        JSONArray jsonBeans = JSONObject.parseArray(value.toString());
                        for (int i = 0; i < jsonBeans.size(); i++) {
                            Map<String, String> map = new HashMap<>();
                            JSONObject jsonBean = JSONObject.parseObject(jsonBeans.get(i).toString());

                            map.put("reportId", jsonBean.get("reportId").toString());
                            map.put("userName",  jsonBean.get("userName").toString());
                            map.put("creattime",  jsonBean.get("creattime").toString());
                            map.put("reporturl",  jsonBean.get("reporturl").toString());
                            list.add(map);
                        }
                    }
                }
                return list;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void main(String[] args) {
        getUserReportPDFInfoByPhoneAndIdNo("17778067291", "430524199305192439");
    }
}
