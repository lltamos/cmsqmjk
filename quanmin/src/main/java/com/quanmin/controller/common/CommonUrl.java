package com.quanmin.controller.common;

import com.quanmin.util.LoadPropertiesDataUtils;
import com.quanmin.util.ResultUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: By heasy.
 * @Date: 2017/10/11.
 * @Contcat: yz972641975@gmail.com.
 * @Description: 获取请求的url地址
 * @Modified By:
 */
@RestController
@RequestMapping("/common")
public class CommonUrl {

    @RequestMapping("/getrequesturl")
    public ResultUtils getRequestUrl() {
        return ResultUtils.returnSucess(LoadPropertiesDataUtils.getValue("request.url"));
    }
}
