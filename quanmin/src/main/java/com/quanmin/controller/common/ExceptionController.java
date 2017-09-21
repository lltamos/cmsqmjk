package com.quanmin.controller.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yang on 2017/7/12.
 * 异常处理
 */
@RestController
public class ExceptionController {
    /**
     * 请求异常
     *
     * @return
     * @throws Exception String
     */
    @RequestMapping(value = "/error_404")
    public String error_404() throws Exception {

        return "{\"msg\":\"请求地址错误，找不到页面\",\"code\":\"1000001\"}";
    }

    /**
     * 服务器异常
     *
     * @return String
     */
    @RequestMapping(value = "/error_500")
    public String error_500() {
        return "{\"msg\":\"服务器处理失败\",\"code\":\"1000002\"}";
    }
}
