package com.quanmin.controller.cms;

import com.quanmin.model.custom.PageArgs;
import com.quanmin.model.jpapo.AppointOrder;
import com.quanmin.service.AppointOrderService;
import com.quanmin.util.ResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
@RestController
@RequestMapping("/cms/1/appoint")
public class CMSAppointOrderController {

    @Resource
    private AppointOrderService appointOrderService;

    /**
     * @param pageArgs
     * @param hospital //医院
     * @param office   //科室
     * @param period   //时间段
     * @return
     */
    @RequestMapping("factorquerylist")
    public ResultUtils factorQueryList(PageArgs pageArgs, AppointOrder appointOrder) {
        return appointOrderService.factorQueryList(pageArgs, appointOrder);
    }

}
