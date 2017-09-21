package com.quanmin.service;

import com.alipay.api.AlipayApiException;
import com.quanmin.util.ResultUtils;

/**
 * IShopOrderService
 *
 * @author llsmp
 * @date 2017/7/25
 */
public interface CMSIShopOrderService {



    ResultUtils findAll(Integer page, Integer size, Integer type,String querystr);


    ResultUtils orderdetails( Long orderId);

    ResultUtils sendOut(Long orderId, Integer expressCompanyId,String expressCompanyNameSimple, String expressCompanyName, String expressNum, Integer type,String expressSendName);

   // ResultUtils findRemindSendOutList(Integer page, Integer size);
    //退款页面
    ResultUtils refundOrderList(Integer type, Integer status,Integer auditStatus ,Integer page, Integer size, String querystr);
    //退款审核
    ResultUtils refundAudit(Long refundID, Integer type) throws AlipayApiException;

    ResultUtils refundCargoAudit(Long refundID,  Integer type) throws AlipayApiException;

    ResultUtils refundorderDetails(Long refundID);

    ResultUtils exportOrder(Integer type, String querystr);

    ResultUtils exportRefund(Integer type, Integer status,Integer auditStatus, String querystr);
}
