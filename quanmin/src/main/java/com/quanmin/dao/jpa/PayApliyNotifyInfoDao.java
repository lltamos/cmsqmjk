package com.quanmin.dao.jpa;

import com.quanmin.model.jpapo.PayApliyNotifyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayApliyNotifyInfoDao extends JpaRepository<PayApliyNotifyInfo,Long> {
    PayApliyNotifyInfo findByOutTradeNoEquals(String outtradeno);

}
