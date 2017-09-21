package com.quanmin.service.impl;

import com.quanmin.dao.jpa.ShopCartDao;
import com.quanmin.dao.jpa.ShopCommodityDao;
import com.quanmin.model.jpapo.ShopCart;
import com.quanmin.model.jpapo.ShopCommodity;
import com.quanmin.model.po.ShopCartPo;
import com.quanmin.service.IShopCartService;
import com.quanmin.util.Commons;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.SortUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ShopCartServiceImpl
 *
 * @author llsmp
 * @date 2017/7/25
 */
@Service
@Transactional
public class ShopCartServiceImpl implements IShopCartService {

    @Resource
    private ShopCartDao shopCartDao;

    @Resource
    private ShopCommodityDao commodityDao;

    @Override
    public ResultUtils insertShopCar(ShopCart shopCart) {


        if (shopCart.getAmount() == null || shopCart.getCommodityId() == null || shopCart.getUserId() == null || shopCart.getType() == null) {
            return ResultUtils.returnFail(Commons.DATA_EXCPTION_STR);
        }

        Long commodityId=shopCart.getCommodityId();
        ShopCart cart=shopCartDao.findByUserIdEqualsAndCommodityIdEquals(shopCart.getUserId(), shopCart.getCommodityId());

        if (cart != null) {
            cart.setAmount(cart.getAmount() + shopCart.getAmount());
            cart.setUpdateTime(new Date());
            return ResultUtils.returnSucess(cart);
        }
        shopCart.setCreateTime(new Date());
        shopCart.setDelStatus(0);
        shopCart.setUpdateTime(new Date());
        cart=shopCartDao.save(shopCart);
        if (cart == null) {
            return  ResultUtils.returnFail(Commons.DATA_ERROR_STR);
        }
        return ResultUtils.returnSucess(cart);
    }

    @Override
    public ResultUtils deleteShopCar(Integer userId, List<Long> shopCartsId) {

        if (shopCartsId == null) {
            return ResultUtils.returnFail(Commons.DATA_EXCPTION_STR);
        }
        for (Long shopCart : shopCartsId) {

            if (shopCart == null) continue;

            try {
                shopCartDao.deleteByUserIdAndIdEquals(userId, shopCart);

            } catch (Exception e) {

                return ResultUtils.returnFail(Commons.DATA_ERROR_STR);
            }


        }
        return ResultUtils.returnSucess();


    }

    /**
     * @param mapList ->pid,offset
     * @return
     */
    @Override
    public ResultUtils addProduceNum(ShopCart mapList) {
        if (mapList == null)
            return ResultUtils.returnFail(Commons.DATA_EXCPTION_STR);
        Long pid=mapList.getId();
        Integer offset=mapList.getAmount();

        if (pid == null && offset == null && offset > 0)
            return ResultUtils.returnFail("请输入正确数量");
        ShopCart shopCart=shopCartDao.findOne(pid);
        if (shopCart==null)
            return ResultUtils.returnFail();
        shopCart.setAmount(offset);
        shopCart.setUpdateTime(new Date());
        return ResultUtils.returnSucess();
    }

    @Override
    public ResultUtils findAll(Integer userId) {

        List<ShopCart> shopCartPages=shopCartDao.findByUserIdEqualsAndDelStatusEquals(userId, 0, SortUtils.DESCCreateTime());
        List<ShopCartPo> dataList=new ArrayList<>();
        if (shopCartPages != null) {
            for (ShopCart stm : shopCartPages) {
                Long commodityId=stm.getCommodityId();
                ShopCommodity commodity=commodityDao.findOne(commodityId);
                ShopCartPo spo=new ShopCartPo();
                BeanUtils.copyProperties(commodity, spo);

                spo.setAmount(stm.getAmount());
                spo.setShopCartId(stm.getId());
                dataList.add(spo);
            }

        }
        return ResultUtils.returnSucess(dataList);
    }


}
