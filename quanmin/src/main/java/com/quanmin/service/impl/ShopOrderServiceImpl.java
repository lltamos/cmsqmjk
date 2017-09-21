package com.quanmin.service.impl;

import com.quanmin.dao.jpa.*;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.model.SysUser;
import com.quanmin.model.custom.ParamValue;
import com.quanmin.model.jpapo.*;
import com.quanmin.model.po.ShopCartPo;
import com.quanmin.model.po.WarpList;
import com.quanmin.service.IShopOrderService;
import com.quanmin.util.Commons;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.SerialNumberUtils;
import com.quanmin.util.SortUtils;
import com.quanmin.util.alipayutil.OrderInfoUtil2_0;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.*;

/**
 * ShopOrderServiceImpl
 *
 * @author llsmp
 * @date 2017 /7/25
 */
@Service
@Transactional
public class ShopOrderServiceImpl implements IShopOrderService {


    @Resource
    private ShopOrderDao shopOrderDao;
    @Resource
    private ShopReceiptAddressDao shopReceiptAddressDao;
    @Resource
    private ShopDictDao dictDao;
    @Resource
    private ShopCommodityDao shopCommodityDao;
    @Resource
    private ShopCommidityOrderDao commodityOrderDao;
    @Resource
    private SerialNumberUtils numberUtils;
    @Resource
    private ShopCommidityOrderDao commidityOrderDao;
    @Resource
    private ShopCartDao shopCartDao;
    @Resource
    private SysUserMapper userMapper;
    @Resource
    private ShopRefundRecordDao shopRefundRecordDao;


    /**
     * @param userId      userId
     * @param fromType    0购物车进入，1 直接购买
     * @param cartOrProId id
     */
    @Override
    public ResultUtils affirmOrder(Integer userId, Integer fromType, Long[] cartOrProId, Integer num) {

        if (userId == null || fromType == null || cartOrProId == null || cartOrProId.length == 0) {
            return ResultUtils.returnFail();
        }
        Map<String, Object> value=new HashMap<>();
        List<ShopReceiptAddress> addressList=shopReceiptAddressDao.findByUserIdEqualsAndTypeEquals(userId, 0);
        List<ShopReceiptAddress> countAdress=shopReceiptAddressDao.findByUserIdEquals(userId);


        if (addressList != null && addressList.size() > 0) {
            ShopReceiptAddress address=addressList.get(0);
            value.put("address", address);
        } else {
            value.put("address", null);
        }

        value.put("countAdress", countAdress.size());

        List<ShopDict> shopDicts=dictDao.findByTypeEqualsAndDelStatusEquals(5, 0);
        value.put("deliveryFun", shopDicts);
        List<Object> dataList=new ArrayList<>();
        if (fromType == 1) {
            ShopCommodity one=shopCommodityDao.findOne(cartOrProId[0]);
            dataList.add(one);
            value.put("produceList", dataList);
            value.put("num", num);
            return ResultUtils.returnSucess(value);
        }


        double totalPrice=0;
        if (fromType == 0) {
            List<Long> longList=Arrays.asList(cartOrProId);
            List<ShopCart> shopCarts=shopCartDao.findAll(longList);
            for (ShopCart shopCart : shopCarts) {
                ShopCommodity one=shopCommodityDao.findOne(shopCart.getCommodityId());
                ShopCartPo shopCartPo=new ShopCartPo();
                BeanUtils.copyProperties(one, shopCartPo);
                shopCartPo.setShopCartId(shopCart.getId());
                shopCartPo.setAmount(shopCart.getAmount());
                totalPrice+=shopCart.getAmount() * one.getPrice();
                dataList.add(shopCartPo);
            }
            value.put("produceList", dataList);
            value.put("totalPrice", totalPrice);
            return ResultUtils.returnSucess(value);
        }
        return ResultUtils.returnFail(Commons.DATA_EXCPTION_STR);
    }

