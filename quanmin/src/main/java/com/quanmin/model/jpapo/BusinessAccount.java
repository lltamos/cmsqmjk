package com.quanmin.model.jpapo;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by yang on 2017/7/24.
 */
@Entity
@Table(name="business_account")
public class BusinessAccount {
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue
    private Long id;
    private Integer type; //1入账，2出账
    private Long orderId;
    private String orderNum;
    private String refundNum;
    private String fromType;//来源
    private Double money;
    private Date bookedTime;
    private Date createTime;
    private Date updateTime;
    private Integer userId;
    private String userNickName;
    private String userPhone;

    private String ext1;
    private String ext2;
    private String ext3;

    public Long getId() {
        return id;
    }

    public String getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(String refundNum) {
        this.refundNum=refundNum;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type=type;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId=orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum=orderNum;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType=fromType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money=money;
    }

    public Date getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(Date bookedTime) {
        this.bookedTime=bookedTime;
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

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName=userNickName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone=userPhone;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1=ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2=ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3=ext3;
    }
}
