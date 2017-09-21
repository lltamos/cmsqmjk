package com.quanmin.dao.jpa;

import com.quanmin.model.jpapo.ShopDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yang on 2017/7/25.
 */
@Repository
public interface ShopDictDao extends JpaRepository<ShopDict,Long> {

    List<ShopDict> findByTypeEqualsAndDelStatusEquals(Integer type,Integer delStatus);

    List<ShopDict> findByTypeEqualsAndDelStatusEqualsAndCommodityNumberEquals(Integer type,Integer delStatus,String commodityNumber);



    ShopDict findByCommodityNumberEqualsAndDelStatusEqualsAndNameEquals(String commodityNumber,int status,String name);
}
