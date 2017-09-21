package com.quanmin.model;

import java.util.Date;

public class UserHraReport {
    private Integer id;

    private Integer userId;

    private Integer hraUserId;

    private Integer hraId;

    private String hraUserName;

    private String hraReportTime;

    private String hraReportUrl;

    private Date createTime;

    private Date updateTime;

    private String ext1;

    private String ext2;

    private String ext3;

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

    public Integer getHraUserId() {
        return hraUserId;
    }

    public void setHraUserId(Integer hraUserId) {
        this.hraUserId = hraUserId;
    }

    public Integer getHraId() {
        return hraId;
    }

    public void setHraId(Integer hraId) {
        this.hraId = hraId;
    }

    public String getHraUserName() {
        return hraUserName;
    }

    public void setHraUserName(String hraUserName) {
        this.hraUserName = hraUserName == null ? null : hraUserName.trim();
    }

    public String getHraReportTime() {
        return hraReportTime;
    }

    public void setHraReportTime(String hraReportTime) {
        this.hraReportTime = hraReportTime == null ? null : hraReportTime.trim();
    }

    public String getHraReportUrl() {
        return hraReportUrl;
    }

    public void setHraReportUrl(String hraReportUrl) {
        this.hraReportUrl = hraReportUrl == null ? null : hraReportUrl.trim();
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
}