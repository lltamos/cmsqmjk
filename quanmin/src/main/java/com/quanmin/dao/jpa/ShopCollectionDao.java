package com.quanmin.dao.jpa;

import com.quanmin.model.jpapo.ShopCart;
import com.quanmin.model.jpapo.ShopCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ShopCartDao
 *
 * @author llsmp
 * @date 2017/7/25
 */
public interface ShopCollectionDao extends JpaRepository<ShopCollection,Long> {

    ShopCollection findByUserIdEqualsAndCommodityIdEqualsAndDelStatusEquals(Integer userId,Long commodityId,Integer status);

    ShopCollection findByUserIdEqualsAndCommodityIdEquals(Integer userId,Long commodityId);

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    List<ShopCollection> findByUserIdEquals(Integer userId);
}
