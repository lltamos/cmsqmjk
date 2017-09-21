package com.quanmin.model.po;

import com.quanmin.model.jpapo.ShopCommodityOrder;
import com.quanmin.model.jpapo.ShopOrder;
import com.quanmin.model.jpapo.ShopRefundRecord;

public class ExportOrderPo {
   private ShopOrder shopOrder;
   private ShopCommodityOrder shopCommodityOrder;
   private ShopRefundRecord shopRefundRecord;

    public ShopOrder getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(ShopOrder shopOrder) {
        this.shopOrder=shopOrder;
    }

    public ShopCommodityOrder getShopCommodityOrder() {
        return shopCommodityOrder;
    }

    public void setShopCommodityOrder(ShopCommodityOrder shopCommodityOrder) {
        this.shopCommodityOrder=shopCommodityOrder;
    }

    public ShopRefundRecord getShopRefundRecord() {
        return shopRefundRecord;
    }

    public void setShopRefundRecord(ShopRefundRecord shopRefundRecord) {
        this.shopRefundRecord=shopRefundRecord;
    }
}
