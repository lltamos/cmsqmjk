package com.quanmin.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.quanmin.model.BBSComment;
import com.quanmin.model.BBSInformation;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.util.ResultUtils;

public interface APPBBSService {
	/**
	 * 根据条件查看论坛列表
	 * 
	 * @param searchCondition
	 * @return
	 */
	ResultUtils showBBSList(SearchCondition searchCondition);

	/**
	 * 保存论坛信息
	 * 
	 * @param comment
	 * @param information
	 * @return
	 */
	ResultUtils saveBBSInfo(BBSInformation information);

	/**
	 * 根据id查询
	 * 
	 * @param bbsInformationId
	 * @return
	 */
	ResultUtils showBBSInfoById(Integer bbsInformationId);

	/**
	 * 保存评论信息
	 * 
	 * @param comment
	 * @param commentId 
	 * @return
	 */
	ResultUtils saveCommentById(BBSComment comment, Integer commentId);

	/**
	 * 查看回复
	 * 
	 * @param searchCondition
	 * @param bbsInformationId
	 * @return
	 */
	ResultUtils showBBSCommentById(SearchCondition searchCondition, Integer bbsInformationId);

	/**
	 * 保存帖子图片
	 * 
	 * @param response
	 * @param fileName
	 * @return
	 */
	ResultUtils saveBBSImage(HttpServletResponse response, MultipartFile fileName);

}
