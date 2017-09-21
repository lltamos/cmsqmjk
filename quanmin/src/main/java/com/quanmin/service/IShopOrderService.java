package com.quanmin.service;

import com.quanmin.model.po.WarpList;
import com.quanmin.util.ResultUtils;

/**
 * IShopOrderService
 *
 * @author llsmp
 * @date 2017/7/25
 */
public interface IShopOrderService {
    ResultUtils affirmOrder(Integer uid,Integer fromType ,Long[] cartOrProId,Integer num);

    ResultUtils productionOrder(WarpList shopCommodityOrders);

    ResultUtils cancelOrder(Integer userId, Long orderId);

    ResultUtils findAll(Integer userId,Integer page,Integer size,Integer type);

    ResultUtils deleteOrder(Integer userId, Long orderId);

//    ResultUtils remindSendOut(Integer userId, Long orderId);
//    ResultUtils delay(Integer userId, Long orderId);

    ResultUtils orderdetails(Integer userId, Long orderId);

    ResultUtils confirmReceipt(Integer userId, Long orderId);

    ResultUtils refundApplication(Integer type, Integer userId, Long orderId, String money, String reason, Long pid, Long backid, String backname);


    ResultUtils refundexpress(Integer userId, Long refundID, Long backExpressid, String backExpressName, String backExpressNum);

    ResultUtils refundOrderList(Integer userId, Integer page, Integer size);

    ResultUtils refundorderDetails(Integer userId, Long refundID);

    ResultUtils refundOrderProgress(Integer userId, Long refundID);

    ResultUtils orderGroupNumber(Integer userId);
}
