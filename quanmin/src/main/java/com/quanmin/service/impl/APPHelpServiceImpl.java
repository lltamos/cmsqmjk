package com.quanmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanmin.dao.mapper.HelpinfoMapper;
import com.quanmin.model.Helpinfo;
import com.quanmin.model.HelpinfoExample;
import com.quanmin.service.APPHelpService;
import com.quanmin.util.ResultUtils;

@Service
public class APPHelpServiceImpl implements APPHelpService {

	@Autowired
	private HelpinfoMapper helpinfoMapper;

	@Override
	public ResultUtils getHelpinfo() {
		ResultUtils result = new ResultUtils();
		HelpinfoExample example = new HelpinfoExample();
		example.setOrderByClause("create_time DESC");
		List<Helpinfo> list = helpinfoMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			result.setMsg("获取成功");
			result.setResultCode("200");
			result.setSuccess(true);
			result.setValue(list);
			return result;
		}
		result.setMsg("获取失败");
		result.setResultCode("500");
		result.setSuccess(false);
		result.setValue("");
		return result;
	}

}
