package com.quanmin.dao.jpa;

import com.quanmin.model.jpapo.ShopOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ListResourceBundle;

/**
 * ShopOrderDao
 *
 * @author llsmp
 * @date 2017/7/25
 */
@Repository
public interface ShopOrderDao extends JpaRepository<ShopOrder, Long> {


    ShopOrder findByOrderNumEquals(String oedernum);

    Page<ShopOrder> findByUserIdEqualsAndDelStatus(Integer userId, Integer delStatus, Pageable pageable);


    Page<ShopOrder> findByUserIdAndOrderStatusEqualsAndDelStatusAndType(Integer userId, Integer orderStatus, Integer delStatus, Integer type, Pageable pageable);

    List<ShopOrder> findByOrderStatusAndTypeEqualsAndDelStatus(Integer orderStatus, Integer type, Integer delStatus);

    Page<ShopOrder> findByOrderStatusEqualsAndTypeEquals(Integer orderStatus, Integer type, Pageable pageable);

    List<ShopOrder> findByOrderStatusEqualsAndTypeEquals(Integer orderStatus, Integer type, Sort sort);

    Page<ShopOrder> findByOrderNumLikeOrUserNickNameLikeOrReceiveNameLikeOrReceivePhoneLike(String orderNum, String userNickName, String receiveName, String receivePhone, Pageable pageable);

    List<ShopOrder> findByOrderNumLikeOrUserNickNameLikeOrReceiveNameLikeOrReceivePhoneLike(String orderNum, String userNickName, String receiveName, String receivePhone, Sort sort);


    @Query("select s from  ShopOrder s where (s.orderNum like ?1 or s.userNickName like ?1 or s.receiveName like ?1 or s.receivePhone like ?1  ) and s.orderStatus=?2 and type =?3")
    Page<ShopOrder> findByOrderStatusAndTypeliked(String querystr, Integer orderStatus, Integer type, Pageable pageable);

    @Query("select s from  ShopOrder s where (s.orderNum like ?1 or s.userNickName like ?1 or s.receiveName like ?1 or s.receivePhone like ?1  ) and s.orderStatus=?2 and type =?3")
    List<ShopOrder> findByOrderStatusAndTypeliked(String querystr, Integer orderStatus, Integer type, Sort sort);


    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    ShopOrder findById(long id);

    /**
     * 查询订单总数
     */
    int countByUserIdAndOrderStatusEqualsAndDelStatusAndType(Integer userId, Integer orderStatus, Integer delStatus, Integer type);

    int countByUserIdEqualsAndDelStatus(Integer userId, Integer delStatus);


}
