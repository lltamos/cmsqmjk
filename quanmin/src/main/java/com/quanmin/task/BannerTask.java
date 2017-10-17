package com.quanmin.task;

import com.quanmin.service.CMSBannerService;
import com.quanmin.service.impl.CMSBannerServiceImpl;
import com.quanmin.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: By heasy.
 * @Date: 2017/9/26.
 * @Contcat: yz972641975@gmail.com.
 * @Description: 关于banner的定时上线和下线
 * @Modified By:
 */
public class BannerTask  {

    @Autowired
    private CMSBannerService cmsBannerService;

    /**
     * banner的定时上线和下线
     */
    public void synUpOrDownBanner() {
        cmsBannerService.synUpOrDownBanner();
    }

}
