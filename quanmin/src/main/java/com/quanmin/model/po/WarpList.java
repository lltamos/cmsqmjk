package com.quanmin.model.po;

import com.quanmin.model.jpapo.ShopCommodityOrder;
import com.quanmin.model.jpapo.ShopOrder;

import java.util.List;

/**
 * WarpList
 *
 * @author llsmp
 * @date 2017/7/28
 */
public class WarpList {
    private ShopOrder shopOrder;
    private List<ShopCommodityOrder> shopCommodityOrders;

    private List<Long> shopCartIds;



    public List<ShopCommodityOrder> getShopCommodityOrders() {
        return shopCommodityOrders;
    }

    public List<Long> getShopCartIds() {
        return shopCartIds;
    }

    public void setShopCartIds(List<Long> shopCartIds) {
        this.shopCartIds=shopCartIds;
    }

    public void setShopCommodityOrders(List<ShopCommodityOrder> shopCommodityOrders) {
        this.shopCommodityOrders = shopCommodityOrders;
    }

    public ShopOrder getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
    }
}
