package com.quanmin.controller.app;

import com.quanmin.model.custom.AppointParam;
import com.quanmin.model.custom.PageArgs;
import com.quanmin.model.jpapo.AppointOrder;
import com.quanmin.service.AppointOrderService;
import com.quanmin.util.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@Controller
@RequestMapping(value="/api/1/appoint")
public class APPAppointOrderController {

    @Resource
    private AppointOrderService appointOrderService;

    /**
     * 生成订单
     *
     * @param appointParam
     * @param errors
     * @return
     */
    @RequestMapping("productionorder")
    @ResponseBody
    public ResultUtils productionOrder(@Valid AppointParam appointParam,Errors errors) {
        return appointOrderService.productionOrder(appointParam);
    }

    /**
     * 生成三方订单
     *
     * @param appointOrder
     * @param errors
     * @return
     */
    @RequestMapping("productionextraorder")
    @ResponseBody
    public ResultUtils productionExtraOrder(@Valid AppointOrder appointOrder, Errors errors) {
        return appointOrderService.productionExtraOrder(appointOrder);
    }


    /**
     * 订单详情
     * @param appointId
     * @param type 0 正常抢号订单  1三方抢号订单
     * @return
     */
    @RequestMapping("appointditails")
    @ResponseBody
    public ResultUtils appointDitails(Long appointId, Integer userId) {
        return appointOrderService.appointDitails(appointId, userId);
    }

    /**
     * 进行中的订单
     *
     * @param args
     * @param userId
     * @return
     */
    @RequestMapping("progressappoint")
    @ResponseBody
    public ResultUtils progressAppoint(@Valid PageArgs args, Integer userId) {
        return appointOrderService.progressAppoint(args, userId);
    }

    /**
     * 完成的订单
     *
     * @param args
     * @param userId
     * @return
     */
    @RequestMapping("completeappoint")
    @ResponseBody
    public ResultUtils completeAppoint(@Valid PageArgs args, Integer userId) {
        return appointOrderService.completeAppoint(args, userId);
    }


    /**
     * 退款
     *
     * @param appointId
     * @param userId
     * @return
     */
    @RequestMapping("returnappiont")
    @ResponseBody
    public ResultUtils returnAppiont(Long appointId, Integer userId, String desc) {
        return appointOrderService.returnAppiont(appointId, userId, desc);
    }


    /**
     * 取消
     *
     * @param appointId
     * @param userId
     * @return
     */
    @RequestMapping("cancelappointorder")
    @ResponseBody
    public ResultUtils cancelAppointOrder(Long appointId, Integer userId) {
        return appointOrderService.cancelAppointOrder(appointId, userId);
    }


    /**
     * 支付订单
     *
     * @param appointId
     * @param userId
     * @return
     */
    @RequestMapping("payappoint")
    @ResponseBody
    public ResultUtils payAppoint(Long appointId, Integer userId, String body, String desc) {
        return appointOrderService.payAppoint(appointId, userId, body, desc);
    }

    /**
     *  支付回调
     * @param appointId
     * @param userId
     * @return
     */
    @RequestMapping("callbackalipay")
    @ResponseBody
    public void callbackalipay(HttpServletRequest request, HttpServletResponse response) {
        appointOrderService.callbackalipay(request, response);
    }

    /**
     *  支付回调
     * @param appointId
     * @param userId
     * @return
     */
    @RequestMapping("countconsultantorder")
    @ResponseBody
    public ResultUtils countConsultantOrder(Integer consultantId,Integer userId) {
        return  appointOrderService.countConsultantOrder(consultantId,userId);
    }



}
