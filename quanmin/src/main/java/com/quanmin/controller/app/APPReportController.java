package com.quanmin.controller.app;

import com.quanmin.model.SysUser;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.APPReportService;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报告管理
 * @author heasy
 *
 */
@RestController
@RequestMapping(value = "/api/1/")
public class APPReportController {

	@Autowired
	private APPReportService reportService;

	/**
	 * 查看报告列表
	 */
	@RequestMapping(value = "/showreportlist")
	public ResultUtils showReportListByUserId(SearchCondition searchCondition) {
		ResultUtils result = reportService.showReportListByUserId(searchCondition);
		return result;

	}

	/**
	 * 查看报告
	 */
	@RequestMapping(value = "/showreport")
	public ResultUtils showReportByUserId(SearchCondition searchCondition,String hraid) {
		ResultUtils result = reportService.showReportByUserId(searchCondition,hraid);
		return result;

	}
	/**
	 * 查看建议
	 */
	@RequestMapping(value = "/showanalyze")
	public ResultUtils showanalyzeByHraidId(SysUser user,String hraid,String idCard) {
		user.setIdNo(idCard);
		ResultUtils result = reportService.showanalyzeByHraidId(user,hraid);
		return result;

	}


}
