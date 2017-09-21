package com.quanmin.controller.app;

import com.quanmin.model.BBSComment;
import com.quanmin.model.BBSInformation;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.APPBBSService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 论坛系统
 * 
 * @author heasy
 *
 */
@Controller
@RequestMapping(value = "/api/1/")
public class APPBBSController {

	@Autowired
	private APPBBSService bbsService;

	/**
	 * 论坛列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showbbslist")
	@ResponseBody
	public ResultUtils showBBSList(SearchCondition searchCondition) {
		ResultUtils result = bbsService.showBBSList(searchCondition);

		return result;
	}

	/**
	 * 保存论坛信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/savebbsinfo")
	@ResponseBody
	public ResultUtils saveBBSInfo(BBSInformation information) {
		ResultUtils result = bbsService.saveBBSInfo(information);
		return result;
	}

	/**
	 * 查看帖子详细信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showbbsinfo")
	@ResponseBody
	public ResultUtils showBBSInfoById(Integer bbsInformationId) {
		ResultUtils result = bbsService.showBBSInfoById(bbsInformationId);
		return result;
	}

	/**
	 * 查看回复
	 * 
	 * @param bbsInformationId
	 * @return
	 */
	@RequestMapping(value = "/showbbscomment")
	@ResponseBody
	public ResultUtils showBBSCommentById(SearchCondition searchCondition,Integer bbsInformationId) {
		ResultUtils result = bbsService.showBBSCommentById(searchCondition,bbsInformationId);
		return result;
	}

	/**
	 * 保存评论信息
	 * 
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/savecomment")
	@ResponseBody
	public ResultUtils saveCommentById(BBSComment comment,Integer commentId) {
		ResultUtils result = bbsService.saveCommentById(comment,commentId);
		return result;
	}
	/**
	 * bbs图片上传
	 * @param response
	 * @param fileName
	 * @return
	 */
	@RequestMapping("/savebbsimage")
	@ResponseBody
	public ResultUtils saveBBSImage(HttpServletResponse response,@RequestParam("fileName") MultipartFile fileName) {
		ResultUtils resultUtils = bbsService.saveBBSImage( response, fileName);
		return resultUtils;
	}

}
