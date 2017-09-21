package com.quanmin.service.impl;

import com.quanmin.dao.mapper.BannerMapper;
import com.quanmin.model.BannerWithBLOBs;
import com.quanmin.service.CMSBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: By heasy.
 * @Date: 2017/9/21.
 * @Contcat: yz972641975@gmail.com.
 * @Description:
 * @Modified By:
 */
@Service
public class CMSBannerServiceImpl implements CMSBannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Override
    public int saveBannerInfo(BannerWithBLOBs bannerWithBLOBs) {
        int i = bannerMapper.insertSelective(bannerWithBLOBs);
        return i;
    }



}
