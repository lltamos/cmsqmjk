package com.quanmin.service.impl;

import com.quanmin.dao.jpa.ShopCommidityOrderDao;
import com.quanmin.dao.jpa.ShopCommodityDao;
import com.quanmin.dao.jpa.ShopOrderDao;
import com.quanmin.dao.jpa.ShopRefundRecordDao;
import com.quanmin.model.jpapo.ShopCommodity;
import com.quanmin.model.jpapo.ShopCommodityOrder;
import com.quanmin.model.jpapo.ShopOrder;
import com.quanmin.model.jpapo.ShopRefundRecord;
import com.quanmin.service.IReceiptScanTaskService;
import com.quanmin.util.SortUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReceiptScanServiceImpl implements IReceiptScanTaskService {
    @Resource
    private ShopOrderDao shopOrderDao;
    @Resource
    private ShopRefundRecordDao shopRefundRecordDao;

    @Resource
    private ShopCommidityOrderDao shopCommidityOrderDao;

    @Resource
    private ShopCommodityDao shopCommodityDao;

    @Override
    public void sweep() {
        sweepNoPay();
        sweepNoConfirm();
        sweepNoExpress();
    }

    //扫描下单两天未付款的订单
    private void sweepNoPay() {
        List<ShopOrder> shopOrders=shopOrderDao.findByOrderStatusAndTypeEqualsAndDelStatus(1, 0, 0);
        for (ShopOrder s : shopOrders) {
            Long initializeTime=s.getCreateTime().getTime();
            Long computeTime=initializeTime + (1000 * 60 * 60 * 24 * 2);
            Long nowTime=System.currentTimeMillis();

            if (nowTime > computeTime) {
                List<ShopCommodityOrder> commodityOrders=shopCommidityOrderDao.findAllByOrderIdEquals(s.getId());
                for (ShopCommodityOrder commodityOrder : commodityOrders) {
                    ShopCommodity commodity=shopCommodityDao.findOne(commodityOrder.getCommodityId());
                    commodity.setTotalNum(commodity.getTotalNum() + commodityOrder.getAmount());
                }
                s.setType(1);
                s.setIsRefound(1);
                s.setEndTime(new Date());
            }
        }
    }

    //扫描发货后到时间未确认收货的订单
    private void sweepNoConfirm() {
        List<ShopOrder> shopOrders=shopOrderDao.findByOrderStatusAndTypeEqualsAndDelStatus(3, 0, 0);
        for (ShopOrder s : shopOrders) {
            Long initializeTime=s.getReceiptTime().getTime();
            Long nowTime=System.currentTimeMillis();
            if (nowTime > initializeTime) {
                s.setOrderStatus(4);
                s.setType(2);
                s.setEndTime(new Date());
            }
        }
    }

    //扫描同意退货，未发快递的订单
    private void sweepNoExpress() {
        List<ShopRefundRecord> recordList=shopRefundRecordDao.findByBackTypeIsAndStatusIsAndAuditStatusIs(1, 1, 2, SortUtils.DESCCreateTime());
        for (ShopRefundRecord record : recordList) {
            if (record.getBackSendTime() != null || record.getBackExpressNum() != null)
                continue;

            Long initializeTime=record.getAuditTime().getTime();
            Long computeTime=initializeTime + (1000 * 60 * 60 * 24 * 7);

            long nowTime=System.currentTimeMillis();
            if (nowTime > computeTime) {
                ShopCommodityOrder commodityOrder=shopCommidityOrderDao.findOne(record.getShopCommodityOrderId());

                commodityOrder.setStatus(1);
                record.setAuditStatus(1);
                record.setStatus(2);

            }
        }
    }


}
