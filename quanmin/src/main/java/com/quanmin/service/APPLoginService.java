package com.quanmin.service;

import com.quanmin.model.SysUser;
import com.quanmin.util.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface APPLoginService {

	/**
	 * 用户验证
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	Integer getCodeLogin(String phone, int code);

	/**
	 * 用户登录
	 * 
	 * @param phone
	 * @param code
	 * @param request 
	 * @return
	 */
	ResultUtils userLogin(String phone, String code,String registrationId, HttpServletRequest request);

	/**
	 * 退出
	 * @param user
	 * @param token
	 * @return
	 */
	ResultUtils logout(SysUser user, String token);

	/**
	 * 校验身份证信息
	 */
	ResultUtils checkIdCardByUserIdAndIdCard(SysUser user);

	/**
	 * 校验身份证号
	 * @param idCard
	 * @return
	 */
	ResultUtils validateidcard(String idCard);

    ResultUtils generateCache();

	 ResultUtils touristsLogin(String phone,  HttpServletRequest request,HttpServletResponse response);


	ResultUtils updatenickname();
}
