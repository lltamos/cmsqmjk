package com.quanmin.dao.jpa;

import com.quanmin.model.jpapo.ShopReceiptAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ShopOrderDao
 *
 * @author llsmp
 * @date 2017/7/25
 */
@Repository
public interface ShopReceiptAddressDao extends JpaRepository<ShopReceiptAddress,Long> {

    List<ShopReceiptAddress> findByUserIdEqualsAndTypeEquals(Integer uid,Integer fromType);

    void deleteAllByUserIdAndIdEquals(Integer userId ,Long id);

    List<ShopReceiptAddress> findByUserIdEquals(Integer uid);


}
