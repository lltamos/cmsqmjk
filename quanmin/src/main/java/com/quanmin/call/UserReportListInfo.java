package com.quanmin.call;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quanmin.model.SysUser;
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

/**
 * 用户报告列表
 */
public class UserReportListInfo {
	public static List<HashMap<String, Object>> getUserReportListInfoByPhoneOrIdNo(SysUser user, String type,
			String hraid) {

		List<HashMap<String, Object>> list = new ArrayList<>();
		try {
			String url = Commons.REPROTLISTDATA;
			HttpPost httppost = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// if (user.getIdNo() != null && user.getIdNo() != "") {
			params.add(new BasicNameValuePair("idCard", user.getIdNo()));
			// } else {
			params.add(new BasicNameValuePair("phone", user.getPhone()));
			// }
			params.add(new BasicNameValuePair("type", type));
			params.add(new BasicNameValuePair("hraid", null != hraid ? hraid : ""));
			httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = new DefaultHttpClient().execute(httppost);

			// System.out.println("status:"+response.getStatusLine().getStatusCode());

			// 如果状态码为200,正常返回
			if (response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity());
//				System.out.println("returnResult:"+result);
				// 解析数据
				JSONObject rootjsonBean = JSONObject.parseObject(result);
				Object rescode = rootjsonBean.get("code");
				if (rescode.toString().equals("200")) {
					Object data = rootjsonBean.get("data");
					System.out.println(data.toString());
					JSONArray parseArray = JSONObject.parseArray(data.toString());
//					System.out.println(parseArray.get(0));
					for (int i = 0; i < parseArray.size(); i++) {
						HashMap<String, Object> map = new HashMap<>();
						Object array = parseArray.get(i);
						JSONObject bean = JSONObject.parseObject(array.toString());
						map.put("reportTime", bean.get("create_time_str").toString().split(" ")[0]);
						map.put("hraid", bean.get("hraid"));
						map.put("username", bean.get("username"));
						map.put("imgurl", "/upload/hra.png");
						map.put("phone", user.getPhone());
						map.put("idCard", user.getIdNo());
//						if(i==0){
//							String str =array.toString();
//							map.put("result",str.replace("\\r\\n","").replace("\\t",""));
//						}
						list.add(map);
					}


					return list;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
		return list;
	}

	public static void main(String[] args) {
		SysUser sysUser = new SysUser();
		sysUser.setPhone("17778067291");
		sysUser.setIdNo("430524199305192439");

		 getUserReportListInfoByPhoneOrIdNo(sysUser,"1",null);
	}
}
