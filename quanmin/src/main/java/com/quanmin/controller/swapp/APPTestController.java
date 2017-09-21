package com.quanmin.controller.swapp;

import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 版本管理
 * 
 * @author heasy
 *
 */
@Controller
@Api(value = "SayController|一个用来测试swagger注解的控制器")
@RequestMapping(value = "/app_test")
public class APPTestController {


	/**
	 * 获取安卓最新版本
	 * 
	 * @return
	 */

	@GetMapping(value = "/one")
	@ResponseBody
	public String one() {
		Logger logger = Logger.getLogger(APPTestController.class);
		logger.info("请求成功");
		logger.warn(new Date());
		logger.info("");
		logger.debug("");
		logger.warn("");
		logger.error("");
		return "OK";
	}

	@GetMapping(value = "/two")
	@ResponseBody
	public String two() {
		Logger logger = Logger.getLogger(APPTestController.class);
		logger.info("请求成功");
		logger.warn(new Date());
		return "OK";
	}
}
