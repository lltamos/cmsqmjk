package com.quanmin.model.custom;

/**
 * cms资讯数据
 * 
 * @author heasy
 *
 */
public class CMSLableInformation {
	private Integer id;

	private String title;

	private String bodyTitle;

	private String userId;

	private String comment;

	private Integer scannum;

	private Integer collectionsum;

	private String coverUrl;

	private Integer publish;

	private String publishUrl;

	private String publishTime;

	private Integer delStatus;

	private String createTime;

	private String content;

	private Integer collectionStatus;// 收藏状态，0 收藏，1未收藏

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getBodyTitle() {
		return bodyTitle;
	}

	public void setBodyTitle(String bodyTitle) {
		this.bodyTitle = bodyTitle == null ? null : bodyTitle.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

	public Integer getScannum() {
		return scannum;
	}

	public void setScannum(Integer scannum) {
		this.scannum = scannum;
	}

	public Integer getCollectionsum() {
		return collectionsum;
	}

	public void setCollectionsum(Integer collectionsum) {
		this.collectionsum = collectionsum;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl == null ? null : coverUrl.trim();
	}

	public Integer getPublish() {
		return publish;
	}

	public void setPublish(Integer publish) {
		this.publish = publish;
	}

	public String getPublishUrl() {
		return publishUrl;
	}

	public void setPublishUrl(String publishUrl) {
		this.publishUrl = publishUrl == null ? null : publishUrl.trim();
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
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

	public Integer getCollectionStatus() {
		return collectionStatus;
	}

	public void setCollectionStatus(Integer collectionStatus) {
		this.collectionStatus = collectionStatus;
	}

}