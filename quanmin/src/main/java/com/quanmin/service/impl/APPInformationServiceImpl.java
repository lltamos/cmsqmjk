package com.quanmin.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.quanmin.dao.mapper.CollectionMapper;
import com.quanmin.dao.mapper.InformationMapper;
import com.quanmin.model.CollectionExample;
import com.quanmin.model.CollectionExample.Criteria;
import com.quanmin.model.Information;
import com.quanmin.model.custom.LableInformation;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.APPInformationService;
import com.quanmin.util.ResultUtils;

@SuppressWarnings("ALL")
@Service
public class APPInformationServiceImpl implements APPInformationService {

    @Autowired
    private InformationMapper informationMapper;

    @Autowired
    private CollectionMapper collectionMapper;


    @Override
    public ResultUtils showIndexInformation(SearchCondition searchCondition) {
        ResultUtils result = new ResultUtils();
        PageHelper.startPage(searchCondition.getPage(), searchCondition.getSize());
        // 根据条件查询资讯信息
        List<LableInformation> list = informationMapper.showIndexInformation(searchCondition);
        // 总条数
//		Integer count = informationMapper.showIndexInformationAllCount(searchCondition);
        if (null != list && list.size() > 0) {
            result.setMsg("查询成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setCount(((Page) list).getPages());
            result.setValue(list);
            return result;
        }
        result.setMsg("查询失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Deprecated
    @Override
    public ResultUtils showInformationdetail(SearchCondition searchCondition) {
        ResultUtils result = new ResultUtils();
        // 根据条件查询资讯信息
        List<LableInformation> list = informationMapper.showIndexInformationDetail(searchCondition);
        if (null != list && list.size() > 0) {
            LableInformation lableInformation = list.get(0);
            Information record = new Information();
            record.setId(Integer.parseInt(searchCondition.getInformationId()));
            Integer scannum = lableInformation.getScannum() + 1;
            record.setScannum(scannum);
            // 修改浏览次数
            informationMapper.updateByPrimaryKeySelective(record);

            // 查询用户的收藏状态

            CollectionExample example = new CollectionExample();
            Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(searchCondition.getId());
            criteria.andInformationIdEqualTo(Integer.parseInt(searchCondition.getInformationId()));
            criteria.andDelStatusEqualTo(0);
            List<com.quanmin.model.Collection> collectionList = collectionMapper.selectByExample(example);

            if (null != collectionList && collectionList.size() > 0) {
                lableInformation.setCollectionStatus(0);
            } else {
                lableInformation.setCollectionStatus(1);
            }
            result.setMsg("查询成功");
            result.setResultCode("200");
            result.setSuccess(true);
            result.setValue(lableInformation);
            return result;
        }
        result.setMsg("查询失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }

    @Override
    public ResultUtils showInformationListByCondition(SearchCondition searchCondition) {
        ResultUtils result = new ResultUtils();

        HashMap<String, Object> map = new HashMap<>();
        // 根据type进行查询精选条目
        LableInformation lableInfo = informationMapper.selectFeaturedByCondition(searchCondition);
        if (null != lableInfo) {
            PageHelper.startPage(searchCondition.getPage(), searchCondition.getSize(), true);
            searchCondition.setInformationId(lableInfo.getId() + "");
            // 根据条件查询资讯信息
            List<LableInformation> list = informationMapper.showInformationListByCondition(searchCondition);
            if (null != list && list.size() > 0) {
                map.put("featured", lableInfo);
                map.put("list", list);
                result.setMsg("查询成功");
                result.setResultCode("200");
                result.setSuccess(true);
                result.setCount(((Page) list).getPages());
                result.setValue(map);
                return result;
            }
        }
        result.setMsg("查询失败");
        result.setResultCode("500");
        result.setSuccess(false);
        result.setValue("");
        return result;
    }


    /**
     * @param searchCondition
     * @return
     * @since 2.2
     */
    @Override
    public LableInformation getInformationdetail(SearchCondition searchCondition) {
        // 根据条件查询资讯信息
        List<LableInformation> list = informationMapper.showIndexInformationDetail(searchCondition);
        List<com.quanmin.model.Collection> collectionList = null;
        if (null != list && list.size() > 0) {
            LableInformation lableInformation = list.get(0);
            Information record = new Information();
            record.setId(Integer.parseInt(searchCondition.getInformationId()));
            Integer scannum = lableInformation.getScannum() + 1;
            record.setScannum(scannum);
            // 修改浏览次数
            informationMapper.updateByPrimaryKeySelective(record);

            // 查询用户的收藏状态
            if (null != searchCondition.getUserId() && !"".equals(searchCondition.getUserId())) {
                CollectionExample example = new CollectionExample();
                Criteria criteria = example.createCriteria();
                criteria.andUserIdEqualTo(Integer.parseInt(searchCondition.getUserId()));
                criteria.andInformationIdEqualTo(Integer.parseInt(searchCondition.getInformationId()));
                criteria.andDelStatusEqualTo(0);
                collectionList = collectionMapper.selectByExample(example);
            }
            if (null != collectionList && collectionList.size() > 0) {
                lableInformation.setCollectionStatus(0);
            } else {
                lableInformation.setCollectionStatus(1);
            }
            return lableInformation;
        }
        return null;
    }

}
