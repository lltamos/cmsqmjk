package com.quanmin.service.impl;

import com.quanmin.dao.mapper.BannerMapper;
import com.quanmin.model.Banner;
import com.quanmin.model.BannerExample;
import com.quanmin.model.BannerWithBLOBs;
import com.quanmin.model.custom.SearchCondition;
import com.quanmin.service.CMSBannerService;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

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
        if (null != bannerWithBLOBs.getId()) {
            return bannerMapper.updateByPrimaryKeySelective(bannerWithBLOBs);
        } else {
            return bannerMapper.insertSelective(bannerWithBLOBs);
        }
    }

    @Override
    public List<BannerWithBLOBs> listBannerBySearchCondition(SearchCondition condition) {
        List<BannerWithBLOBs> list = bannerMapper.listBannerBySearchCondition(condition);
        return list;
    }

    @Override
    public BannerWithBLOBs getBannerByBannerId(Integer bannerId) {
        BannerWithBLOBs bannerWithBLOBs = bannerMapper.selectByPrimaryKey(bannerId);
        return bannerWithBLOBs;
    }

    @Override
    public int getUpOrDownByBannerId(Integer bannerId, Integer bannerStatus) {
        BannerWithBLOBs bannerRecord = new BannerWithBLOBs();
        bannerRecord.setId(bannerId);
        bannerRecord.setBannerStatus(bannerStatus);
        return bannerMapper.updateByPrimaryKeySelective(bannerRecord);
    }

    @Override
    public void synUpOrDownBanner() {
        BannerExample bannerExample = new BannerExample();
        BannerExample.Criteria criteria = bannerExample.createCriteria();
        criteria.andDelStatusEqualTo(0);
        List<BannerWithBLOBs> bannerList = bannerMapper.selectByExampleWithBLOBs(bannerExample);
        for (BannerWithBLOBs bannerWithBLOBs : bannerList) {

            //获取开始时间
            Date startTime = bannerWithBLOBs.getStartTime();

            //获取结束时间
            Date endTime = bannerWithBLOBs.getEndTime();
            long cypher = endTime.getTime() - startTime.getTime();
            long currentStart = System.currentTimeMillis() - startTime.getTime();

            if (currentStart < 0) {
                System.out.println("还没有到任务时间");
            }
            if (currentStart > 0 && currentStart <= cypher) {
                if (bannerWithBLOBs.getBannerStatus() == 3) {
                    BannerWithBLOBs bannerRecord = new BannerWithBLOBs();
                    bannerRecord.setId(bannerWithBLOBs.getId());
                    bannerRecord.setBannerStatus(1);
                    bannerMapper.updateByPrimaryKeySelective(bannerRecord);
                }
            }
            if (currentStart > 0 && currentStart > cypher) {
                BannerWithBLOBs bannerRecord = new BannerWithBLOBs();
                bannerRecord.setId(bannerWithBLOBs.getId());
                bannerRecord.setBannerStatus(2);
                bannerMapper.updateByPrimaryKeySelective(bannerRecord);
            }
        }
    }



}
