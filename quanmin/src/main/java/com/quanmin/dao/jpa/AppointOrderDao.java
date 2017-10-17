package com.quanmin.dao.jpa;

import com.quanmin.model.jpapo.AppointOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

/**
 * @Author: By llsmpsvn.
 * @Date: 2017/9/12.
 * @Contcat: llsmpsvn@gmail.com.
 * @Description:
 * @Modified By:
 */
public interface AppointOrderDao extends JpaRepository<AppointOrder, Long> {

        Page<AppointOrder> findByUserIdIsAndLifeTypeIn(Integer userId, Collection<Integer> lifeType, Pageable pageable);

        AppointOrder findByOrderNo(String orderNo);

        int countByUserIdIsAndConsultantIdIs(Integer userId, Integer consultantId);

        @Query("select a.city from AppointOrder a group by a.city")
        List<Object[]> findCitys();

        @Query("select a.treatmentHospital from AppointOrder a WHERE a.city=?1 group by a.treatmentHospital order by a.createTime")
        List<Object[]> findHospitalNames(String city);

        @Query("select a.deptName from AppointOrder a WHERE a.city=?1 and a.treatmentHospital=?2 group by a.deptName")
        List<Object[]> finddeptNames(String city,String treatmentHospital);

        @Query("select a from AppointOrder a WHERE a.lifeType=1 and a.orderStatus=?1")
        List<AppointOrder> findNoPays(Integer orderStatus);




}
