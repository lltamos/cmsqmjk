package com.quanmin.controller.app;

import com.alipay.api.AlipayApiException;
import com.quanmin.service.APPPayService;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yang on 2017/6/16.
 */
@RestController
@RequestMapping(value="/api/1/")
public class APPPayController {

    @Autowired
    private APPPayService payService;


    /**
     * @param orderNum
     * @param type     1 支付宝， 2微信
     * @param price
     * @return
     * @throws AlipayApiException
     */

    @RequestMapping(value="/orderpay")
    public ResultUtils orderPay(String orderNum, Integer type, Double price, Long orderId) throws AlipayApiException {
        return payService.orderAliPay(orderNum, price, orderId, type);
    }

    /**
     * 支付回调
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping(value="/alipay/callbackalipay")
    public void callbackAliPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
          payService.callbackAliPay(request,response);
    }

    /**
     * 退款
     *
     * @param reFundId
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping("submitrebate")
    public ResultUtils submitRebate(Long reFundId) throws AlipayApiException {
        return payService.submitRebate(reFundId);
    }


}
