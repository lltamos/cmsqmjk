package com.quanmin.dao.jpa;

import com.quanmin.model.jpapo.ShopRefundRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRefundRecordDao extends JpaRepository<ShopRefundRecord, Long> {


    Page<ShopRefundRecord> findByBackTypeIsAndStatusIs(Integer backType, Integer status, Pageable pageable);

    List<ShopRefundRecord> findByBackTypeIsAndStatusIs(Integer backType, Integer status, Sort sort);


    Page<ShopRefundRecord> findByBackTypeIsAndStatusIsAndAuditStatusIs(Integer backType, Integer status, Integer auditStatus, Pageable pageable);

    @Query("select s from  ShopRefundRecord s where (s.refundNum like ?1 or s.shopOrderNum like ?1 or s.commodityName like ?1 or s.receiveName like ?1 or s.receivePhone like ?1  ) and s.backType =?2 and s.status=?3 and s.auditStatus=?4")
    Page<ShopRefundRecord> findByBackTypeIsAndStatusIsAndAuditStatusIsLikes(String querystr, Integer backType, Integer status, Integer auditStatus, Pageable pageable);


    List<ShopRefundRecord> findByBackTypeIsAndStatusIsAndAuditStatusIs(Integer backType, Integer status, Integer auditStatus, Sort sort);

    @Query("select s from  ShopRefundRecord s where (s.refundNum like ?1 or s.shopOrderNum like ?1 or s.commodityName like ?1 or s.receiveName like ?1 or s.receivePhone like ?1  ) and s.backType =?2 and s.status=?3 and s.auditStatus=?4")
    List<ShopRefundRecord> findByBackTypeIsAndStatusIsAndAuditStatusIsLikes(String querystr, Integer backType, Integer status, Integer auditStatus, Sort sort);


    //退货or退款
    @Query("select s from  ShopRefundRecord s where (s.refundNum like ?1 or s.shopOrderNum like ?1 or s.commodityName like ?1 or s.receiveName like ?1 or s.receivePhone like ?1  ) and s.backType =?2 and s.status=?3")
    Page<ShopRefundRecord> findByReFoundTypeLikes(String querystr, Integer backType, Integer status, Pageable pageable);

    @Query("select s from  ShopRefundRecord s where (s.refundNum like ?1 or s.shopOrderNum like ?1 or s.commodityName like ?1 or s.receiveName like ?1 or s.receivePhone like ?1  ) and s.backType =?2 and s.status=?3")
    List<ShopRefundRecord> findByReFoundTypeLikes(String querystr, Integer backType, Integer status, Sort sort);


    @Query("select s from  ShopRefundRecord s where (s.refundNum like ?1 or s.shopOrderNum like ?1 or s.commodityName like ?1 or s.receiveName like ?1 or s.receivePhone like ?1  ) and s.backType =?2")
    Page<ShopRefundRecord> findByBackTypeIsLikes(String querystr, Integer backType, Pageable pageable);

    @Query("select s from  ShopRefundRecord s where (s.refundNum like ?1 or s.shopOrderNum like ?1 or s.commodityName like ?1 or s.receiveName like ?1 or s.receivePhone like ?1  ) and s.backType =?2")
    List<ShopRefundRecord> findByBackTypeIsLikes(String querystr, Integer backType, Sort sort);


    Page<ShopRefundRecord> findByBackTypeIs(Integer backType, Pageable pageable);

    List<ShopRefundRecord> findByBackTypeIs(Integer backType, Sort sort);

    int countByUserIdIs(Integer backType);

    @Query("select s from  ShopRefundRecord s where  ( s.backType =?1 or  s.backType=?2 ) and s.userId=?3")
    Page<ShopRefundRecord> findByBackTypeList(Integer p1, Integer p2, Integer userId, Pageable pageable);
}
