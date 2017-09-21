package com.quanmin.model.custom;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.quanmin.model.BBSComment;
import com.quanmin.util.DateFormatUtil;

public class CommentInfo {
	private Integer id;

	private Integer replyId;
	
	private Integer parentId;

	private String replyName;

	private String replyHeadUrl;

	private Integer userId;

	private String userName;

	private String userHeadUrl;

	private Integer bbsInformationId;

	private Integer type;

	private Integer sort;

	private String comment;

	private String createTime;

	private Date updateTime;

	private String ext1;

	private String ext2;

	private String ext3;

	private List<BBSComment> commentList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName == null ? null : replyName.trim();
	}

	public String getReplyHeadUrl() {
		return replyHeadUrl;
	}

	public void setReplyHeadUrl(String replyHeadUrl) {
		this.replyHeadUrl = replyHeadUrl == null ? null : replyHeadUrl.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserHeadUrl() {
		return userHeadUrl;
	}

	public void setUserHeadUrl(String userHeadUrl) {
		this.userHeadUrl = userHeadUrl == null ? null : userHeadUrl.trim();
	}

	public Integer getBbsInformationId() {
		return bbsInformationId;
	}

	public void setBbsInformationId(Integer bbsInformationId) {
		this.bbsInformationId = bbsInformationId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		try {
			this.createTime = DateFormatUtil.millisecondFormatTime(createTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1 == null ? null : ext1.trim();
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2 == null ? null : ext2.trim();
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3 == null ? null : ext3.trim();
	}

	public List<BBSComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<BBSComment> commentList) {
		this.commentList = commentList;
	}

	

}