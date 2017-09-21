package com.quanmin.model.custom;

/**
 * 用户和论坛关联
 * 
 * @author yang
 *
 */
public class BBSInformationAndUser {
	private Integer bbsInformationId;

	private String title;

	private String userId;

	private String createTime;

	private String content;

	private String name;

	private String nickname;

	private String userHeadImageUrl;

	private Integer scannum;

	private Integer commentnum;

	public Integer getBbsInformationId() {
		return bbsInformationId;
	}

	public void setBbsInformationId(Integer bbsInformationId) {
		this.bbsInformationId = bbsInformationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserHeadImageUrl() {
		return userHeadImageUrl;
	}

	public void setUserHeadImageUrl(String userHeadImageUrl) {
		this.userHeadImageUrl = userHeadImageUrl;
	}

	public Integer getScannum() {
		return scannum;
	}

	public void setScannum(Integer scannum) {
		this.scannum = scannum;
	}

	public Integer getCommentnum() {
		return commentnum;
	}

	public void setCommentnum(Integer commentnum) {
		this.commentnum = commentnum;
	}


}
