package com.quanmin.service.impl;

import java.lang.reflect.InvocationHandler;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.quanmin.util.LoadPropertiesDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quanmin.dao.mapper.BBSCommentMapper;
import com.quanmin.dao.mapper.BBSInformationMapper;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.model.BBSComment;
import com.quanmin.model.BBSCommentExample;
import com.quanmin.model.BBSCommentExample.Criteria;
import com.quanmin.model.BBSInformation;
import com.quanmin.model.SysUser;
import com.quanmin.model.custom.BBSInformationAndUser;
import com.quanmin.model.custom.CommentInfo;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.APPBBSService;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.SaveImageUtil;

@SuppressWarnings("ALL")
@Service
public class APPBBSServiceImpl implements APPBBSService {


	@Autowired
	private BBSInformationMapper bbsInformationMapper;

	@Autowired
	private BBSCommentMapper bbsCommentMapper;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public ResultUtils showBBSList(SearchCondition searchCondition) {
		ResultUtils result = new ResultUtils();
		PageHelper.startPage(searchCondition.getPage(), searchCondition.getSize());
		List<BBSInformationAndUser> list = bbsInformationMapper.showBBSList(searchCondition);
		if (null != list && list.size() > 0) {
			result.setMsg("查询成功");
			result.setResultCode("200");
			result.setSuccess(true);
			result.setCount(((Page) list).getPages());
			result.setValue(list);
			return result;
		}
		result.setMsg("查询失败");
		result.setResultCode("500");
		result.setSuccess(false);
		result.setValue("");
		return result;
	}

	@Override
	public ResultUtils saveBBSInfo(BBSInformation information) {
		ResultUtils result = new ResultUtils();
		SysUser user = sysUserMapper.selectByPrimaryKey(information.getUserId());
		information.setUserName(user.getNickname());
		information.setUserHeadUrl(user.getHeadImageUrl());
		int i = bbsInformationMapper.insertSelective(information);
		if (i > 0) {
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

	@Override
	public ResultUtils showBBSInfoById(Integer bbsInformationId) {
		ResultUtils result = new ResultUtils();
		BBSInformation bbsInformation = bbsInformationMapper.selectByPrimaryKey(bbsInformationId);
		// 更新浏览册次数
		BBSInformation record = new BBSInformation();
		record.setId(bbsInformationId);
		record.setScannum(bbsInformation.getScannum() + 1);
		bbsInformationMapper.updateByPrimaryKeySelective(record);

		if (null != bbsInformation) {
			result.setMsg("查询成功");
			result.setResultCode("200");
			result.setSuccess(true);
			result.setValue(bbsInformation);
			return result;
		}
		result.setMsg("查询失败");
		result.setResultCode("500");
		result.setSuccess(false);
		result.setValue("");
		return result;
	}

	@Override
	public ResultUtils saveCommentById(BBSComment comment, Integer commentId) {
		ResultUtils result = new ResultUtils();
		SysUser replyuser = sysUserMapper.selectByPrimaryKey(comment.getReplyId());
		comment.setReplyHeadUrl(replyuser.getHeadImageUrl());
		comment.setReplyName(replyuser.getNickname());
		SysUser user = sysUserMapper.selectByPrimaryKey(comment.getUserId());
		comment.setUserHeadUrl(user.getHeadImageUrl());
		comment.setUserName(user.getNickname());
		if (null != commentId&&comment.getType()==1) {
			comment.setParentId(commentId);
			// comment.setParentId(118);
			Integer sort = bbsCommentMapper.selectByInformationIdAndParentIdAndType(comment);
			comment.setSort(null==sort?1:sort + 1);
		}else if(comment.getType()==0) {
			comment.setParentId(0);
		}
		int i = bbsCommentMapper.insertSelective(comment);
		if (i > 0) {
			BBSInformation record = new BBSInformation();
			record.setId(comment.getBbsInformationId());
			BBSInformation bbsInformation = bbsInformationMapper.selectByPrimaryKey(comment.getBbsInformationId());
			record.setCommentnum(bbsInformation.getCommentnum() + 1);
			bbsInformationMapper.updateByPrimaryKeySelective(record);
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

	@Override
	public ResultUtils showBBSCommentById(SearchCondition searchCondition, Integer bbsInformationId) {
		ResultUtils result = new ResultUtils();
		PageHelper.startPage(searchCondition.getPage(), searchCondition.getSize());
		// 查看评论信息
		BBSCommentExample example = new BBSCommentExample();
		example.setOrderByClause(" create_time desc");
		Criteria criteria = example.createCriteria();
		criteria.andBbsInformationIdEqualTo(bbsInformationId);
		List<CommentInfo> list = bbsCommentMapper.selectByInformationId(bbsInformationId);

		if (null != list && list.size() > 0) {
			result.setMsg("查询成功");
			result.setResultCode("200");
			result.setSuccess(true);
			result.setValue(list);
			result.setCount(((Page) list).getPages());
			return result;
		}
		result.setMsg("查询失败");
		result.setResultCode("500");
		result.setSuccess(false);
		result.setValue("");
		return result;
	}

	@Override
	public ResultUtils saveBBSImage(HttpServletResponse response, MultipartFile fileName) {
		String basePath = LoadPropertiesDataUtils.getValue("qm.uploading.url");
		String visit = LoadPropertiesDataUtils.getValue("qm.visit.url");
		ResultUtils result = new ResultUtils();
		HashMap<String, Object> hs = new HashMap<>();
		try {
			String visitUrl = SaveImageUtil.saveImage(response, fileName,basePath,visit);
			hs.put("url", visitUrl);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("上传失败");
			result.setResultCode("500");
			result.setSuccess(false);
			return result;
		}
		result.setMsg("上传成功");
		result.setResultCode("200");
		result.setSuccess(true);
		result.setValue(hs);
		return result;
	}

}
