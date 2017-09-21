package com.quanmin.model.jpapo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yang on 2017/7/24.
 */
@Entity
@Table(name = "shop_cart")
public class ShopCart {
    private Long id;
    private Long commodityId; //商品id
    private Integer type;//商品类型
    private Integer userId;
    private Integer amount;
    private Integer delStatus;
    private Date createTime;
    private Date updateTime;
    private String ext1;
    private String ext2;
    private String ext3;


    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "commodity_id", nullable = true)
    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "amount", nullable = true)
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "del_status", nullable = true)
    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "ext1", nullable = true, length = 255)
    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    @Basic
    @Column(name = "ext2", nullable = true, length = 255)
    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    @Basic
    @Column(name = "ext3", nullable = true, length = 255)
    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopCart shopCart = (ShopCart) o;

        if (id != shopCart.id) return false;
        if (commodityId != null ? !commodityId.equals(shopCart.commodityId) : shopCart.commodityId != null)
            return false;
        if (type != null ? !type.equals(shopCart.type) : shopCart.type != null) return false;
        if (userId != null ? !userId.equals(shopCart.userId) : shopCart.userId != null) return false;
        if (amount != null ? !amount.equals(shopCart.amount) : shopCart.amount != null) return false;
        if (delStatus != null ? !delStatus.equals(shopCart.delStatus) : shopCart.delStatus != null) return false;
        if (createTime != null ? !createTime.equals(shopCart.createTime) : shopCart.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(shopCart.updateTime) : shopCart.updateTime != null) return false;
        if (ext1 != null ? !ext1.equals(shopCart.ext1) : shopCart.ext1 != null) return false;
        if (ext2 != null ? !ext2.equals(shopCart.ext2) : shopCart.ext2 != null) return false;
        if (ext3 != null ? !ext3.equals(shopCart.ext3) : shopCart.ext3 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (commodityId != null ? commodityId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (delStatus != null ? delStatus.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (ext1 != null ? ext1.hashCode() : 0);
        result = 31 * result + (ext2 != null ? ext2.hashCode() : 0);
        result = 31 * result + (ext3 != null ? ext3.hashCode() : 0);
        return result;
    }
}
