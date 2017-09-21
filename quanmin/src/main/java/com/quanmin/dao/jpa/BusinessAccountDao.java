package com.quanmin.dao.jpa;

import com.quanmin.model.jpapo.BusinessAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessAccountDao extends JpaRepository<BusinessAccount, Long> {


    @Query("select s from  BusinessAccount s where (s.orderNum like ?1 or s.userNickName like  ?1 or s.userPhone like ?1 or s.refundNum like ?1 ) and s.type=?2")
    Page<BusinessAccount> findByTypeLikes(String queryStr, Integer type, Pageable pageable);

    @Query("select s from  BusinessAccount s where (s.orderNum like ?1 or s.userNickName like  ?1 or s.userPhone like ?1 or s.refundNum like ?1 ) and s.type=?2")
    List<BusinessAccount> findByTypeLikes(String queryStr, Integer type, Sort sort);

}
