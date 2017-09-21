package com.quanmin.service;

import com.alipay.api.AlipayApiException;
import com.quanmin.util.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

/**
 * Created by yang on 2017/8/22.
 */
public interface APPPayService {
    /**
     * 支付接口
     * @param orderNum
     * @param price
     * @return
     */
    ResultUtils orderAliPay(String orderNum, Double price, Long orderId,Integer type) throws AlipayApiException;

    /**
     * 支付回调
     * @param request
     */
    void callbackAliPay(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 退款接口
     * @param reFundId
     * @return
     */
    ResultUtils submitRebate(Long reFundId) throws AlipayApiException;
}
