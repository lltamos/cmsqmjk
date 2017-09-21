package com.quanmin.dao.jpa;

import com.quanmin.model.jpapo.ShopCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ShopCartDao
 *
 * @author llsmp
 * @date 2017/7/25
 */
public interface ShopCartDao extends JpaRepository<ShopCart,Long> {

    List<ShopCart> findByUserIdEqualsAndDelStatusEquals(Integer userId, Integer delStatus, Sort sort);

    void deleteByUserIdAndIdEquals(Integer userId,Long cid);

    ShopCart findByUserIdEqualsAndCommodityIdEquals(Integer userId, Long commodityId);
}
