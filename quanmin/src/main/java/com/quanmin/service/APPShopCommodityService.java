package com.quanmin.service;

import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.jpapo.ShopCollection;
import com.quanmin.model.jpapo.ShopCommodity;
import com.quanmin.util.ResultUtils;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/7/25.
 */
public interface APPShopCommodityService {


    /**
     * 根据条件查询
     * @param searchCondition
     * @return
     */
    ResultUtils findBySearchkeyCondition(SearchCondition searchCondition);

    /**
     * 根据类型查询
     * @param searchCondition
     * @return
     */
    ResultUtils findByTypeCondition(SearchCondition searchCondition);

    /**
     * 根据商品id查询商品详细内容
     * @param commodityId
     * @return
     */
    Map<String,Object> findByCommodityId(Long commodityId,Integer userId);

    /**
     * 用户收藏
     * @param shopCollection
     * @return
     */
    int userSaveOrCanncelShopCollection(ShopCollection shopCollection);

    /**
     * 根据商品编码查询
     * @param number
     * @return
     */
    Map<String,Object> findByNumberCondition(String number);

    /**
     * 推荐列表
     * @return
     */
    List<ShopCommodity> systemRecommend();
}
