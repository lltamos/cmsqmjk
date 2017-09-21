package com.quanmin.controller.app;

import com.quanmin.model.custom.PageArgs;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.jpapo.AppointOrder;
import com.quanmin.service.AppointOrderService;
import com.quanmin.util.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping("productionorder")
    @ResponseBody
    public ResultUtils productionOrder(@Valid AppointOrder appointOrder, Errors errors) {

        List<ObjectError> allErrors=errors.getAllErrors();
        if (allErrors.size() > 0)
            return ResultUtils.returnException();

        return appointOrderService.productionOrder(appointOrder);
    }


}
