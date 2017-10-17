package com.quanmin.call;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.quanmin.util.HttpUtils;

@SuppressWarnings("ALL")
public class UserIpInfo {
	/**
	 * 通过ip地址获取所在的省市区
	 */

	public static String getIpInfo(String ip) throws Exception {
		String host = "https://dm-81.data.aliyun.com";
		String path = "/rest/160601/ip/getIpInfo.json";
		String method = "GET";
		String appcode = "fe4638006de64dab8c940de520dd1e42";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("ip", ip);

		HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
		// 获取response的body
		String result = EntityUtils.toString(response.getEntity());
		return result;
	}
}
