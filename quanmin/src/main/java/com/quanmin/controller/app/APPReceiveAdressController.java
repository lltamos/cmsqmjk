package com.quanmin.controller.app;


import com.quanmin.model.jpapo.ShopReceiptAddress;
import com.quanmin.service.impl.ShopReceiptAddressServiceImpl;
import com.quanmin.util.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/1/address/")
public class APPReceiveAdressController {

    @Resource
    private ShopReceiptAddressServiceImpl shopReceiptAddressService;
    @RequestMapping("addaddress")
    @ResponseBody
    public ResultUtils addAddress(ShopReceiptAddress receiptAddress) {

        return shopReceiptAddressService.addAddress(receiptAddress);
    }

    @RequestMapping("updateaddress")
    @ResponseBody
    public ResultUtils updateAddress(ShopReceiptAddress receiptAddress) {

        return shopReceiptAddressService.updateAddress(receiptAddress);
    }

    @RequestMapping("deleteaddress")
    @ResponseBody
    public ResultUtils deleteAddress( @RequestParam Integer userId, String longs) {
        List<Long> ling=new ArrayList<>();
        String[] split = longs.split(",");
        for (String s:split) {
            long l = Long.parseLong(s);
            ling.add(l);
        }
        Long[] objects = ling.toArray(new Long[]{});
        return shopReceiptAddressService.deleteAddress(userId, objects);
    }


    @RequestMapping("findall")
    @ResponseBody
    public ResultUtils findAll(Integer userId) {

        return shopReceiptAddressService.findAll(userId);
    }

}
