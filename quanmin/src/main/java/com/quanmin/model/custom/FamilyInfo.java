package com.quanmin.model.custom;

import java.util.Date;

/**
 * 家庭信息
 * @author heasy
 *
 */

public class     FamilyInfo {

	private String name;

	private Integer age;

	private String phone;
	private String idNo;

	private String checkGreen;

	private String areThereReport;

	private String area;

	private Date createTime;

	private Date updateTime;

	private Integer userId;

	private Integer familyId;

	private String appellation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name=name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age=age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone=phone;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo=idNo;
	}

	public String getCheckGreen() {
		return checkGreen;
	}

	public void setCheckGreen(String checkGreen) {
		this.checkGreen=checkGreen;
	}

	public String getAreThereReport() {
		return areThereReport;
	}

	public void setAreThereReport(String areThereReport) {
		this.areThereReport=areThereReport;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area=area;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime=updateTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId=familyId;
	}

	public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation=appellation;
	}
}
