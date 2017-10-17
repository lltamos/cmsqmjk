package com.quanmin.service.impl;

import com.github.pagehelper.*;
import com.quanmin.dao.jpa.ShopCommodityDao;
import com.quanmin.dao.jpa.ShopDictDao;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.jpapo.ShopCommodity;
import com.quanmin.model.jpapo.ShopDict;
import com.quanmin.service.CMSShopCommodityService;
import com.quanmin.util.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by yang on 2017/7/25.
 */
@SuppressWarnings("ALL")
@Service
@Transactional
public class CMSShopCommodityServiceImpl implements CMSShopCommodityService {
    @Autowired
    private ShopCommodityDao shopCommodityDao;

    @Autowired
    private ShopDictDao shopDictDao;

    @Override
    public List<ShopCommodity> findByName(String name, String number) {
        if (!StringUtils.isEmpty(number)) {
            return shopCommodityDao.findByNumberEqualsAndDelStatusEqualsAndStatusEquals(number, 0, 1);
        }
        return shopCommodityDao.findByNameEqualsAndDelStatusEqualsAndStatusEquals(name, 0, 1);
    }

    @Override
    public int saveShopCommondity(ShopCommodity shopCommodity, Long commondityId) {
        ShopCommodity save = null;
        shopCommodity.setStatusCreateTime(new Date());
        shopCommodity.setDelStatus(0);
        shopCommodity.setTotalSalesNum(0);

        if (shopCommodity.getType() == 1) {
            shopCommodity.setPublicCommodityHardUrl("/shop/htmlupload/views/harddetails.html");
        } else {
            shopCommodity.setPublicCommodityMedicineUrl("/shop/htmlupload/views/medicinedetails.html");
        }
        switch (shopCommodity.getStatus()) {
            case 1:
                shopCommodity.setCreateTime(new Date());
                break;
            case 2:
                shopCommodity.setUpdateTime(new Date());
                break;
            default:
                break;
        }
        if (null != commondityId) {
            shopCommodity.setId(commondityId);
            shopCommodity.setCreateTime(new Date());
            shopCommodity.setStatusUpdateTime(new Date());
            save = shopCommodityDao.save(shopCommodity);

        } else {
            save = shopCommodityDao.save(shopCommodity);
            if (shopCommodity.getType() == 1) {
                shopDictDao.findOne(shopCommodity.getSizeId()).setCommodityNumber(shopCommodity.getNumber());
                shopDictDao.findOne(shopCommodity.getColorId()).setCommodityNumber(shopCommodity.getNumber());
            }
        }
        return 1;
    }

    @Override
    public Page<ShopCommodity> shopCommondityList(SearchCondition searchCondition) {
        Pageable pageable = new PageRequest(searchCondition.getPage() - 1, searchCondition.getSize(), new Sort(Sort.Direction.DESC, "createTime"));
        ShopCommodity shopCommodity = new ShopCommodity();
        if (!StringUtils.isEmpty(searchCondition.getSearchKey())) {
            shopCommodity.setName(searchCondition.getSearchKey());
        }
        if (null != searchCondition.getCategoryId()) {
            shopCommodity.setCategoryId(searchCondition.getCategoryId());
        }
        if (null != searchCondition.getStatus()) {
            shopCommodity.setStatus(searchCondition.getStatus());
        }
        if (null != searchCondition.getProvinceId()) {
            shopCommodity.setAreaProvinceId(Long.valueOf(searchCondition.getProvinceId()));
        }
        if (!StringUtils.isEmpty(searchCondition.getCompany())) {
            shopCommodity.setCompany(searchCondition.getCompany());
        }
        shopCommodity.setType(searchCondition.getType());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example example = Example.of(shopCommodity, matcher);

        Page<ShopCommodity> pageShopCommodity = shopCommodityDao.findAll(example, pageable);
        return pageShopCommodity;
    }

    @Override
    public int shopCommondityUpOrDown(Long commondityId, Integer status) {
        ShopCommodity one = shopCommodityDao.findOne(commondityId);
        one.setStatus(status == 0 ? 3 : 1);
//        ShopCommodity save = shopCommodityDao.save(one);
        return null != one ? 1 : 0;
    }

    @Override
    public Map<String, Object> shopCommonditOne(Long commondityId) {
        Map<String, Object> map = new HashMap<>();
        ShopCommodity one = shopCommodityDao.findOne(commondityId);
        map.put("shopCommodity", one);

        ShopDict colorShopDict = new ShopDict();
        colorShopDict.setCommodityNumber(one.getNumber());
        colorShopDict.setType(2);
        Example colorExample = Example.of(colorShopDict);
        List<ShopDict> color = shopDictDao.findAll(colorExample);
        map.put("color", color);

        ShopDict sizeShopDict = new ShopDict();
        sizeShopDict.setCommodityNumber(one.getNumber());
        sizeShopDict.setType(3);
        Example sizeExample = Example.of(sizeShopDict);
        List<ShopDict> size = shopDictDao.findAll(sizeExample);
        map.put("size", size);

        return map;
    }

    @Override
    public List<String> shopCompanyAll(Integer type) {
        return shopCommodityDao.shopCompanyAll(type);


    }


}
