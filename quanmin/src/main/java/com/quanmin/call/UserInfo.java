package com.quanmin.call;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.quanmin.model.SysUser;
import com.quanmin.util.Commons;

/**
 * 获取用户信息
 * 
 * @author heasy
 *
 */
@SuppressWarnings("ALL")
public class UserInfo {

	/**
	 * 根据手机号和身份证号。获取用户信息
	 * 
	 * @param phone
	 * @param cardId
	 * @return
	 */
	public static SysUser getUserInfoByPhoneAndIdNo(String phone, String cardId) {
		SysUser record = new SysUser();
		try {
			String url = Commons.LOGINUSERINFO;
			// 输出文件路径
			// PostMethod postMethod = new PostMethod(url);

			HttpPost httppost = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			if (cardId != null && cardId != "") {
				params.add(new BasicNameValuePair("idCard", cardId));
			} else {
				params.add(new BasicNameValuePair("phone", phone));
			}

			httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = new DefaultHttpClient().execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
				String result = EntityUtils.toString(response.getEntity());
				// // 解析数据
				JSONObject rootjsonBean = JSONObject.parseObject(result);
				Object rescode = rootjsonBean.get("code");
				if (rescode.toString().equals("200")) {
					Object value = rootjsonBean.get("value");

					JSONObject jsonBean = JSONObject.parseObject(value.toString());
					String name = (String) jsonBean.get("name");
					String age = (String) jsonBean.get("age");
					String idCard = (String) jsonBean.get("idCard");
					String weight = (String) jsonBean.get("weight");
					String height = (String) jsonBean.get("height");
					String address = (String) jsonBean.get("address");
					String sex = "1";
					if (jsonBean.get("sex").equals("男")) {
						sex = "0";
					}
					String birthDay = (String) jsonBean.get("birthDay");
					record.setName(name);
					record.setAge(Integer.parseInt(age));
					record.setIdNo(idCard);
					record.setSex(Integer.parseInt(sex));
					record.setBirthday(birthDay);
					record.setWeight(weight);
					record.setHeight(height);
					record.setAddress(address);
					return record;
				} else {
					record.setIdNo(cardId);
					return record;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return record;
		}
		return record;
	}
}
