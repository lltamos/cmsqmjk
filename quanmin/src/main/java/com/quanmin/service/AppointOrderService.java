package com.quanmin.service;

import com.quanmin.model.custom.PageArgs;
import com.quanmin.model.jpapo.AppointOrder;
import com.quanmin.util.ResultUtils;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
public interface AppointOrderService {

    ResultUtils productionOrder(AppointOrder appointOrder);
    ResultUtils factorQueryList(PageArgs args, AppointOrder appointOrder);
}
