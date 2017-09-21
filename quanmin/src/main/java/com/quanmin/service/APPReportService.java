package com.quanmin.service;

import com.quanmin.model.SysUser;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.util.ResultUtils;

public interface APPReportService {
	/**
	 * 查看报告列表
	 *
	 * @param searchCondition
	 * @return
	 */
	ResultUtils showReportListByUserId(SearchCondition searchCondition);

	/**
	 * 查看单个报告
	 * 
	 * @param searchCondition
	 * @return
	 */
	ResultUtils showReportByUserId(SearchCondition searchCondition, String hraid);

	/**
	 * 查看建议
	 * 
	 * @param user
	 * @param hraid
	 * @return
	 */
	ResultUtils showanalyzeByHraidId(SysUser user, String hraid);

}
