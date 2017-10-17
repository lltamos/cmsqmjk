package com.quanmin.service.impl;

import com.quanmin.dao.jpa.ShopReceiptAddressDao;
import com.quanmin.model.jpapo.ShopReceiptAddress;
import com.quanmin.service.IShopReceiptAddressService;
import com.quanmin.util.ResultUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * ShopReceiptAddressService
 *
 * @author llsmp
 * @date 2017/7/26
 */
@Service
@Transactional
public class ShopReceiptAddressServiceImpl implements IShopReceiptAddressService {

    @Resource
    private ShopReceiptAddressDao shopReceiptAddressDao;


    @Override
    public ResultUtils addAddress(ShopReceiptAddress receiptAddress) {
        if (receiptAddress == null || receiptAddress.getUserId() == null || receiptAddress.getReceiptPhone() == null || receiptAddress.getReceiptName() == null || receiptAddress.getReceiptAddress() == null) {
            return ResultUtils.returnException();
        }

        if (receiptAddress.getId() != null) {
            return ResultUtils.returnFail("已存在不能新增");
        }

        if (receiptAddress.getArea() == null) {
            return ResultUtils.returnException();
        }

        if (receiptAddress.getType() == 0) {
            List<ShopReceiptAddress> defaultAdress=shopReceiptAddressDao.findByUserIdEqualsAndTypeEquals(receiptAddress.getUserId(), 0);
            if (defaultAdress.size() > 0) {
                defaultAdress.get(0).setType(1);
                defaultAdress.get(0).setUpdateTime(new Date());
            }
        } else {
            receiptAddress.setType(1);
        }
        receiptAddress.setCreateTime(new Date());
        receiptAddress.setUpdateTime(new Date());

        ShopReceiptAddress address=shopReceiptAddressDao.save(receiptAddress);
        return ResultUtils.returnSucess(address);
    }



    @Override
    public ResultUtils updateAddress(ShopReceiptAddress receiptAddress) {
        if (receiptAddress == null || receiptAddress.getId() == null || receiptAddress.getUserId() == null || receiptAddress.getReceiptPhone() == null || receiptAddress.getReceiptName() == null || receiptAddress.getReceiptAddress() == null) {
            return ResultUtils.returnException();
        }

        if (receiptAddress.getType() == 0) {
            List<ShopReceiptAddress> defaultAdress=shopReceiptAddressDao.findByUserIdEqualsAndTypeEquals(receiptAddress.getUserId(), 0);
            if (defaultAdress.size() > 0) {
                defaultAdress.get(0).setType(1);
                defaultAdress.get(0).setUpdateTime(new Date());
            }
        }

        if (receiptAddress.getArea() == null) {
            return ResultUtils.returnException();
        }

        ShopReceiptAddress address=shopReceiptAddressDao.findOne(receiptAddress.getId());
        address.setReceiptAddress(receiptAddress.getReceiptAddress());
        address.setReceiptPhone(receiptAddress.getReceiptPhone());
        address.setReceiptName(receiptAddress.getReceiptName());
        address.setType(receiptAddress.getType());
        address.setArea(receiptAddress.getArea());
        address.setUpdateTime(new Date());
        return ResultUtils.returnSucess(address);
    }

    @Override
    public ResultUtils deleteAddress(Integer userId, Long[] longs) {
        if (longs == null || longs.length <= 0) {
            return ResultUtils.returnException();
        }
        for (Long l : longs) {
            shopReceiptAddressDao.deleteAllByUserIdAndIdEquals(userId, l);
        }
        return ResultUtils.returnSucess(null);
    }

    @Override
    public ResultUtils findAll(Integer userId) {

        if (userId == null) {
            return ResultUtils.returnFail();
        }

        ShopReceiptAddress receiptAddress=new ShopReceiptAddress();
        receiptAddress.setUserId(userId);
        Example example=Example.of(receiptAddress);

        List<ShopReceiptAddress> addressList=shopReceiptAddressDao.findAll(example);

        if (addressList == null || addressList.size() < 1) {
            return ResultUtils.returnFail();
        } else {
            return ResultUtils.returnSucess(addressList);
        }
    }
}
