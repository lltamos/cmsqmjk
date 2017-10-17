package com.quanmin.service;

import com.quanmin.util.ResultUtils;

/**
 * @Author: By heasy.
 * @Date: 2017/9/25.
 * @Contcat: yz972641975@gmail.com.
 * @Description: 第三方推荐
 * @Modified By:
 */
public interface RecommendService {

     Integer insertCode(String phone, int code);

    ResultUtils applyRegister(String referee, String referral, String code);

}
