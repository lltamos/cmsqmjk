package com.quanmin.dao.jpa;

import com.quanmin.model.jpapo.ShopCommodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/7/25.
 */

@Repository
public interface ShopCommodityDao extends JpaRepository<ShopCommodity, Long> {

     /* 根据名字查询
     * @param name
     * @param status
     * @return
     */
    List<ShopCommodity> findByNameEqualsAndDelStatusEqualsAndStatusEquals(String name, int status,Integer  commodityStatus);



    /**
     * 根据编号
     * @param number
     * @param status
     * @return
     */
    List<ShopCommodity> findByNumberEqualsAndDelStatusEqualsAndStatusEquals(String number, int status,Integer  commodityStatus);

    /**
     * 根据商品编号查询
     * @param number
     * @param status
     * @param Sort
     * @return
     */
    List<ShopCommodity> findByNumberEqualsAndDelStatusEquals(String number, Integer status, Sort Sort);


    List<ShopCommodity> findAllByDelStatusEqualsAndStatus(int status,Integer  commodityStatus);


//查询厂商
    @Query(name="company",value = "SELECT company FROM ShopCommodity  WHERE type=?1 and company IS NOT null  GROUP BY company ")

    List<String> shopCompanyAll(Integer type);


    List<ShopCommodity> findAllByIdIn(List<Long> ids);
}