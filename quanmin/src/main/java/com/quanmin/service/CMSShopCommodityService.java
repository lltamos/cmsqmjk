package com.quanmin.service;

import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.jpapo.ShopCommodity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/7/25.
 */
public interface CMSShopCommodityService {


    /**
     * 根据商品名称搜索
     *
     * @param name
     * @return
     */

    List<ShopCommodity> findByName(String name,String number);


    /**
     * 保存商品
     * @param shopCommodity
     * @return
     */
    int saveShopCommondity(ShopCommodity shopCommodity,Long commondityId);

    /**
     * 查询商品列表
     * @param searchCondition
     * @return
     */
    Page<ShopCommodity> shopCommondityList(SearchCondition searchCondition);

    /**
     * 商品上架或者下架
     * @param commondityId
     * @param status
     * @return
     */
    int shopCommondityUpOrDown(Long commondityId, Integer status);

    /**
     * 根據商品id查看
     * @param commondityId
     * @return
     */
    Map<String,Object> shopCommonditOne(Long commondityId);

    /**
     * 查询厂商列表
     * @return
     */
    List<String> shopCompanyAll(Integer type);
}
