package com.quanmin.service.impl;

import com.quanmin.dao.jpa.ShopDictDao;
import com.quanmin.model.jpapo.ShopDict;
import com.quanmin.service.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {
    @Resource
    private ShopDictDao shopDictDao;

    @Override
    public List<ShopDict> shopDictList(Integer type) {
        return shopDictDao.findByTypeEqualsAndDelStatusEquals(type, 0);
    }
}
