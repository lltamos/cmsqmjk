package com.quanmin.service;

import com.quanmin.util.ResultUtils;

public interface APPVersionService {

	/**
	 * 获取安卓最新版本
	 * 
	 * @return
	 */
	ResultUtils getversion(String type);

	/**
	 * 获取ios版本信息
	 * 
	 * @param version
	 * @return
	 */
	ResultUtils getIOSVersion(String version);

}
