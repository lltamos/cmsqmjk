package com.quanmin.test;

import java.util.ArrayList;
import java.util.List;

import com.quanmin.util.Commons;

public class TestPersonInfo {

	public static void main(String[] args) {
		
		String url = Commons.LOGINUSERINFO;
		// 输出文件路径
//		PostMethod postMethod = new PostMethod(url);
		
//		NameValuePair phonePair = new NameValuePair("phone", "17778067291");
//		NameValuePair idCardPair =new NameValuePair("idCard", "430524199305292438") ;
//		
//		NameValuePair[] pairs = {new NameValuePair("idCard", "430524199305292438")};
//		
//		postMethod.setRequestBody(pairs);
//		HttpClient httpclient = new HttpClient();
//		int statusCode = httpclient.executeMethod(postMethod);
//		String responseBodyAsString = postMethod.getResponseBodyAsString();
//		// 解析数据
//		JSONObject rootjsonBean = JSONObject.fromObject(responseBodyAsString);
//		System.out.println(rootjsonBean.toString());
//		Object rescode= rootjsonBean.get("code");
		
		List<String> list = new ArrayList<>();
		list.add("abc");
		list.add("bcd");
		String[] arr= new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i]=list.get(i);
		}
		
		for (String string : arr) {
			System.out.println(string);
		}
	}
}
