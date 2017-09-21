package com.quanmin.service;

import com.quanmin.model.SysArea;
import com.quanmin.model.jpapo.ShopDict;

import java.util.List;

/**
 * Created by yang on 2017/7/26.
 */
public interface CommonDictService {
    /**
     * 获取所有的规格列表
     * @return
     */
    List<ShopDict> shopDictList(Integer type,String commodityNumber);

    /**
     * 查询省
     * @return
     */
    List<SysArea> findProvinceList(Integer layer);

    /**
     * 查询市
     * @param provinceId
     * @return
     */
    List<SysArea> findCityByProvinceId(Integer provinceId);

    /**
     * 保存标签，颜色，或者大小
     * @param shopDict
     * @return
     */
    ShopDict saveShopDict(ShopDict shopDict );

    /**
     * 根据标签查看是否付存在
     * @param shopDict
     * @return
     */
    ShopDict findByCommodityIdAndNameCondition(ShopDict shopDict);
}
