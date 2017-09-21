package com.quanmin.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanmin.dao.mapper.FeedbackMapper;
import com.quanmin.model.Feedback;
import com.quanmin.service.APPFeedbackService;
import com.quanmin.util.ResultUtils;

@Service
public class APPFeedbackServiceImpl implements APPFeedbackService{

	@Autowired
	private FeedbackMapper feedbackMapper;

	@Override
	public ResultUtils saveFeedback(Feedback feedback) {
		ResultUtils result = new ResultUtils();
feedback.setCreateTime(new Date());
		int insertSelective = feedbackMapper.insertSelective(feedback);
		
		if (insertSelective > 0) {
			
			result.setMsg("添加成功");
			result.setResultCode("200");
			result.setSuccess(true);
			result.setValue("");
			return result;
		}
		result.setMsg("添加失败");
		result.setResultCode("500");
		result.setSuccess(false);
		result.setValue("");
		return result;
		
	}

}
