package com.quanmin.controller.app;

import com.quanmin.model.BannerWithBLOBs;
import com.quanmin.service.APPBannerService;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: By heasy.
 * @Date: 2017/9/26.
 * @Contcat: yz972641975@gmail.com.
 * @Description: banner管理
 * @Modified By:
 */
@RestController
@RequestMapping("api/1/")
public class APPBannerController {
    @Autowired
    private APPBannerService appBannerService;

    /**
     * 展示banner
     * @return
     */
    @RequestMapping(value = "/listbanner")
    public ResultUtils listBanner() {
        List<BannerWithBLOBs> bannerWithBLOBslist = appBannerService.listBanner();
        return null != bannerWithBLOBslist && bannerWithBLOBslist.size() > 0 ? ResultUtils.returnSucess(bannerWithBLOBslist) : ResultUtils.returnFail();
    }


}
