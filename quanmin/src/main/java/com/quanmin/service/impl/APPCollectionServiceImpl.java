package com.quanmin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.quanmin.dao.jpa.ShopCollectionDao;
import com.quanmin.dao.jpa.ShopCommodityDao;
import com.quanmin.model.jpapo.ShopCollection;
import com.quanmin.model.jpapo.ShopCommodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quanmin.dao.mapper.CollectionMapper;
import com.quanmin.dao.mapper.InformationMapper;
import com.quanmin.model.Collection;
import com.quanmin.model.CollectionExample;
import com.quanmin.model.CollectionExample.Criteria;
import com.quanmin.model.Information;
import com.quanmin.model.LabelInformation;
import com.quanmin.service.APPCollectionService;
import com.quanmin.util.ResultUtils;

@Service
public class APPCollectionServiceImpl implements APPCollectionService {

    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private InformationMapper informationMapper;

    @Autowired
    private ShopCommodityDao shopCommodityDao;

    @Autowired
    private ShopCollectionDao shopCollectionDao;

    @Override
    public ResultUtils SaveOrUpdateCollectionInformation(Integer informationId, Integer userId, String type) {
        ResultUtils result = new ResultUtils();
        Information information = informationMapper.selectByPrimaryKey(informationId);
        // 等于0，新增
        if (type.equals("0")) {
            // 添加到收藏表中
            Collection collection = new Collection();
            collection.setCreateTime(new Date());
            collection.setDelStatus(0);
            collection.setInformationId(informationId);
            collection.setUserId(userId);
            int i = collectionMapper.insertSelective(collection);

            // 消息的收藏信息加1
            Information record = new Information();
            record.setId(informationId);
            record.setCollectionsum(information.getCollectionsum() + 1);
            int j = informationMapper.updateByPrimaryKeySelective(record);
            if (i > 0) {
                result.setMsg("添加或者修改成功");
                result.setResultCode("200");
                result.setSuccess(true);
                result.setValue("");
                return result;
            }
        } else {
            CollectionExample example = new CollectionExample();
            Criteria criteria = example.createCriteria();
            criteria.andInformationIdEqualTo(informationId);
            criteria.andUserIdEqualTo(userId);
            Collection record = new Collection();
            record.setDelStatus(1);
            record.setUpdateTime(new Date());
            int i = collectionMapper.updateByExampleSelective(record, example);
            // 收藏-1
            Information info = new Information();
            info.setId(informationId);
            info.setCollectionsum(information.getCollectionsum() - 1);
            int j = informationMapper.updateByPrimaryKeySelective(info);

            result.setMsg("添加或者修改成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue("");
            return result;
        }
        result.setMsg("添加失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public ResultUtils showCollectionByUserId(Integer userId) {
        ResultUtils result = new ResultUtils();
        List<LabelInformation> list = informationMapper.showCollectionByUserId(userId);
        if (null != list && list.size() > 0) {
            result.setMsg("查询成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue(list);
            return result;
        }
        result.setMsg("查询失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public List<ShopCommodity>  showCollectionCommodityByUserId(Integer userId, Integer type) {

        List<ShopCollection> collectionList = shopCollectionDao.findByUserIdEquals(userId);
        List<Long> ids = new ArrayList<>();
        for (ShopCollection shopCollection : collectionList) {
            ids.add(shopCollection.getCommodityId());
        }
        List<ShopCommodity> commoditiesList = shopCommodityDao.findAllByIdIn(ids);

        return commoditiesList;
    }

}
