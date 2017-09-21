package com.quanmin.controller.swapp;

import com.quanmin.service.APPVersionService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 版本管理
 * 
 * @author heasy
 *
 */
@Controller

public class APPTestVersionController {

	@Autowired
	private APPVersionService versionService;

	/**
	 * 获取安卓最新版本
	 * 
	 * @return
	 */
	@ApiOperation("用户详情")
	@ApiImplicitParam(name = "type", value = "type", required = true, dataType = "String")
	@GetMapping(value = "/getversion")
	@ResponseBody
	public String getversion(String type) {
		ResultUtils result = versionService.getversion(type);
		return JsonUtils.objectToJson(result);
	}

	/**
	 * 获取IOS最新版本
	 *
	 * @return
	 */
	@RequestMapping(value = "/getisoversion")
	@ResponseBody
	public String getIOSVersion( HttpServletRequest request,
			HttpServletResponse httpServletResponse) {

		// 用户version
		String version = request.getParameter("version");

		ResultUtils result = versionService.getIOSVersion(version);
		return JsonUtils.objectToJson(result);
	}
}
