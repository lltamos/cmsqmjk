package com.quanmin.controller.app;


import com.quanmin.model.jpapo.ShopCart;
import com.quanmin.service.IShopCartService;
import com.quanmin.util.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/api/1/cart/")
public class APPShopCartController {

    @Resource
    private IShopCartService shopCartService;

    @RequestMapping("addshoppingcart")
    @ResponseBody
    public ResultUtils addShoppingCart(ShopCart shopCart) {

        return shopCartService.insertShopCar(shopCart);
    }


    @RequestMapping("deleteshopcar")
    @ResponseBody
    public ResultUtils deleteShopCar( @RequestParam Integer userId, String shopCartIdList) {
        List<Long> ling=new ArrayList<>();
        String[] split = shopCartIdList.split(",");
        for (String s:split) {
            long l = Long.parseLong(s);
            ling.add(l);
        }
        Long[] objects = ling.toArray(new Long[]{});
        return shopCartService.deleteShopCar( userId,Arrays.asList(objects));
    }

    @RequestMapping("addproducenum")
    @ResponseBody
    public ResultUtils addProduceNum(ShopCart shopCarts) {
        return shopCartService.addProduceNum(shopCarts);
    }


    @RequestMapping("findall")
    @ResponseBody
    public ResultUtils findAll(Integer userId) {

        return shopCartService.findAll(userId);
    }

}
