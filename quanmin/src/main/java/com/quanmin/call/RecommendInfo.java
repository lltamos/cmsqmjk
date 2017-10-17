package com.quanmin.call;

import com.quanmin.util.LoadPropertiesDataUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 同步微信端代码
 */
public class RecommendInfo {

    /**
     * 向微信端发送消息
     *
     * @param phone
     * @param createTime
     * @return
     */
    public static void sendRecommendMsgToWeChatp(String phone, String createTime   ) {
        try {

            String recommendUrl= LoadPropertiesDataUtils.getValue("php.recommend");
            HttpPost httppost = new HttpPost("recommendUrl");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            List<Map<String, String>> list = new ArrayList<>();
                params.add(new BasicNameValuePair("user_mobile", phone));
                params.add(new BasicNameValuePair("logintime", createTime));
                params.add(new BasicNameValuePair("activity_id", "1"));
                params.add(new BasicNameValuePair("status", "1"));
            httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse response = new DefaultHttpClient().execute(httppost);
            if (response.getStatusLine().getStatusCode() == 200) {

                System.out.println("通知成功");
            } else {
                System.out.println("通知失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
