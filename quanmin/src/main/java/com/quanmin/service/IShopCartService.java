package com.quanmin.service;

import com.quanmin.model.jpapo.ShopCart;
import com.quanmin.util.ResultUtils;

import java.util.List;

/**
 * ShopCartService
 *
 * @author llsmp
 * @date 2017/7/25
 */
public interface IShopCartService {
    ResultUtils insertShopCar(ShopCart shopCart);

    ResultUtils deleteShopCar(Integer userId,List<Long> shopCartsId);

    ResultUtils addProduceNum(ShopCart mapList);

    ResultUtils findAll(Integer uid) ;

}
