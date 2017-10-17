package com.quanmin.service.impl;

import com.quanmin.dao.mapper.BannerMapper;
import com.quanmin.model.BannerExample;
import com.quanmin.model.BannerWithBLOBs;
import com.quanmin.service.APPBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: By heasy.
 * @Date: 2017/9/26.
 * @Contcat: yz972641975@gmail.com.
 * @Description:
 * @Modified By:
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class APPBannerServiceImpl implements APPBannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Override
    public List<BannerWithBLOBs> listBanner() {

        BannerExample bannerExample = new BannerExample();
        BannerExample.Criteria criteria = bannerExample.createCriteria();
        criteria.andDelStatusEqualTo(0);
        criteria.andBannerStatusEqualTo(1);
       return bannerMapper.selectByExampleWithBLOBs(bannerExample);
    }
}