    @Override
    @Transactional
    public ResultUtils productionOrder(WarpList shopCommodityOrders) {

        if (shopCommodityOrders == null || shopCommodityOrders.getShopCommodityOrders().size() <= 0)
            return ResultUtils.returnException();

        ShopOrder shopOrder=shopCommodityOrders.getShopOrder();
        if (shopOrder == null || shopOrder.getUserId() == null) return ResultUtils.returnException();

        if (shopOrder.getReceiptAddressId() == null) return ResultUtils.returnException();

        if (shopCommodityOrders.getShopCartIds() != null && shopCommodityOrders.getShopCartIds().size() > 0) {
            for (Long ids : shopCommodityOrders.getShopCartIds()) {
                try {
                    shopCartDao.delete(ids);
                } catch (Exception e) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResultUtils.returnFail("亲，您的订单已经提交成功" + "，请尽快完成支付，否则订单将会被取消");
                }

            }
        }

        ShopReceiptAddress address=shopReceiptAddressDao.findOne(shopOrder.getReceiptAddressId());

        if (address == null) return ResultUtils.returnFail("地址信息不存在");

        SysUser sysUser=userMapper.selectByPrimaryKey(shopOrder.getUserId());

        if (sysUser == null) return ResultUtils.returnFail("用户不存在");
        double totalPrice=0D;
        int count=0;

        String orderNum=numberUtils.createOrderNum();
        shopOrder.setFromType("商城");
        shopOrder.setReceiveName(address.getReceiptName());
        shopOrder.setReceivePhone(address.getReceiptPhone());
        shopOrder.setUserNickName(sysUser.getNickname());
        shopOrder.setUserPhone(sysUser.getPhone());

        shopOrder.setReceiveAddress(address.getReceiptAddress());
        shopOrder.setReceiveName(address.getReceiptName());
        shopOrder.setReceivePhone(address.getReceiptPhone());

        shopOrder.setIsRefound(0);
        shopOrder.setOrderNum(orderNum);
        shopOrder.setCreateTime(new Date());
        shopOrder.setUpdateTime(new Date());
        shopOrder.setIsCallBack(0);
        shopOrder.setOrderStatus(1);
        shopOrder.setRemindStatus(0);
        shopOrder.setPayType(0);
        shopOrder.setDelStatus(0);
        shopOrder.setPayStatus(0);
        shopOrder.setIsDelay(0);
        shopOrder.setDelStatus(0);
        shopOrder.setReceiveName(address.getReceiptName());
        shopOrder.setReceivePhone(address.getReceiptPhone());
        shopOrder.setType(0);
        ShopOrder order=shopOrderDao.save(shopOrder);

