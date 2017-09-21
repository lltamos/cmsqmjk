package com.quanmin.service;

import com.quanmin.model.BannerWithBLOBs;

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
     * @param bannerWithBLOBs
     * @return
     */

    public int saveBannerInfo(BannerWithBLOBs bannerWithBLOBs) ;
}
