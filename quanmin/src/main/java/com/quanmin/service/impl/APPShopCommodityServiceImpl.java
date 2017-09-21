package com.quanmin.service.impl;

import com.github.pagehelper.PageHelper;
import com.quanmin.dao.jpa.ShopCollectionDao;
import com.quanmin.dao.jpa.ShopCommodityDao;
import com.quanmin.dao.jpa.ShopDictDao;
import com.quanmin.dao.mapper.ShopCommodityMapper;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.model.jpapo.ShopCollection;
import com.quanmin.model.jpapo.ShopCommodity;
import com.quanmin.model.jpapo.ShopDict;
import com.quanmin.service.APPShopCommodityService;
import com.quanmin.util.Commons;
import com.quanmin.util.RedisUtils;
import com.quanmin.util.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by yang on 2017/7/25.
 */
@Service
public class APPShopCommodityServiceImpl implements APPShopCommodityService {
    @Autowired
    private ShopCommodityDao shopCommodityDao;

    @Autowired
    private ShopCommodityMapper shopCommodityMapper;

    @Autowired
    private ShopDictDao shopDictDao;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ShopCollectionDao shopCollectionDao;
    @Override
    public ResultUtils findBySearchkeyCondition(SearchCondition searchCondition) {
        ResultUtils resultUtils = new ResultUtils();
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(searchCondition.getPage() - 1, searchCondition.getSize(), true);
        if (!StringUtils.isEmpty(searchCondition.getSearchKey())) {
            redisUtils.sadd(Commons.REDIS_COMMONDITY_USER_SEARCH_HISTORY + searchCondition.getUserId(), searchCondition.getSearchKey());
        }

//        Sort orders = new Sort(new Sort.Order(Sort.Direction.DESC, "createTime"));
//        PageRequest pageable = new PageRequest(searchCondition.getPage() - 1, searchCondition.getSize(), orders);

        List<ShopCommodity> pageList = shopCommodityMapper.findBySearchkey(searchCondition.getSearchKey(), 0, 1 );

        if (null != pageList && pageList.size() > 0) {
            map.put("searchList",pageList);
            map.put("recommedList",new ArrayList<>());
            resultUtils.setValue(map);
            resultUtils.setCount(((com.github.pagehelper.Page) pageList).getPages());
            resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
            resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
            resultUtils.setSuccess(Commons.DATA_TRUE);
            return resultUtils;
        } else {
            map.put("recommedList",systemRecommend());
            map.put("searchList",new ArrayList<>());
            resultUtils.setValue(map);
            resultUtils.setCount(1);
            resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
            resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
            resultUtils.setSuccess(Commons.DATA_FALSE);
            return resultUtils;
        }
    }

    @Override
    public ResultUtils findByTypeCondition(SearchCondition searchCondition) {
        ResultUtils resultUtils = new ResultUtils();
        PageHelper.startPage(searchCondition.getPage() - 1, searchCondition.getSize(), true);
        List<ShopCommodity> pageList = shopCommodityMapper.findByType(searchCondition.getType(), 0, 1);

        if (null != pageList && pageList.size() > 0) {
            resultUtils.setValue(pageList);
            resultUtils.setCount(((com.github.pagehelper.Page) pageList).getPages());
            resultUtils.setResultCode(Commons.DATA_SUCCESS_CODE);
            resultUtils.setMsg(Commons.DATA_SUCCESS_STR);
            resultUtils.setSuccess(Commons.DATA_TRUE);
            return resultUtils;
        } else {
            return ResultUtils.returnFail();
        }
    }

    @Override
    public Map<String, Object> findByCommodityId(Long commodityId, Integer userId) {
        Map<String, Object> map = new HashMap<>();
        ShopCommodity one = shopCommodityDao.findOne(commodityId);
        map.put("shopCommodity", one);
        ShopCollection collection = shopCollectionDao.findByUserIdEqualsAndCommodityIdEqualsAndDelStatusEquals(userId, commodityId, 0);
        map.put("collectionStatus", null != collection ? 1 : 2);
        map.put("shopDescription", findShopDictIds(one.getLableId()));
        map.put("contactPhone", "15210855918");
        return map;
    }


    public List<ShopDict> findShopDictIds(String ids) {

        List<ShopDict> list = new ArrayList<>();
        String[] split = ids.split(",");
        for (int i = 0; i < split.length; i++) {
            list.add(shopDictDao.findOne(Long.valueOf(split[i])));
        }
        return list;
    }

    @Override
    public int userSaveOrCanncelShopCollection(ShopCollection shopCollection) {
        ShopCollection collection = shopCollectionDao.findByUserIdEqualsAndCommodityIdEquals(shopCollection.getUserId(), shopCollection.getCommodityId());
        if (null != collection) {
            shopCollectionDao.delete(collection.getId());
            return 2;
        }
        shopCollection.setCreateTime(new Date());
        shopCollection.setDelStatus(0);
        ShopCollection save = shopCollectionDao.save(shopCollection);
        return null != save ? 1 : 0;
    }


    @Override
    public Map<String, Object> findByNumberCondition(String number) {
        Map<String, Object> map = new HashMap<>();
        List<ShopCommodity> commodityList = shopCommodityDao.findByNumberEqualsAndDelStatusEquals(number, 0, new Sort(Sort.Direction.DESC, "createTime"));
        List<Map<String, Object>> colorSet = new ArrayList<>();
        //型号
        Map<String, Object> modelMap = new HashMap<>();
        HashSet<Map<String, Object>> hs = null;
        //获取型号
        for (ShopCommodity shopCommodity : commodityList) {
            List<Map<String, Object>> colorList = new ArrayList<>();
            Map<String, Object> colorMap = new HashMap<>();
//            根据型号名称，获取颜色
            for (ShopCommodity commodity : commodityList) {
                if (shopCommodity.getModelName().equals(commodity.getModelName())) {
                    colorMap.put(commodity.getColorName(), commodity);
                    colorList.add(colorMap);
                }
            }
            hs = new HashSet<Map<String, Object>>(colorList);
            modelMap.put(shopCommodity.getModelName(), hs);
        }
        if (null != modelMap && modelMap.size() > 0)
            colorSet.add(modelMap);

        map.put("commodityInfo", colorSet);
        return map;
    }

    @Override
    public List<ShopCommodity> systemRecommend() {

        List<ShopCommodity> resultList = new ArrayList<>();

        List<ShopCommodity> all = shopCommodityMapper.findAll(0, 1);

        Set<Integer> set = new HashSet<>();

        //生成五个随机数
        while (true) {
            if (all.size() <= 5) {
                for (Integer i = 0; i < all.size(); i++) {
                    int i1 = new Random().nextInt(all.size());
                    set.add(i1);
                }
                break;
            }
            int i1 = new Random().nextInt(all.size());
            set.add(i1);
            if (set.size() > 5) {
                break;
            }
        }

        for (Integer i : set) {
            resultList.add(all.get(i));
        }

        return resultList;
    }


    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();

        //生成五个随机数
        while (true) {
            int i1 = new Random().nextInt(4);
            set.add(i1);
            if (set.size() > 5) {
                break;
            }
        }
    }

}
