package com.quanmin.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quanmin.model.Feedback;
import com.quanmin.service.APPFeedbackService;
import com.quanmin.service.APPHelpService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;

/**
 * 意见反馈
 * 
 * @author heasy
 *
 */
@Controller
@RequestMapping(value = "/api/1/")
public class APPHelpController {

	@Autowired
	private APPHelpService helpService;

	/**
	 * 获取帮助信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gethelpinfo")
	@ResponseBody
	public ResultUtils getHelpinfo() {
		ResultUtils result = helpService.getHelpinfo();
		return result;
	}
}
