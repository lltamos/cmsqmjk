package com.quanmin.util.alipayutil;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.quanmin.model.jpapo.PayApliyNotifyInfo;
import com.quanmin.util.Commons;
import com.quanmin.util.DateFormatUtil;
import com.quanmin.util.LoadPropertiesDataUtils;
import com.quanmin.util.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付工具类
 * @author  heasy
 * @date 2016/10/31
 */
public class PayUtils {

    /**
     * 支付工具类
     *
     * @param orderNum
     * @param price
     * @param body//订单标题
     * @param desc//订单描述
     * @return
     * @throws AlipayApiException
     */
    public static String aliPay(String orderNum, Double price, String body, String desc,String notifyUrl) throws AlipayApiException {
        AlipayClient alipayClient=new DefaultAlipayClient(Commons.ALIGATEWAY, Commons.APP_ID, Commons.APP_PRIVATE_KEY, Commons.FORMAT, Commons.CHARSET, Commons.ALIPAY_PUBLIC_KEY, Commons.SIGNTYPE);
        AlipayTradeAppPayRequest request=new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model=new AlipayTradeAppPayModel();
        model.setBody(desc);
        model.setSubject(body);
        model.setOutTradeNo(orderNum);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(price + "");
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        AlipayTradeAppPayResponse response=alipayClient.sdkExecute(request);
        if (response.isSuccess()) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public static Boolean submitRebate(String orderNum, Double price, String desc) throws AlipayApiException {
        AlipayClient alipayClient=new DefaultAlipayClient(Commons.ALIGATEWAY, Commons.APP_ID, Commons.APP_PRIVATE_KEY, Commons.FORMAT, Commons.CHARSET, Commons.ALIPAY_PUBLIC_KEY, Commons.SIGNTYPE);
        AlipayTradeRefundRequest request=new AlipayTradeRefundRequest();
        HashMap<String, Object> map=new HashMap<>(16);
        map.put("out_trade_no", orderNum);
        map.put("refund_amount", price);
        map.put("refund_reason", desc);
        request.setBizContent(JSON.toJSONString(map));
        AlipayTradeRefundResponse response=alipayClient.execute(request);
        return response.isSuccess();
    }


    /**
     * 支付回调返回数据到map中
     *
     * @param request
     * @return
     * @throws AlipayApiException
     */
    public static Map<String, String> callbackAliPayToMap(HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        System.out.println("进入回调");
        //获取支付宝POST过来反馈信息
        Map<String, String> params=new HashMap<String, String>(16);
        Map requestParams=request.getParameterMap();
        System.out.println("异步回调返回数据:" + JSON.toJSON(requestParams));
        for (Iterator iter=requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name=(String) iter.next();
            String[] values=(String[]) requestParams.get(name);
            String valueStr="";
            for (int i=0; i < values.length; i++) {
                valueStr=(i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            valueStr=new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }

    public static PayApliyNotifyInfo callbackAliPayToPojo(Map<String, String> map) throws ParseException {

        PayApliyNotifyInfo payApliyNotifyInfo=new PayApliyNotifyInfo();
        if (null != map.get("gmt_create")) {
            payApliyNotifyInfo.setGmtCreate(DateFormatUtil.stringToDateformatyyyyMMddHHmmss(map.get("gmt_create")));
        }
        payApliyNotifyInfo.setCharset(map.get("charset"));
        payApliyNotifyInfo.setSellerEmail(map.get("seller_email"));
        payApliyNotifyInfo.setSubject(map.get("subject"));
        payApliyNotifyInfo.setSign(map.get("sign"));
        payApliyNotifyInfo.setBody(map.get("body"));
        payApliyNotifyInfo.setBuyerId(map.get("buyer_id"));
        payApliyNotifyInfo.setInvoiceAmount(map.get("invoice_amount"));
        payApliyNotifyInfo.setNotifyId(map.get("notify_id"));
        payApliyNotifyInfo.setFundBillList(map.get("fund_bill_list"));
        payApliyNotifyInfo.setNotifyType(map.get("notify_type"));
        //判断是否支付成功  TRADE_SUCCE01只有这个是支付成功，其他都是失败
        payApliyNotifyInfo.setTradeStatus(map.get("trade_status"));
        payApliyNotifyInfo.setReceiptAmount(map.get("receipt_amount"));
        payApliyNotifyInfo.setAppId(map.get("app_id"));
        payApliyNotifyInfo.setBuyerPayAmount(map.get("buyer_pay_amount"));
        payApliyNotifyInfo.setSignType(map.get("sign_type"));
        payApliyNotifyInfo.setSellerId(map.get("seller_id"));
        if (null != map.get("gmt_payment")) {
            payApliyNotifyInfo.setGmtPayment(DateFormatUtil.stringToDateformatyyyyMMddHHmmss(map.get("gmt_payment")));
        }
        if (null != map.get("notify_time")) {
            payApliyNotifyInfo.setNotifyTime(DateFormatUtil.stringToDateformatyyyyMMddHHmmss(map.get("notify_time")));
        }
        payApliyNotifyInfo.setVersion(map.get("version"));
        payApliyNotifyInfo.setOutTradeNo(map.get("out_trade_no"));
        if (null != map.get("total_amount")) {
            payApliyNotifyInfo.setTotalAmount(Double.parseDouble(map.get("total_amount")));
        }
        payApliyNotifyInfo.setTradeNo(map.get("trade_no"));
        payApliyNotifyInfo.setBuyerPayAmount(map.get("buyer_pay_amount"));
        payApliyNotifyInfo.setBuyerLogonId(map.get("buyer_logon_id"));

        payApliyNotifyInfo.setPointAmount(map.get("point_amount"));
        return payApliyNotifyInfo;
    }
}
