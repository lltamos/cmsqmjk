package com.quanmin.service.impl;

import com.quanmin.dao.mapper.VersioninfoMapper;
import com.quanmin.model.Versioninfo;
import com.quanmin.model.VersioninfoExample;
import com.quanmin.model.VersioninfoExample.Criteria;
import com.quanmin.service.APPVersionService;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APPVersionServiceImpl implements APPVersionService {

	@Autowired
	private VersioninfoMapper versioninfoMapper;

	@Override
	public ResultUtils getversion(String type) {
		ResultUtils result = new ResultUtils();
		VersioninfoExample example = new VersioninfoExample();
		example.setOrderByClause("create_time DESC");
		Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(type);
		List<Versioninfo> list = versioninfoMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			result.setMsg("获取成功");
			result.setResultCode("200");
			result.setSuccess(true);
			result.setValue(list.get(0));
			return result;
		}
		result.setMsg("获取失败");
		result.setResultCode("500");
		result.setSuccess(false);
		result.setValue("");
		return result;
	}

	@Override
	public ResultUtils getIOSVersion(String version) {
		ResultUtils result = new ResultUtils();
		VersioninfoExample example = new VersioninfoExample();
		example.setOrderByClause("create_time DESC");
		Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo("0");
		List<Versioninfo> list = versioninfoMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			result.setMsg("获取成功");
			//如果这个版本号和数据库中相等就返回0，不相等返回1，当提交ios审核时，需要手动将1修改成0，//TODO
			if(list.get(0).getVersionNo().equals(version)){
				result.setResultCode("0");
			}else{
				result.setResultCode("0");
			}
			result.setSuccess(true);
			result.setValue(list.get(0));
			return result;
		}
		result.setMsg("获取失败");
		result.setResultCode("500");
		result.setSuccess(false);
		result.setValue("");
		return result;
	}
}
