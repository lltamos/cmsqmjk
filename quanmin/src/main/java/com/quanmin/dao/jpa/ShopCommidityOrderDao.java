package com.quanmin.dao.jpa;

import com.quanmin.model.jpapo.ShopCommodityOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ShopCartDao
 *
 * @author llsmp
 * @date 2017/7/25
 */
public interface ShopCommidityOrderDao extends JpaRepository<ShopCommodityOrder,Long> {

    List<ShopCommodityOrder> findAllByOrderIdEquals(Long orderId);


    List<ShopCommodityOrder> findByOrderIdIsAndStatusIs(Long orderId, Integer status);

}
