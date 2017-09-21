package com.quanmin.model.jpapo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yang on 2017/7/24.
 */
@Entity
@Table(name="shop_commodity_order")
public class ShopCommodityOrder {
    private Long id;
    private Long orderId;
    private Long commodityId;
    private Integer type;
    private Integer amount;//数量
    private Integer delStatus;
    private Date createTime;
    private Date updateTime;
    private Double prices;//单价
    private Integer status;// 0 未支付，1已支付，2退款中，3，已退款
    private String commodityName;
    private String colors;
    private String sizeName;//尺寸
    private String modelName;//型号
    private String commodityUrl;
    private String ext1;
    private String ext2;
    private String ext3;

    public String getCommodityUrl() {
        return commodityUrl;
    }

    public void setCommodityUrl(String commodityUrl) {
        this.commodityUrl=commodityUrl;
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

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName=commodityName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status=status;
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


    public Double getPrices() {
        return prices;
    }

    public void setPrices(Double prices) {
        this.prices=prices;
    }

    @Basic
    @Column(name="order_id", nullable=true)
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId=orderId;
    }

    @Basic
    @Column(name="commodity_id", nullable=true)
    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId=commodityId;
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
    @Column(name="amount", nullable=true)
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount=amount;
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

}
