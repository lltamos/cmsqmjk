package com.quanmin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.quanmin.model.SysUser;
import com.quanmin.util.ResultUtils;

public interface APPUserService {

	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 */
	ResultUtils selectByPrimaryKey(String id);

	/**
	 * 根据id更新用户信息
	 * @param user
	 * @return
	 */
	ResultUtils updateByPrimaryKey(SysUser user);

	/**
	 * 上传头像
	 * @param request
	 * @param response
	 * @param fileName
	 * @param id
	 * @return
	 */
	ResultUtils saveImageByUserId( HttpServletResponse response, MultipartFile fileName,String id);
}
