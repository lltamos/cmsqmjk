package com.quanmin.model.jpapo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yang on 2017/7/24.
 */
@Entity
@Table(name="shop_order")
public class ShopOrder {
    private Long id;
    private String fromType;
    private Long receiptAddressId;
    private String orderNum;//订单号
    private Integer type;// 0正常，1取消订单，2订单完成
    private Integer orderStatus;//订单状态 1待付款、2待发货、3待收货、4已收货
    private Integer payStatus; //支付状态，0未支付，1已支付
    private Double totalMoney;
    private Integer totalCount;//订单商品数量
    private Date payTime;//付款时间
    private Integer payType;//o 支付宝，1 微信
    private Integer expressCompanyId;//快递公司id
    private String expressCompanyName;//快递公司名
    private String expressCompanyNameSimple;//快递公司名简称
    private String expressNum;//快递单号
    private Integer expressMoney;// 快递费用
    private Date expressSendTime;//发货时间
    private String expressSendName;//发货人
    private Date receiptTime;//系统自动收货时间
    private Date endTime;//完成时间
    private Integer remindStatus;//0 未提醒，1 已提醒
    private Integer delStatus;//0 未删除，1 已删除
    private Date createTime;
    private Date updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private Integer userId;
    private String userPhone;
    private String message;
    private Integer isDelay;//1延迟 0未延迟
    private String payOrderNum;  //淘宝交易号
    private String receiveName;
    private String receivePhone;
    private String receiveAddress;
    private String userNickName;
    private Integer isCallBack;//是否走回调0 未回调 1 以回调
    private Integer isRefound;//是否全部申请退款 0 未退款 1全退


    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime=endTime;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone=userPhone;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType=fromType;
    }

    public Integer getIsCallBack() {
        return isCallBack;
    }

    public void setIsCallBack(Integer isCallBack) {
        this.isCallBack=isCallBack;
    }

    public Integer getIsRefound() {
        return isRefound;
    }

    public void setIsRefound(Integer isRefound) {
        this.isRefound=isRefound;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress=receiveAddress;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName=userNickName;
    }

    @Id
    @GeneratedValue
    @Column(name="id", nullable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId=userId;
    }

    @Basic
    @Column(name="receipt_address_id", nullable=true)
    public Long getReceiptAddressId() {
        return receiptAddressId;
    }

    public void setReceiptAddressId(Long receiptAddressId) {
        this.receiptAddressId=receiptAddressId;
    }

    @Basic
    @Column(name="order_num", nullable=true)
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum=orderNum;
    }

    @Basic
    @Column(name="type", nullable=true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type=type;
    }

    @Basic
    @Column(name="order_status", nullable=true)
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus=orderStatus;
    }

    @Basic
    @Column(name="message", nullable=true, length=255)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message=message;
    }

    @Basic
    @Column(name="total_money", nullable=true, length=255)
    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney=totalMoney;
    }

    @Basic
    @Column(name="total_count", nullable=true, length=255)
    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount=totalCount;
    }

    @Basic
    @Column(name="pay_status", nullable=true)
    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus=payStatus;
    }

    @Basic
    @Column(name="pay_time", nullable=true)
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime=payTime;
    }

    @Basic
    @Column(name="pay_type", nullable=true, length=255)
    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType=payType;
    }


    @Basic
    @Column(name="express_company_id", nullable=true)
    public Integer getExpressCompanyId() {
        return expressCompanyId;
    }

    public void setExpressCompanyId(Integer expressCompanyId) {
        this.expressCompanyId=expressCompanyId;
    }

    @Basic
    @Column(name="express_company_name", nullable=true, length=255)
    public String getExpressCompanyName() {
        return expressCompanyName;
    }

    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName=expressCompanyName;
    }

    @Basic
    @Column(name="express_num", nullable=true)
    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum=expressNum;
    }

    @Basic
    @Column(name="receipt_time", nullable=true)
    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime=receiptTime;
    }


    @Basic
    @Column(name="del_status", nullable=true)
    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus=delStatus;
    }

    @Basic
    @Column(name="create_time", nullable=true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime=createTime;
    }

    @Basic
    @Column(name="update_time", nullable=true)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime=updateTime;
    }

    @Basic
    @Column(name="ext1", nullable=true, length=255)
    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1=ext1;
    }

    @Basic
    @Column(name="ext2", nullable=true, length=255)
    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2=ext2;
    }

    @Basic
    @Column(name="ext3", nullable=true, length=255)
    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3=ext3;
    }


    public Integer getExpressMoney() {
        return expressMoney;
    }

    public void setExpressMoney(Integer expressMoney) {
        this.expressMoney=expressMoney;
    }

    public Integer getIsDelay() {
        return isDelay;
    }

    public void setIsDelay(Integer isDelay) {
        this.isDelay=isDelay;
    }

    public Integer getRemindStatus() {
        return remindStatus;
    }

    public void setRemindStatus(Integer remindStatus) {
        this.remindStatus=remindStatus;
    }

    public String getPayOrderNum() {
        return payOrderNum;
    }

    public void setPayOrderNum(String payOrderNum) {
        this.payOrderNum=payOrderNum;
    }

    public String getExpressCompanyNameSimple() {
        return expressCompanyNameSimple;
    }

    public void setExpressCompanyNameSimple(String expressCompanyNameSimple) {
        this.expressCompanyNameSimple=expressCompanyNameSimple;
    }

    public Date getExpressSendTime() {
        return expressSendTime;
    }

    public void setExpressSendTime(Date expressSendTime) {
        this.expressSendTime=expressSendTime;
    }

    public String getExpressSendName() {
        return expressSendName;
    }

    public void setExpressSendName(String expressSendName) {
        this.expressSendName=expressSendName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName=receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone=receivePhone;
    }
}
