package com.quanmin.controller.app;

import com.quanmin.model.po.WarpList;
import com.quanmin.service.IShopOrderService;
import com.quanmin.util.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * APPShopOrderController
 *
 * @author llsmp
 * @date 2017/7/25
 */
@Controller
@RequestMapping(value = "/api/1/order")
public class APPShopOrderController {
    @Resource
    private IShopOrderService shopOrderService;

    /**
     *确认订单
     * @param userId
     * @param fromType 0购物车进入，1 直接购买
     * @param cartOrProId
     * @param num
     * @return
     */
    @RequestMapping("affirmorder")
    @ResponseBody
    public ResultUtils affirmOrder(Integer userId,Integer fromType ,String cartOrProId,Integer num){
        List<Long> ling=new ArrayList<>();
        String[] split = cartOrProId.split(",");
        for (String s:split) {
            long l = Long.parseLong(s);
            ling.add(l);
        }
        Long[] objects = ling.toArray(new Long[]{});
        return shopOrderService.affirmOrder( userId,fromType,objects ,num);

    }

    /**
     *生成订单
     * @param warpList
     * @return
     */
    @RequestMapping("productionorder")
    @ResponseBody
    public ResultUtils productionOrder( @RequestBody WarpList warpList){
        return shopOrderService.productionOrder(warpList);
    }

    /**
     * 取消订单
     *@param orderId
     * @return
     */
    @RequestMapping("cancelorder")
    @ResponseBody
   public ResultUtils cancelOrder(Integer userId,Long orderId){
       return shopOrderService.cancelOrder(userId,orderId);
   }

    /**
     * 删除订单
     *@param orderId
     * @return
     */
    @RequestMapping("deleteorder")
    @ResponseBody
    public ResultUtils deleteOrder(Integer userId,Long orderId){
        return shopOrderService.deleteOrder(userId,orderId);
    }



    /**
     *@param type 0 all 1待付款、2待发货、3待收货、4已完成
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("findall")
    @ResponseBody
    public ResultUtils findAll(Integer userId,Integer page,Integer size,Integer type){
       return shopOrderService.findAll(userId,page,size,type);
    }

//    /**
//     *提醒发货(暂时不做)
//     * @param userId
//     * @param orderId
//     * @return
//     */
//    @RequestMapping("remindSendOut")
//    @ResponseBody
//    public ResultUtils remindSendOut(Integer userId,Long orderId){
//        return shopOrderService.remindSendOut(userId, orderId);
//    }

//    /**
//     *延迟发货(暂时不做)
//     * @param userId
//     * @param orderId
//     * @return
//     */
//    @RequestMapping("delay")
//    @ResponseBody
//    public ResultUtils delay(Integer userId,Long orderId){
//
//        return shopOrderService.delay(userId, orderId);
//    }

    /**
     * @param userId
     * @param orderId
     * @return
     */
    @RequestMapping("orderdetails")
    @ResponseBody
    public ResultUtils orderDetails(Integer userId,Long orderId){
        return shopOrderService.orderdetails(userId, orderId);
    }

    /**
     *确认收货
     * @param userId
     * @param orderId
     * @return
     */
    @RequestMapping("confirmreceipt")
    @ResponseBody
    public ResultUtils confirmReceipt(Integer userId,Long orderId){
        return shopOrderService.confirmReceipt(userId, orderId);
    }

    /**
     * 退款申请
     * @param type  退款类型 0退款，1退货退款
     * @param userId
     * @param orderId
     * @param money 退款金额
     * @param reason 说明
     * @param shopOrderAndCommodityId 订单商品关系表id
     * @param backid 退换原因id
     * @param backName 不想买了、尺寸颜色拍错、
                 质量问题、发错货
     * @return
     */
    @RequestMapping("refundapply")
    @ResponseBody
    public ResultUtils refundApplication(Integer type,Integer userId,Long orderId,String money,String reason,Long shopOrderAndCommodityId,Long backid,String backName){
        return shopOrderService.refundApplication(type,userId, orderId,money,reason,shopOrderAndCommodityId,backid,backName);
    }

    /**
     * 退货快递
     * @param userId
     * @param refundID
     * @param backExpressid
     * @param backExpressName
     * @param backExpressNum
     * @return
     */
    @RequestMapping("refundexpress")
    @ResponseBody
    public ResultUtils refundexpress(Integer userId,Long refundID,Long backExpressid,String backExpressName,String backExpressNum){
        return shopOrderService.refundexpress(userId, refundID,backExpressid,backExpressName,backExpressNum);
    }

    /**
     *退款页面
     *   userId
     * @return
     */
    @RequestMapping("refundorderlist")
    @ResponseBody
    public ResultUtils refundOrderList(Integer userId,Integer page,Integer size) {
        return shopOrderService.refundOrderList(userId,page,size);
    }


    /**
     *退款详情
     * @param  refundID 退款记录id
     * @return
     */
    @RequestMapping("refundorderdetails")
    @ResponseBody
    public ResultUtils refundorderDetails( Integer userId,Long refundID) {
        return shopOrderService.refundorderDetails(userId,refundID);
    }


    /**
     * 退款进度
     */
    @RequestMapping("refundorderprogress")
    @ResponseBody
    public ResultUtils refundOrderProgress( Integer userId,Long refundID) {
        return shopOrderService.refundOrderProgress(userId,refundID);
    }

    /**
     * 订单各分类数量
     * @param userId
     * @return
     */
    @RequestMapping("ordergroupnumber")
    @ResponseBody
    public ResultUtils orderGroupNumber( Integer userId) {
        return shopOrderService.orderGroupNumber(userId);
    }


}
