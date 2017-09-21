package com.quanmin.model;

import java.util.Date;

public class UserFamily {
    private Integer id;

    private Integer userId;

    private Integer familyId;

    private String appellation;

    private Date createTime;

    private Date updateTime;

    private Integer checkGreen;

    private Integer areThereReport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation == null ? null : appellation.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCheckGreen() {
        return checkGreen;
    }

    public void setCheckGreen(Integer checkGreen) {
        this.checkGreen = checkGreen;
    }

    public Integer getAreThereReport() {
        return areThereReport;
    }

    public void setAreThereReport(Integer areThereReport) {
        this.areThereReport = areThereReport;
    }
}