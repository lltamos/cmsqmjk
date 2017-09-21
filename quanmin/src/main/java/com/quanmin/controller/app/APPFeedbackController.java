package com.quanmin.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quanmin.model.Feedback;
import com.quanmin.service.APPFeedbackService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;

/**
 *意见反馈
 * @author heasy
 *
 */
@Controller
@RequestMapping(value = "/api/1/")
public class APPFeedbackController  {
	
	@Autowired
	private APPFeedbackService feedbackService;

	/**
	 * 根据用户id查看消息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/feedback")
	@ResponseBody
	public ResultUtils saveFeedback(Feedback feedback) {
		ResultUtils result = feedbackService.saveFeedback(feedback);
		return result;
	}
}
