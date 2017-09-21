package com.quanmin.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * Created by llsmp on 2017/7/19.
 */

@SuppressWarnings("ALL")
public class HttpRequestUtils {

    /**
     * httpPost
     * @param url  路径
     * @param nvps 参数
     * @return
     */
    public static Map<String,Object> httpPost(String url, List<NameValuePair> nvps){
        return httpPost(url,nvps, false);
    }

    /**
     * post请求
     * @param url         url地址
     * @param nvp     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    public static Map<String,Object> httpPost(String url,List<NameValuePair> nvp, boolean noNeedResponse){
        //post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != nvp&&nvp.size()>0) {
                //解决中文乱码问题
                method.setEntity(new UrlEncodedFormEntity(nvp,"UTF-8"));
            }
            // method.addHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=UTF-8");
            //method.setHeader(HTTP.CONTENT_ENCODING, "UTF-8");
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/

            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
        }
        return jsonResult;
    }


    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static JSONObject httpGet(String url){
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                // jsonResult = JSONObject.fromObject(strResult);
                System.out.println(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
            }
        } catch (IOException e) {
        }
        return jsonResult;
    }
}
