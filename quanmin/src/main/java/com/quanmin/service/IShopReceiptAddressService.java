package com.quanmin.service;

import com.quanmin.model.jpapo.ShopCart;
import com.quanmin.model.jpapo.ShopReceiptAddress;
import com.quanmin.util.ResultUtils;

import java.util.List;

/**
 * IShopReceiptAddressService
 *
 * @author llsmp
 * @date 2017/7/26
 */
public interface IShopReceiptAddressService {

    ResultUtils addAddress( ShopReceiptAddress receiptAddress);

    ResultUtils updateAddress(ShopReceiptAddress receiptAddress);

    ResultUtils deleteAddress(Integer userId, Long[] longs);

    ResultUtils findAll(Integer userId);
}
