package com.quanmin.controller.cms;

import com.alipay.api.AlipayApiException;
import com.quanmin.service.CMSIShopOrderService;
import com.quanmin.util.ResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cms/1/order")
public class CMSShopOrderController {
    @Resource
    private CMSIShopOrderService shopOrderService;


    /**
     * @param type 0 all 1待付款、2待发货、3待收货、4已收货
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("findall")
    @ResponseBody
    public ResultUtils findAll(Integer page, Integer size, Integer type,String querystr) {

        return shopOrderService.findAll(page, size, type,querystr);
    }

    /**
     * @param orderId
     * @return
     */
    @RequestMapping("orderdetails")
    @ResponseBody
    public ResultUtils orderDetails(Long orderId) {
        return shopOrderService.orderdetails(orderId);
    }


    /**
     * 退款审核
     * @param
     * @param type 0 允许，1 拒绝
     * @return
     */
    @RequestMapping("refundaudit")
    @ResponseBody
    public ResultUtils refundAudit(Long refundID,Integer type) throws AlipayApiException {
        return shopOrderService.refundAudit(refundID, type);
    }

    /**
     * 退货审核
     * @param
     * @param type 0 同意退货申请，1 拒绝退货申请 2,收到货同意退款
     * @returnargo
     */
    @RequestMapping("refundcargoaudit")
    @ResponseBody
    public ResultUtils refundCargoAudit(Long refundID, Integer type) throws AlipayApiException {
        return shopOrderService.refundCargoAudit(refundID, type);
    }


    /**
     * fahuo
     * @param type 0发货，1 修改发货地址
     * @return
     */
    @RequestMapping("sendout")
    @ResponseBody
    public ResultUtils sendOut(Long orderId, Integer expressCompanyId,String expressCompanyNameSimple, String expressCompanyName, String expressNum,Integer type,String expressSendName) {
        return shopOrderService.sendOut(orderId,expressCompanyId,expressCompanyNameSimple,expressCompanyName,expressNum,type,expressSendName);
    }

//    /**
//     *提醒发货
//     * @return
//     */
//    @RequestMapping("findremindsendoutList")
//    @ResponseBody
//    public ResultUtils findRemindSendOutList(Integer page,Integer size) {
//        return shopOrderService.findRemindSendOutList(page,size);
//    }

    /**
     *tuikuan yemian
     * @param  type 0退款，1退货
     * @param  status 0 申请退款 1，退款中，2，已完成 4，全部
     * @return
     */
    @RequestMapping("refundorderlist")
    @ResponseBody
    public ResultUtils refundOrderList(Integer type, Integer status,Integer auditStatus ,Integer page, Integer size, String querystr) {
        return shopOrderService.refundOrderList(type,status,auditStatus,page,size,querystr);
    }

    /**
     *退款详情
     * @param  refundID 退款记录id
     * @return
     */
    @RequestMapping("refundorderdetails")
    @ResponseBody
    public ResultUtils refundorderDetails( Long refundID) {
        return shopOrderService.refundorderDetails(refundID);
    }


    /**
     *导出 订单
     * @param type 0 all 1待付款、2待发货、3待收货、4已收货
     */
    @RequestMapping("exportorder")
    @ResponseBody
    public ResultUtils exportOrder(  Integer type,String querystr) {
        return shopOrderService.exportOrder(type,querystr);
    }

    /**
     * 导出 退款
     * @param  type 0退款，1退货
     * @param  status 0 申请退款 1，退款中，2，已完成 4全部
     */
    @RequestMapping("exportrefund")
    @ResponseBody
    public ResultUtils exportRefund(Integer type, Integer status,Integer auditStatus, String querystr) {
        return shopOrderService.exportRefund(type,status,auditStatus,querystr);
    }







}
