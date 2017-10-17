package com.quanmin.service;

import com.quanmin.model.BannerWithBLOBs;

import java.util.List;

/**
 * @Author: By heasy.
 * @Date: 2017/9/26.
 * @Contcat: yz972641975@gmail.com.
 * @Description: banner 管理
 * @Modified By:
 */
public interface APPBannerService {

    /**
     * 展示banner
     * @return
     */
    List<BannerWithBLOBs> listBanner();
}
