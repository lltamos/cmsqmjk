package com.quanmin.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quanmin.service.APPVersionService;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;

/**
 * 版本管理
 *
 * @author heasy
 */
@Controller
@RequestMapping(value = "/api/1/")
public class APPVersionController {

    @Autowired
    private APPVersionService versionService;

    /**
     * 获取安卓最新版本
     *
     * @return
     */
    @RequestMapping(value = "/getversion")
    @ResponseBody
    public ResultUtils getversion(@RequestParam("phoneType") String type) {
        ResultUtils result = versionService.getversion(type);
        return result;
    }

    /**
     * 获取IOS最新版本
     *
     * @return
     */
    @RequestMapping(value = "/getisoversion")
    @ResponseBody
    public ResultUtils getIOSVersion(HttpServletRequest request,
                                     HttpServletResponse httpServletResponse) {

        // 用户version
        String version = request.getParameter("version");

        ResultUtils result = versionService.getIOSVersion(version);
        return result;
    }
}
