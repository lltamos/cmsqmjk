package com.quanmin.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 配置跨域
 * @author heasy
 *
 */
public class SimpleCORSFilter implements Filter{  

  @Override  
  public void destroy() {  
        
  }  

  @Override  
  public void doFilter(ServletRequest req, ServletResponse res,  
          FilterChain chain) throws IOException, ServletException {  
          HttpServletResponse response = (HttpServletResponse) res;  
          response.setHeader("Access-Control-Allow-Origin","*");  
          response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
          response.setHeader("Access-Control-Max-Age", "3600");  
//          response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
          response.setHeader("Access-Control-Allow-Headers", "Content-Type,Accept-CMS-Token");
          chain.doFilter(req, res);

  }

  @Override  
  public void init(FilterConfig arg0) throws ServletException {  
        
  }  

}  