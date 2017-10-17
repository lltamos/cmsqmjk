package com.quanmin.util;

import com.quanmin.model.SysLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class BaseRQAndQSParmUtils {

    /**
     * 根据请求获取参数
     *
     * @param request
     * @param response
     * @return
     */
    public static SysLog getSysLog(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求uri
        String requestUri = request.getRequestURI();
        // 请求url
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        // 用户token
        String token = request.getParameter("token");
        // 获取用户手机号
        String phone = request.getParameter("phone");
        // 用户id
        String id = request.getParameter("id");
        // ip地址
        String ip = request.getRemoteAddr();
        // 方法类型
        String method = request.getMethod();
        // 请求参数
        SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
        parameterMap.putAll(request.getParameterMap());

        StringBuffer params = new StringBuffer();
        for (String key : parameterMap.keySet()) {
            params.append(key).append("=").append(request.getParameter(key)).append("&");
        }

        // 用户手机类型
        String phoneType = request.getParameter("phoneType");
        // 用户version
        String version = request.getParameter("version");
// 手机型号
        String phoneModel = request.getParameter("phoneModel");
// 系统版本
        String systemVersion = request.getParameter("systemVersion");

        SysLog log = new SysLog();
        log.setPhoneModel(phoneModel);
        log.setSystemVersion(systemVersion);
        log.setAppName("全民健康app");
        log.setCreateTime(new Date());
        log.setLogType(0);
        log.setMethodName(method);
        if (null != phoneType && !"".equals(phoneType)) {
            log.setPhonetype("0".equals(phoneType) ? "ios" : "Android");
        }
        log.setVersionId(version);
        log.setRequestIp(ip);
        log.setRequestParams(params.toString());
        log.setRequestUri(requestUri);

        return log;
    }

}
