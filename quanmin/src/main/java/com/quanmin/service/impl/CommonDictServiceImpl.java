package com.quanmin.service.impl;

import com.quanmin.dao.jpa.ShopDictDao;
import com.quanmin.dao.mapper.SysAreaMapper;
import com.quanmin.model.SysArea;
import com.quanmin.model.SysAreaExample;
import com.quanmin.model.jpapo.ShopDict;
import com.quanmin.service.CommonDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yang on 2017/7/26.
 */
@Service
public class CommonDictServiceImpl implements CommonDictService {
    @Autowired
    private ShopDictDao shopDictDao;

    @Autowired
    private SysAreaMapper sysAreaMapper;

    @Override
    public List<ShopDict> shopDictList(Integer type, String commodityNumber) {
        List<ShopDict> shopCommodityList = null;
        switch (type) {
            case 6:
            case 5:
            case 4:
                shopCommodityList = shopDictDao.findByTypeEqualsAndDelStatusEquals(type, 0);
                break;
            default:
                shopCommodityList = shopDictDao.findByTypeEqualsAndDelStatusEqualsAndCommodityNumberEquals(type, 0, commodityNumber);
        }
        return shopCommodityList;
    }

    @Override
    public List<SysArea> findProvinceList(Integer layer) {
        SysAreaExample sysAreaExample = new SysAreaExample();
        SysAreaExample.Criteria criteria = sysAreaExample.createCriteria();
        criteria.andLayerEqualTo(layer);
        List<SysArea> sysAreas = sysAreaMapper.selectByExample(sysAreaExample);
        return sysAreas;
    }

    @Override
    public List<SysArea> findCityByProvinceId(Integer provinceId) {
        SysAreaExample areaExample = new SysAreaExample();
        SysAreaExample.Criteria criteria = areaExample.createCriteria();
        criteria.andParentIdEqualTo(provinceId);
        return sysAreaMapper.selectByExample(areaExample);
    }

    @Override
    public ShopDict saveShopDict(ShopDict shopDict) {
        shopDict.setCreateTime(new Date());
        shopDict.setDelStatus(0);
        return shopDictDao.save(shopDict);
    }

    @Override
    public ShopDict findByCommodityIdAndNameCondition(ShopDict shopDict) {
        ShopDict one = shopDictDao.findByCommodityNumberEqualsAndDelStatusEqualsAndNameEquals(shopDict.getCommodityNumber(), 0, shopDict.getName());
        return one;
    }

}
