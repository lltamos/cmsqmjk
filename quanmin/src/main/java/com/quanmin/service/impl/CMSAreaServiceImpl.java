package com.quanmin.service.impl;

import com.quanmin.dao.mapper.SysAreaMapper;
import com.quanmin.model.SysArea;
import com.quanmin.model.SysAreaExample;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.CMSAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang on 2017/6/27.
 */
@Service
public class CMSAreaServiceImpl implements CMSAreaService {
    @Autowired
    private SysAreaMapper sysAreaMapper;
    @Override
    public List<SysArea> getAreaListAll(SearchCondition condition) {
        SysAreaExample example = new SysAreaExample();
        SysAreaExample.Criteria criteria = example.createCriteria();
        criteria.andLayerEqualTo(0);
        List<SysArea> sysAreas = sysAreaMapper.selectByExample(example);

        return sysAreas;
    }
}
