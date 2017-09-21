package com.quanmin.service;

import com.quanmin.model.Label;
import com.quanmin.model.custom.LableInformation;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.util.ResultUtils;

public interface CMSContentService {

	/**
	 * 保存消息
	 * 
	 * @param labelInformation
	 * @param type
	 * @return
	 */
	ResultUtils saveContent(LableInformation labelInformation, String type, String popular,String informationId);

	/**
	 * 查看消息列表
	 * 
	 * @param condition
	 * @return
	 */
	ResultUtils selectInfoListByCondition(SearchCondition condition);

	/**
	 * 删除消息
	 * 
	 * @param condition
	 * @return
	 */
	ResultUtils deleteInfoByInfoId(SearchCondition condition);

	/**
	 * 重新生成咨询html页面
	 */
	void generateHtml();

	/**
	 * 咨询标签列表
	 * 
	 * @return
	 */
	ResultUtils lableList();

	/**
	 * 添加标签
	 * 
	 * @param label
	 * @return
	 */
	ResultUtils saveLabel(Label label);

	/**
	 * 保存图片标签
	 * 
	 * @param scName
	 * @return
	 */
	ResultUtils scSaveLabel(String scName);

	/**
	 * 图片标签列表
	 * 
	 * @return
	 */
	ResultUtils sclabelList();

	/**
	 * 图片列表
	 * 
	 * @param condition
	 * @return
	 */
	ResultUtils scImagelList(SearchCondition condition);

	/**
	 * 根据id查看咨询
	 * @param condition
	 * @return
	 */
	ResultUtils selectInfoByInfoId(SearchCondition condition);
}