        List<ShopCommodityOrder> shopCommodityList=new ArrayList<>();
        for (ShopCommodityOrder shopCommodityOrder : shopCommodityOrders.getShopCommodityOrders()) {
            if (shopCommodityOrder.getAmount() == null || shopCommodityOrder.getCommodityId() == null) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultUtils.returnException();
            }
            ShopCommodity commodity=shopCommodityDao.findOne(shopCommodityOrder.getCommodityId());
            if (commodity.getTotalNum() < shopCommodityOrder.getAmount()) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultUtils.returnFail("库存不足，掌柜的正在备货，请稍等~");
            }
            commodity.setTotalSalesNum(commodity.getTotalSalesNum() + shopCommodityOrder.getAmount());
            commodity.setTotalNum(commodity.getTotalNum() - shopCommodityOrder.getAmount());
            shopCommodityOrder.setOrderId(order.getId());
            shopCommodityOrder.setCreateTime(new Date());
            shopCommodityOrder.setCommodityName(commodity.getName());
            shopCommodityOrder.setUpdateTime(new Date());
            shopCommodityOrder.setStatus(0);
            shopCommodityOrder.setColors(commodity.getColorName());
            shopCommodityOrder.setPrices(commodity.getPrice());
            shopCommodityOrder.setModelName(commodity.getModelName());
            shopCommodityOrder.setSizeName(commodity.getSizeName());
            shopCommodityOrder.setCommodityUrl(commodity.getCoverUrl());
            Double prices=shopCommodityOrder.getPrices();
            Integer amount=shopCommodityOrder.getAmount();
            totalPrice+=prices * amount;
            count++;
            shopCommodityList.add(shopCommodityOrder);
        }

        order.setTotalCount(count);
        order.setTotalMoney(totalPrice);
        commodityOrderDao.save(shopCommodityList);

        return ResultUtils.returnSucess(order);

    }

    @Override
    public ResultUtils cancelOrder(Integer userId, Long orderId) {

        ShopOrder shopOrder=shopOrderDao.findOne(orderId);
        Integer userId1=shopOrder.getUserId();
        if (userId == null || !userId.equals(userId1)) return ResultUtils.returnFail();
        if (shopOrder.getOrderStatus() == 1 || shopOrder.getPayStatus() == 0) {
            try {
                shopOrder.setType(1);
                return ResultUtils.returnSucess();
            } catch (Exception e) {
                return ResultUtils.returnFail();
            }
        }
        return ResultUtils.returnFail("订单未完成，不能取消订单");

    }

    @Override
    public ResultUtils findAll(Integer userId, Integer page, Integer size, Integer type) {
        if (page < 1) {
            return ResultUtils.returnFail();
        }
        PageRequest pageable=new PageRequest(page - 1, size, SortUtils.DESCCreateTime());
        List<Map> data=new ArrayList<>();
        Page<ShopOrder> shopOrders;
        if (type == null || type == 0) {
            //全部
            shopOrders=shopOrderDao.findByUserIdEqualsAndDelStatus(userId, 0, pageable);
        } else {
            shopOrders=shopOrderDao.findByUserIdAndOrderStatusEqualsAndDelStatusAndType(userId, type, 0, 0, pageable);
        }
        List<ShopOrder> result=shopOrders.getContent();

        for (ShopOrder shopOrder : result) {

            Map<String, Object> map=new HashMap<>();
            List<Map> list=new ArrayList<>();
            Long id=shopOrder.getId();
            List<ShopCommodityOrder> lists=commidityOrderDao.findAllByOrderIdEquals(id);
            for (ShopCommodityOrder o : lists) {
                ShopCommodity one=shopCommodityDao.findOne(o.getCommodityId());
                Map<String, Object> temp=new HashMap<>();
                temp.put("ShopCommodity", one);
                temp.put("ShopCommodityOrder", o);
                list.add(temp);
            }
            map.put("order", shopOrder);
            map.put("list", list);
            data.add(map);
        }


        if (data.size() <= 0) {
            return ResultUtils.returnFail();
        }
        return ResultUtils.returnSucess(data, shopOrders.getTotalPages());
    }

    @Override
    public ResultUtils deleteOrder(Integer userId, Long orderId) {
        ShopOrder shopOrder=shopOrderDao.findOne(orderId);
        Integer userId1=shopOrder.getUserId();
        if (userId == null || !userId.equals(userId1)) return ResultUtils.returnFail();
        try {
            shopOrder.setDelStatus(1);
            return ResultUtils.returnSucess();
        } catch (Exception e) {
            return ResultUtils.returnFail();
        }
    }

    /*@Override
    public ResultUtils remindSendOut(Integer userId, Long orderId) {

        ShopOrder shopOrder=shopOrderDao.findOne(orderId);
        if (shopOrder == null) return ResultUtils.returnException();

        if (userId == null || !userId.equals(shopOrder.getUserId())) return ResultUtils.returnFail();
        if (shopOrder.getPayStatus() == 1 && shopOrder.getOrderStatus() == 2) {
            if (shopOrder.getRemindStatus() == 0)
                shopOrder.setUpdateTime(new Date());
            shopOrder.setRemindStatus(1);
            return ResultUtils.returnSucess("已提醒发货！！");
        }
        return ResultUtils.returnFail("该订单不能提醒发货");
    }*/

   /* @Override
    public ResultUtils delay(Integer userId, Long orderId) {
        ShopOrder shopOrder=shopOrderDao.findOne(orderId);
        if (userId == null || shopOrder == null || !shopOrder.getUserId().equals(userId))
            return ResultUtils.returnFail();
        Date receiptTime=shopOrder.getReceiptTime();

        if (shopOrder.getOrderStatus() == 3 && shopOrder.getPayStatus() == 1 && shopOrder.getType() == 0) {

            if (shopOrder.getIsDelay() != null && shopOrder.getIsDelay() == 0) {
                Calendar cal=Calendar.getInstance();
                cal.setTime(receiptTime);
                cal.add(Calendar.DATE, 7);
                receiptTime=cal.getTime();
                shopOrder.setReceiptTime(receiptTime);
                shopOrder.setIsDelay(1);
                return ResultUtils.returnSucess();
            }

        }

        return ResultUtils.returnFail();


    }*/

    @Override
    public ResultUtils orderdetails(Integer userId, Long orderId) {
        ShopOrder shopOrder=shopOrderDao.findOne(orderId);
        if (shopOrder == null || userId == null || !userId.equals(shopOrder.getUserId()))
            return ResultUtils.returnException();

        long createTime=shopOrder.getCreateTime().getTime();
        long time=new Date().getTime();
        long ms=time - createTime;
        int ss=1000;
        int mi=ss * 60;
        int hh=mi * 60;
        int dd=hh * 24;
        long day=ms / dd;
        long hour=(ms - day * dd) / hh;


        Map<String, Object> map=new HashMap<>();
        List<Map> list=new ArrayList<>();
        Long id=shopOrder.getId();
        List<ShopCommodityOrder> lists=commidityOrderDao.findAllByOrderIdEquals(id);


        for (ShopCommodityOrder o : lists) {
            ShopCommodity one=shopCommodityDao.findOne(o.getCommodityId());
            Map<String, Object> temp=new HashMap<>();
            temp.put("ShopCommodity", one);
            temp.put("ShopCommodityOrder", o);
            list.add(temp);
        }

        Long receiptAddressId=shopOrder.getReceiptAddressId();
        ShopReceiptAddress receiptAddress=shopReceiptAddressDao.findOne(receiptAddressId);

        map.put("receiptAddress", receiptAddress);
        map.put("order", shopOrder);
        map.put("list", list);
        map.put("day", day);
        map.put("hours", hour);

        return ResultUtils.returnSucess(map);
    }

    @Override
    public ResultUtils confirmReceipt(Integer userId, Long orderId) {
        ShopOrder shopOrder=shopOrderDao.findOne(orderId);
        if (shopOrder == null || userId == null || !userId.equals(shopOrder.getUserId()))
            return ResultUtils.returnException();

        if (shopOrder.getPayStatus() == 1 && shopOrder.getOrderStatus() == 3 && shopOrder.getType() == 0) {
            shopOrder.setOrderStatus(4);
            shopOrder.setType(2);
            shopOrder.setEndTime(new Date());
            return ResultUtils.returnSucess(shopOrder);
        }
        return ResultUtils.returnFail();
    }

    @Override
    public ResultUtils refundApplication(Integer type, Integer userId, Long orderId, String money, String reason, Long shopOrderAndCommodityId, Long backid, String backname) {
        ShopOrder shopOrder=shopOrderDao.findOne(orderId);


        if (shopOrder == null || userId == null || !userId.equals(shopOrder.getUserId()))
            return ResultUtils.returnException();

        if (shopOrderAndCommodityId == null)
            return ResultUtils.returnException();

        ShopCommodityOrder shopCommodityOrder=commidityOrderDao.findOne(shopOrderAndCommodityId);
        if (shopCommodityOrder == null)
            return ResultUtils.returnException();

        if (Double.parseDouble(money) > shopCommodityOrder.getAmount() * shopCommodityOrder.getPrices())
            return ResultUtils.returnFail("退款金额超过限制");


        if (type == 0) {
            if (shopOrder.getPayStatus() != 1 || shopOrder.getOrderStatus() != 2 || shopOrder.getType() != 0) {
                return ResultUtils.returnException();
            }
        }
        if (type == 1) {
            if (shopOrder.getPayStatus() != 1 || shopOrder.getOrderStatus() != 3 || shopOrder.getType() != 0) {
                return ResultUtils.returnException();
            }
        }
        if (shopCommodityOrder.getStatus() == 2 || shopCommodityOrder.getStatus() == 3) {
            return ResultUtils.returnFail("该订单已申请退款");
        }

        shopCommodityOrder.setUpdateTime(new Date());
        shopCommodityOrder.setStatus(2);
        commidityOrderDao.save(shopCommodityOrder);

        ShopRefundRecord refundRecord=new ShopRefundRecord();

        if (type == 0) {
            List<ShopCommodityOrder> commodityOrders=commidityOrderDao.findByOrderIdIsAndStatusIs(orderId, 1);

            if (commodityOrders == null || commodityOrders.size() == 0) {
                shopOrder.setIsRefound(1);
            }
            refundRecord.setBackType(0);
        }

        if (type == 1) {
            refundRecord.setBackType(1);
        }

        refundRecord.setFromType(shopOrder.getFromType());
        refundRecord.setRefundNum(OrderInfoUtil2_0.getOutTradeNo());
        refundRecord.setBackId(backid);
        refundRecord.setCommodityId(shopCommodityOrder.getCommodityId());
        refundRecord.setCommodityName(shopCommodityOrder.getCommodityName());
        refundRecord.setShopCommodityOrderId(shopCommodityOrder.getId());
        refundRecord.setShopOrderNum(shopOrder.getOrderNum());
        refundRecord.setShopOrderId(shopOrder.getId());
        refundRecord.setBackName(backname);
        refundRecord.setBackMoney(Double.parseDouble(money));
        refundRecord.setBuyTime(shopOrder.getCreateTime());
        refundRecord.setCreateTime(new Date());
        refundRecord.setUpdateTime(new Date());
        refundRecord.setPayTime(shopOrder.getPayTime());
        refundRecord.setSendTime(shopOrder.getExpressSendTime());
        refundRecord.setSizeName(shopCommodityOrder.getSizeName());
        refundRecord.setReceiveName(shopOrder.getReceiveName());
        refundRecord.setReceivePhone(shopOrder.getReceivePhone());
        refundRecord.setUserNickName(shopOrder.getUserNickName());
        refundRecord.setPayOutNum(shopOrder.getPayOrderNum());
        refundRecord.setAmount(shopCommodityOrder.getAmount());
        refundRecord.setCommodityUrl(shopCommodityOrder.getCommodityUrl());
        refundRecord.setReceiveAddress(shopOrder.getReceiveAddress());
        refundRecord.setColors(shopCommodityOrder.getColors());
        refundRecord.setModelName(shopCommodityOrder.getModelName());
        refundRecord.setPrices(shopCommodityOrder.getPrices());
        refundRecord.setUserId(shopOrder.getUserId());
        refundRecord.setStatus(0);
        refundRecord.setUserPhone(shopOrder.getUserPhone());
        refundRecord.setAuditStatus(0);
        refundRecord.setReasons(reason);
        shopRefundRecordDao.save(refundRecord);
        return ResultUtils.returnSucess();
    }

    @Override
    public ResultUtils refundexpress(Integer userId, Long refundID, Long backExpressid, String backExpressName, String backExpressNum) {
        ShopRefundRecord refundRecord=shopRefundRecordDao.findOne(refundID);

        if (refundRecord == null || !Objects.equals(refundRecord.getUserId(), userId)) return ResultUtils.returnFail();

        refundRecord.setBackExpressDictId(backExpressid);
        refundRecord.setBackExpressName(backExpressName);
        refundRecord.setBackExpressNum(backExpressNum);
        refundRecord.setBackSendTime(new Date());

        return ResultUtils.returnSucess();
    }

    @Override
    public ResultUtils refundOrderList(Integer userId, Integer page, Integer size) {

        if (page < 1) {
            return ResultUtils.returnFail();
        }
        Pageable pageable=new PageRequest(page - 1, size, SortUtils.DESCCreateTime());
        Page<ShopRefundRecord> refundOrder;

        refundOrder=shopRefundRecordDao.findByBackTypeList(0, 1, userId, pageable);

        if (refundOrder == null || refundOrder.getContent() == null || refundOrder.getContent().size() == 0) {
            return ResultUtils.returnFail();
        }
        return ResultUtils.returnSucess(refundOrder);
    }


    @Override
    public ResultUtils refundorderDetails(Integer userId, Long refundID) {
        ShopRefundRecord refundRecord=shopRefundRecordDao.findOne(refundID);

        if (!refundRecord.getUserId().equals(userId)) {
            return ResultUtils.returnException();
        }

        ShopCommodity commoditye=shopCommodityDao.findOne(refundRecord.getCommodityId());
        Map<String, Object> m=new HashMap<>();
        m.put("refundRecord", refundRecord);
        m.put("commodity", commoditye);


        return ResultUtils.returnSucess(m);
    }

    @Override
    public ResultUtils refundOrderProgress(Integer userId, Long refundID) {

        ShopRefundRecord refundRecord=shopRefundRecordDao.findOne(refundID);

        if (!refundRecord.getUserId().equals(userId)) {
            return ResultUtils.returnException();
        }

        Integer status=refundRecord.getStatus();
        Integer auditStatus=refundRecord.getAuditStatus();
        Integer backType=refundRecord.getBackType();
        Date backSendTime=refundRecord.getBackSendTime();
        Date endTime=refundRecord.getEndTime();
        List<ParamValue> data=new ArrayList<>();

        if (backType == 1) {

            //申请退款为审核
            if (auditStatus == 0 && status == 0 && backSendTime == null && endTime == null) {
                data.add(createParamValue("申请退款", refundRecord.getCreateTime()));
                data.add(createParamValue("等待平台审核", new Date()));

            }
            //申请退货审核拒绝
            if (auditStatus == 1 && status == 2 && backSendTime == null && endTime == null) {
                data.add(createParamValue("申请退款", refundRecord.getCreateTime()));
                data.add(createParamValue("平台拒绝退货", refundRecord.getAuditTime()));
                data.add(createParamValue("退货失败", refundRecord.getEndTime()));
            }

            //申请退货审核同意 买家未发货
            if (auditStatus == 2 && status == 1 && backSendTime == null && endTime == null) {
                data.add(createParamValue("申请退货", refundRecord.getCreateTime()));
                data.add(createParamValue("平台同意退货", refundRecord.getAuditTime()));
                data.add(createParamValue("等待用户退货", new Date()));
            }
            //申请退货审核同意 买家发货
            if (auditStatus == 2 && status == 1 && backSendTime != null && endTime == null) {
                data.add(createParamValue("申请退货", refundRecord.getCreateTime()));
                data.add(createParamValue("平台同意退货", refundRecord.getAuditTime()));
                data.add(createParamValue("用户退货", refundRecord.getBackSendTime()));
                data.add(createParamValue("退款中", new Date()));
            }
            //申请退货审核同意 退款完成
            if (auditStatus == 2 && status == 2 && backSendTime != null && endTime != null) {
                data.add(createParamValue("申请退货", refundRecord.getCreateTime()));
                data.add(createParamValue("平台同意退货", refundRecord.getAuditTime()));
                data.add(createParamValue("用户退货", refundRecord.getBackSendTime()));
                data.add(createParamValue("退款完成", refundRecord.getEndTime()));
            }

        }

        if (backType == 0) {

            //申请退款为审核
            if (auditStatus == 0 && status == 0) {
                data.add(createParamValue("申请退款", refundRecord.getCreateTime()));
                data.add(createParamValue("等待平台审核", new Date()));

            }
            //申请退款审核拒绝
            if (auditStatus == 1 && status == 2) {
                data.add(createParamValue("申请退款", refundRecord.getCreateTime()));
                data.add(createParamValue("平台拒绝退款", refundRecord.getAuditTime()));
                data.add(createParamValue("退款失败", refundRecord.getEndTime()));
            }

            //申请退款审核同意
            if (auditStatus == 2 && status == 2) {
                data.add(createParamValue("申请退款", refundRecord.getCreateTime()));
                data.add(createParamValue("平台同意退款", refundRecord.getAuditTime()));
                data.add(createParamValue("退款成功", refundRecord.getEndTime()));
            }

        }
        return ResultUtils.returnSucess(data);

    }

    @Override
    public ResultUtils orderGroupNumber(Integer userId) {

        int totalCount=shopOrderDao.countByUserIdEqualsAndDelStatus(userId, 0);
        int noPayCount=shopOrderDao.countByUserIdAndOrderStatusEqualsAndDelStatusAndType(userId, 1, 0, 0);
        int noSendCount=shopOrderDao.countByUserIdAndOrderStatusEqualsAndDelStatusAndType(userId, 2, 0, 0);
        int noSignCount=shopOrderDao.countByUserIdAndOrderStatusEqualsAndDelStatusAndType(userId, 3, 0, 0);
        int refundCount=shopRefundRecordDao.countByUserIdIs(userId);


        Map<String, Integer> map=new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("noPayCount", noPayCount);
        map.put("noSendCount", noSendCount);
        map.put("noSignCount", noSignCount);
        map.put("refundCount", refundCount);
        return ResultUtils.returnSucess(map);
    }


    private ParamValue createParamValue(String str, Date date) {
        ParamValue v1=new ParamValue();
        v1.setKey(str);

        v1.setValue(date);
        return v1;
    }

}
