package com.quanmin.dao.mapper;

import com.quanmin.model.jpapo.ShopCommodity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yang on 2017/7/25.
 */

@Repository
public interface ShopCommodityMapper extends JpaRepository<ShopCommodity, Long> {

    /**
     * 根据条件查询
     *
     * @param name
     * @param pageable
     * @return
     */
    @Query("select s from  ShopCommodity s where (s.name like CONCAT('%',?1,'%')  ) and s.delStatus=?2 and s.status =?3 group by s.number ")
    Page<ShopCommodity> findByNameContainingAndDelStatusEqualsAndStatusEquals(String name, Integer status, Integer commodityStatus, Pageable pageable);

    List<ShopCommodity> findBySearchkey(@Param("searchKey")String searchKey, @Param("status")Integer status, @Param("commodityStatus")Integer commodityStatus);


    List<ShopCommodity> findAll(@Param("status")int status,@Param("commodityStatus")Integer  commodityStatus);
    /**
     * 根据类型查询
     *
     * @param type
     * @return
     */

    List<ShopCommodity> findByType(@Param("type") Integer type, @Param("status") Integer status, @Param("commodityStatus") Integer commodityStatus);



}