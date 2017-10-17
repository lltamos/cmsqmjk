package com.quanmin.service.impl;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.quanmin.util.LoadPropertiesDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.quanmin.call.UserInfo;
import com.quanmin.dao.mapper.BBSCommentMapper;
import com.quanmin.dao.mapper.BBSInformationMapper;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.model.BBSComment;
import com.quanmin.model.BBSCommentExample;
import com.quanmin.model.BBSInformation;
import com.quanmin.model.BBSInformationExample;
import com.quanmin.model.BBSInformationExample.Criteria;
import com.quanmin.model.SysUser;
import com.quanmin.service.APPUserService;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.SaveImageUtil;
import com.quanmin.util.StringUtil;

@SuppressWarnings("ALL")
@Service
public class APPUserServiceImpl implements APPUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private BBSCommentMapper commentMapper;
	@Autowired
	private BBSInformationMapper informationMapper;

	@Override
	public ResultUtils selectByPrimaryKey(String id) {
		ResultUtils result = new ResultUtils();
		SysUser user = sysUserMapper.selectByPrimaryKey(Integer.parseInt(id));

		if (null != user) {
			result.setMsg("查询成功");
			result.setResultCode("200");
			result.setSuccess(true);
			result.setValue(user);
			return result;
		}
		result.setMsg("查询失败");
		result.setResultCode("500");
		result.setSuccess(false);
		return result;
	}

	@Override
	public ResultUtils updateByPrimaryKey(SysUser user) {
		ResultUtils result = new ResultUtils();
		HashMap<String, Object> hs = new HashMap<>();
		// 是否是会员
		String member = "0";
		int key = 0;

		// 如果是修改身份证
		if (user.getIdNo() != null && (!"".equals(user))) {
			// 根据手机号和身份证号获取用户信息
			SysUser record = UserInfo.getUserInfoByPhoneAndIdNo(user.getPhone(), user.getIdNo());
			// 如果通过身份证获取到用户的信息
			if (null != record.getName() && !("".equals(record.getName()))) {
				user.setName(record.getName());
				user.setSex(record.getSex());
				user.setBirthday(record.getBirthday());
				user.setIdNo(record.getIdNo());
				user.setAge(record.getAge());
				member = "1";
			} else {
				{
					user.setName("");
					user.setSex(0);
					user.setBirthday("");
					user.setIdNo(user.getIdNo());
					user.setAge(0);
				}
			}
		}

		key = sysUserMapper.updateByPrimaryKeySelective(user);
		if (key > 0) {
			// 更新帖子信息
			int k = updateCommentAndinformationByUserInfo(user);
			hs.put("member", member);
			hs.put("user", user);
			result.setMsg("更新成功");
			result.setResultCode("200");
			result.setSuccess(true);
			result.setValue(hs);
			return result;
		}
		result.setMsg("更新失败");
		result.setResultCode("500");
		result.setSuccess(false);
		result.setValue("");
		return result;
	}

	/**
	 * 更新帖子信息，主要是发帖人信息，和评论人信息
	 * 
	 * @param user
	 * @return
	 */
	private int updateCommentAndinformationByUserInfo(SysUser user) {
		if (!StringUtil.isEmpty(user.getHeadImageUrl()) || !StringUtil.isEmpty(user.getNickname())) {
			// 根据用户id修改发帖人信息
			BBSInformation bbsInformation = new BBSInformation();
			bbsInformation.setUserName(user.getNickname());
			bbsInformation.setUserHeadUrl(user.getHeadImageUrl());
			BBSInformationExample bbsInformationExample = new BBSInformationExample();
			Criteria bbsInformationCreateCriteria = bbsInformationExample.createCriteria();
			bbsInformationCreateCriteria.andUserIdEqualTo(user.getId());
			informationMapper.updateByExampleSelective(bbsInformation, bbsInformationExample);

			// 修改评论人信息
			BBSCommentExample commentExample1 = new BBSCommentExample();
			com.quanmin.model.BBSCommentExample.Criteria commentCriteria1 = commentExample1.createCriteria();
			commentCriteria1.andUserIdEqualTo(user.getId());
			BBSComment bbsComment1 = new BBSComment();
			if (!StringUtil.isEmpty(user.getNickname())) {
				bbsComment1.setUserName(user.getNickname());
			}
			if (!StringUtil.isEmpty(user.getHeadImageUrl())) {
				bbsComment1.setUserHeadUrl(user.getHeadImageUrl());
			}
			commentMapper.updateByExampleSelective(bbsComment1, commentExample1);

			// 修改被评论人信息
			BBSCommentExample commentExample2 = new BBSCommentExample();
			com.quanmin.model.BBSCommentExample.Criteria commentCriteria2 = commentExample2.createCriteria();
			commentCriteria2.andReplyIdEqualTo(user.getId());
			BBSComment bbsComment2 = new BBSComment();
			if (!StringUtil.isEmpty(user.getNickname())) {
				bbsComment2.setReplyName(user.getNickname());
			}
			if (!StringUtil.isEmpty(user.getHeadImageUrl())) {
				bbsComment2.setReplyHeadUrl(user.getHeadImageUrl());
			}
			commentMapper.updateByExampleSelective(bbsComment2, commentExample2);
		}

		return 0;
	}

	@Override
	public ResultUtils saveImageByUserId(HttpServletResponse response, MultipartFile fileName, String id) {
		String basePath = LoadPropertiesDataUtils.getValue("qm.uploading.url");
		String visit = LoadPropertiesDataUtils.getValue("qm.visit.url");
		ResultUtils result = new ResultUtils();
		HashMap<String, Object> hs = new HashMap<>();

		try {
			String visitUrl = SaveImageUtil.saveImage(response, fileName,basePath,visit);
			hs.put("headUrl", visitUrl);
			SysUser record = new SysUser();
			record.setId(Integer.parseInt(id));
			record.setHeadImageUrl(visitUrl);
			int i = sysUserMapper.updateByPrimaryKeySelective(record);
			updateCommentAndinformationByUserInfo(record);
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
