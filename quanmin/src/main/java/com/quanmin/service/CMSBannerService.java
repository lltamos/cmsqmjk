package com.quanmin.service;

import com.quanmin.model.BannerWithBLOBs;
import com.quanmin.model.custom.SearchCondition;

import java.util.List;

/**
 * @Author: By heasy.
 * @Date: 2017/9/21.
 * @Contcat: yz972641975@gmail.com.
 * @Description: banner管理
 * @Modified By:
 */
public interface CMSBannerService {
    /**
     * 保存banner
     *
     * @param bannerWithBLOBs
     * @return
     */

    public int saveBannerInfo(BannerWithBLOBs bannerWithBLOBs);

    /**
     * 根据条件查询banner列表
     *
     * @param condition
     * @return
     */
    List<BannerWithBLOBs> listBannerBySearchCondition(SearchCondition condition);

    /**
     * 根据id查看
     *
     * @param bannerId
     * @return
     */
    BannerWithBLOBs getBannerByBannerId(Integer bannerId);

    /**
     * banner上线或者下线
     *
     * @param bannerId
     * @return
     */
    int getUpOrDownByBannerId(Integer bannerId,Integer bannerStatus);

    /**
     * banner的定时上线和下线
     */
    void synUpOrDownBanner();
}
