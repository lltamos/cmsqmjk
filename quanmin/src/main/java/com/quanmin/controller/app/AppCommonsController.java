package com.quanmin.controller.app;

import com.quanmin.model.jpapo.ShopDict;
import com.quanmin.service.CommonService;
import com.quanmin.util.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/api/1/commons")
public class AppCommonsController {
    @Resource
    private CommonService commonService;

    /**
     * 获取标签列表
     *
     * @return
     */
    @RequestMapping("/finddictlist")
    @ResponseBody
    public ResultUtils findShopDictList(Integer type) {
        List<ShopDict> shopCommodity = commonService.shopDictList(type);
        return (null != shopCommodity) ? ResultUtils.returnSucess(shopCommodity) : ResultUtils.returnFail();
    }
}
