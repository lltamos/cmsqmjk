package com.quanmin.util;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quanmin.dao.mapper.TokenMapper;
import com.quanmin.model.SysLog;
import com.quanmin.model.Token;
import com.quanmin.model.TokenExample;
import com.quanmin.model.TokenExample.Criteria;

@Component
public class ResquestAndResponseParmUtils {

	@Autowired
	private static TokenMapper tokenMapper;
	
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
		TreeMap<String, Object> parameterMap = new TreeMap<String, Object>();
		parameterMap.putAll(request.getParameterMap());

		StringBuffer params = new StringBuffer();
		for (String key : parameterMap.keySet()) {
			params.append(key).append("=").append(request.getParameter(key)).append("&");
		}

		TokenExample example = new TokenExample();
		Criteria criteria = example.createCriteria();
		criteria.andTokenEqualTo(token);
		List<Token> list = tokenMapper.selectByExample(example);

		// 用户手机类型
		String phoneType = request.getParameter("phoneType");
		// 用户version
		String version = request.getParameter("version");

		SysLog log = new SysLog();
		log.setAppName("全民健康");
		log.setCreateTime(new Date());
		log.setLogType(0);
		log.setMethodName(method);
		log.setPhonetype("0".equals(phoneType) ? "ios" : "Android");
		log.setRequestIp(ip);
		log.setRequestParams(params.toString());
		log.setRequestUri(requestUri);
		if (list.size() > 0) {
            log.setUserId(list.get(0).getUserId() != null ? list.get(0).getUserId() + "" : "");
        }
		log.setVersionId(version);
		return log;
	}

	public String getExceptionAllinformation(Exception ex) {
		String sOut = "";
		StackTraceElement[] trace = ex.getStackTrace();
		for (StackTraceElement s : trace) {
			sOut += "\tat " + s + "\r\n";
		}
		return sOut;
	}
}
