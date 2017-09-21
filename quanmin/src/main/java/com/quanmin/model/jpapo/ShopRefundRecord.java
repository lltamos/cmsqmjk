package com.quanmin.model.jpapo;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="shop_refundRecord")
public class ShopRefundRecord {
    @Id
    @Column(name="id", nullable=false)
    @GeneratedValue
    private Long id;
    private String refundNum;
    private Long shopOrderId;
    private String shopOrderNum;
    private Long shopCommodityOrderId;
    private Long commodityId;
    private String commodityName;
    private String colors;
    private String sizeName;//尺寸
    private String modelName;//型号
    private Double prices;//单价
    private String receiveName;
    private String receivePhone;
    private Integer userId;
    private String reasons;//说明
    private Double backMoney;//退款金额
    private Integer backType;//退款类型 0退款，1退货
    private Integer status;// 0 申请退款 1，退款中，2，已完成
    private Integer auditStatus;// 0 未审核 1，拒绝  2,同意，
    private Date auditTime;// 审核时间
    private String commodityUrl;
    private Long backId;//退款原因id
    private String backName;//退款原因
    private Date createTime;
    private Date payTime;
    private Integer amount;
    private Date buyTime;//订单生成时间
    private Date sendTime;//订单发货时间
    private Date receiptTime;//收货时间
    private Date endTime;//完成时间
    private String receiveAddress;
    private Date updateTime;
    private String userNickName;
    private String userPhone;
    private String payOutNum;
    private Long backExpressDictId;
    private String backExpressName;//退货快递公司
    private String backExpressNum;//退货快递单号
    private Date backSendTime;//退货时间
    private String fromType;//来源
    @Transient
    private String backSendTimeStr;

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

    public String getBackSendTimeStr() {
        return backSendTimeStr;
    }

    public void setBackSendTimeStr(String backSendTimeStr) {
        this.backSendTimeStr=backSendTimeStr;
    }

    public Date getBackSendTime() {
        if (backSendTime!=null){
            this.backSendTimeStr=backSendTime.getTime() + "";
        }
        return backSendTime;
    }

    public void setBackSendTime(Date backSendTime) {
        this.backSendTime=backSendTime;
        if (backSendTime!=null){
            this.backSendTimeStr=backSendTime.getTime() + "";
        }
    }

    public String getCommodityUrl() {
        return commodityUrl;
    }

    public void setCommodityUrl(String commodityUrl) {
        this.commodityUrl=commodityUrl;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime=auditTime;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime=receiptTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime=endTime;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors=colors;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName=sizeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName=modelName;
    }

    public Double getPrices() {
        return prices;
    }

    public void setPrices(Double prices) {
        this.prices=prices;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime=sendTime;
    }

    public Long getBackExpressDictId() {
        return backExpressDictId;
    }

    public void setBackExpressDictId(Long backExpressDictId) {
        this.backExpressDictId=backExpressDictId;
    }

    public String getBackExpressName() {
        return backExpressName;
    }

    public void setBackExpressName(String backExpressName) {
        this.backExpressName=backExpressName;
    }

    public String getBackExpressNum() {
        return backExpressNum;
    }

    public void setBackExpressNum(String backExpressNum) {
        this.backExpressNum=backExpressNum;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime=buyTime;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress=receiveAddress;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime=payTime;
    }


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount=amount;
    }

    public String getPayOutNum() {
        return payOutNum;
    }

    public void setPayOutNum(String payOutNum) {
        this.payOutNum=payOutNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(String refundNum) {
        this.refundNum=refundNum;
    }

    public Long getShopOrderId() {
        return shopOrderId;
    }

    public void setShopOrderId(Long shopOrderId) {
        this.shopOrderId=shopOrderId;
    }

    public String getShopOrderNum() {
        return shopOrderNum;
    }

    public void setShopOrderNum(String shopOrderNum) {
        this.shopOrderNum=shopOrderNum;
    }

    public Long getShopCommodityOrderId() {
        return shopCommodityOrderId;
    }

    public void setShopCommodityOrderId(Long shopCommodityOrderId) {
        this.shopCommodityOrderId=shopCommodityOrderId;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId=commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName=commodityName;
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

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName=userNickName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId=userId;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons=reasons;
    }

    public Double getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(Double backMoney) {
        this.backMoney=backMoney;
    }

    public Integer getBackType() {
        return backType;
    }

    public void setBackType(Integer backType) {
        this.backType=backType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status=status;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus=auditStatus;
    }

    public Long getBackId() {
        return backId;
    }

    public void setBackId(Long backId) {
        this.backId=backId;
    }

    public String getBackName() {
        return backName;
    }

    public void setBackName(String backName) {
        this.backName=backName;
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
}
