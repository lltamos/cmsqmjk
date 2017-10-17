package com.quanmin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.quanmin.dao.jpa.*;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.model.SysUser;
import com.quanmin.model.jpapo.*;
import com.quanmin.service.APPPayService;
import com.quanmin.util.Commons;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.LoadPropertiesDataUtils;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.alipayutil.PayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/8/22.
 */
@SuppressWarnings("ALL")
@Service
@Transactional
public class APPPayServiceImpl implements APPPayService {
    @Resource
    private ShopOrderDao shopOrderDao;

    @Resource
    private ShopCommidityOrderDao shopCommidityOrderDao;


    @Resource
    private PayApliyNotifyInfoDao apliyNotifyInfoDao;

    @Resource
    private ShopRefundRecordDao shopRefundRecordDao;

    @Resource
    private BusinessAccountDao accountDao;

    @Override
    public ResultUtils orderAliPay(String orderNum, Double price, Long orderId, Integer type) throws AlipayApiException {
        ResultUtils resultUtils=null;

        switch (type) {
            //支付支付
            case 1:
                String body="";
                String desc="";
                List<ShopCommodityOrder> list=shopCommidityOrderDao.findByOrderIdIsAndStatusIs(orderId, 0);
                if (null == list || list.size() == 0) {
                    return ResultUtils.returnFail("查询订单为空");
                }
                for (ShopCommodityOrder shopCommodityOrder : list) {
                    if (StringUtils.isEmpty(body)) {
                        ShopOrder order=shopOrderDao.findById(shopCommodityOrder.getOrderId());
                        body="雍和健康--" + order.getOrderNum();
                    }

                    desc=desc + shopCommodityOrder.getCommodityName() + "*" + shopCommodityOrder.getAmount() + ";";
                }
                resultUtils=orderAliPay(orderNum, price, body.substring(0, body.length() - 1), "雍和健康--" + desc.substring(0, desc.length() - 1));
                break;
            //微信支付
            case 2:
                break;
            default:
                return ResultUtils.returnFail();
        }
        return resultUtils;
    }

    public ResultUtils orderAliPay(String orderNum, Double price, String body, String desc) throws AlipayApiException {
        ShopOrder order=shopOrderDao.findByOrderNumEquals(orderNum);
        if (order == null) {
            return ResultUtils.returnException();
        }
        if (price == null) {
            ResultUtils.returnFail("订单异常，为设置金额");
        }
        String notifyURLNo=LoadPropertiesDataUtils.getValue("alipay.product.notifyURLNo");
        String response=PayUtils.aliPay(orderNum, price, body, desc, notifyURLNo);
        if (response == null) {
            return ResultUtils.returnFail("支付失败");
        }
        System.out.println("支付信息：" + JSON.toJSONString(response));//就是orderString 可以直接给客户端请求，无需再做处理。
        return ResultUtils.returnSucess(response);
    }

    @Override
    public void callbackAliPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> map=PayUtils.callbackAliPayToMap(request);

        PayApliyNotifyInfo payApliyNotifyInfo=PayUtils.callbackAliPayToPojo(map);

        String tradeNo=payApliyNotifyInfo.getOutTradeNo();
        //        查找订单表，修改订单状态为付款状态
        if (payApliyNotifyInfo.getTradeStatus().contains("TRADE_SUCCESS")) {
            ShopOrder shopOrder=shopOrderDao.findByOrderNumEquals(tradeNo);

            if (shopOrder.getIsCallBack() == 1) {
                return;
            }

            shopOrder.setPayStatus(1);
            shopOrder.setIsCallBack(1);
            shopOrder.setPayTime(new Date());
            shopOrder.setOrderStatus(2);
            shopOrder.setIsCallBack(1);
            shopOrder.setPayOrderNum(payApliyNotifyInfo.getTradeNo());
            shopOrderDao.save(shopOrder);

            BusinessAccount account=new BusinessAccount();
            account.setCreateTime(shopOrder.getCreateTime());
            account.setBookedTime(new Date());
            account.setUpdateTime(new Date());
            account.setType(1);
            account.setFromType(shopOrder.getFromType());
            account.setOrderId(shopOrder.getId());
            account.setOrderNum(shopOrder.getOrderNum());
            account.setUserId(shopOrder.getUserId());
            account.setUserNickName(shopOrder.getUserNickName());
            account.setUserPhone(shopOrder.getUserPhone());
            account.setMoney(shopOrder.getTotalMoney());

            accountDao.save(account);

            List<ShopCommodityOrder> shopCommodityOrders=shopCommidityOrderDao.findAllByOrderIdEquals(shopOrder.getId());
            for (ShopCommodityOrder shopCommodityOrder : shopCommodityOrders) {
                shopCommodityOrder.setStatus(1);
            }

            shopCommidityOrderDao.save(shopCommodityOrders);
            System.out.println("支付回调修改成功：" + JSON.toJSONString(shopOrder));
            //保存到支付信息表中
            apliyNotifyInfoDao.save(payApliyNotifyInfo);
            response.getWriter().write("success");
        }
    }

    @Override
    public ResultUtils submitRebate(Long reFundId) throws AlipayApiException {
        ShopRefundRecord refundRecord=shopRefundRecordDao.findOne(reFundId);

        AlipayClient alipayClient=new DefaultAlipayClient(Commons.ALIGATEWAY, Commons.APP_ID, Commons.APP_PRIVATE_KEY, Commons.FORMAT, Commons.CHARSET, Commons.ALIPAY_PUBLIC_KEY, Commons.SIGNTYPE);
        AlipayTradeRefundRequest request=new AlipayTradeRefundRequest();

        Map<String, Object> map=new HashMap<>();
        map.put("refund_amount", refundRecord.getBackMoney());
        map.put("refund_reason", "正常退款");
        map.put("out_request_no", refundRecord.getRefundNum());
        map.put("out_trade_no", refundRecord.getShopOrderNum());
        request.setBizContent(JSON.toJSONString(map));
        AlipayTradeRefundResponse response=alipayClient.execute(request);

        System.out.println("退款参数：" + JSON.toJSONString(request));
        if (response.isSuccess()) {
            System.out.println("调用成功");
            return ResultUtils.returnSucess();
        } else {
            System.out.println("调用失败");
            return ResultUtils.returnFail(response.getSubMsg());
        }
    }
}
