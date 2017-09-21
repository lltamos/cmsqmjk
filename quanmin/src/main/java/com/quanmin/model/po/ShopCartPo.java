package com.quanmin.model.po;

import com.quanmin.model.jpapo.ShopCommodity;

public class ShopCartPo extends ShopCommodity{

    private  Long shopCartId;
    private Integer amount;


    public Long getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(Long shopCartId) {
        this.shopCartId=shopCartId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount=amount;
    }
}
