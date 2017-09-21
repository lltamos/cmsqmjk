package com.quanmin.controller.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.quanmin.model.SysUser;
import com.quanmin.service.APPUserService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;

/**
 * 用户信息
 * 
 * @author quanmin
 *
 */
@Controller
@RequestMapping(value = "/api/1/")
public class APPUserController {

	@Autowired
	private APPUserService userService;

	/**
	 * 根据id查询用户信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showpersoninfo")
	@ResponseBody
	public ResultUtils selectByPrimaryKey(String id) {
		ResultUtils result = userService.selectByPrimaryKey(id);
		return result;
	}

	/**
	 * 根据id查询用户信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/saveoreditpersoninfo")
	@ResponseBody
	public ResultUtils updateByPrimaryKey(SysUser user) {
		ResultUtils result = userService.updateByPrimaryKey(user);
		return result;
	}

	/**
	 * 图片上传
	 * 
	 * @param response
	 * @param fileName
	 * @param id
	 * @return
	 */
	
	@RequestMapping("/saveimage")
	@ResponseBody
	public ResultUtils saveImage(HttpServletResponse response, @RequestParam("fileName") MultipartFile fileName, String id) {
		ResultUtils resultUtils = userService.saveImageByUserId(response, fileName, id);
		return resultUtils;
	}

}
