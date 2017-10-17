package com.quanmin.filter;

import com.alibaba.fastjson.JSON;
import com.quanmin.dao.mapper.SysLogMapper;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.dao.mapper.TokenMapper;
import com.quanmin.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class CommFilter implements Filter {



    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        System.out.println("请求路径:" + uri);
        System.out.println("请求参数列表："+ JSON.toJSON(request.getParameterMap()));
        request.setCharacterEncoding("utf-8");

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
