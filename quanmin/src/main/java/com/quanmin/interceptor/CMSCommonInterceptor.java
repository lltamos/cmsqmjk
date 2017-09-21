package com.quanmin.interceptor;

import com.quanmin.dao.mapper.SysLogMapper;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.dao.mapper.TokenMapper;
import com.quanmin.model.SysLog;
import com.quanmin.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

/**
 * 自定义拦截器,用户过滤cmstoken，记录日志
 *
 * @author heasy
 */
public class CMSCommonInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysLogMapper sysLogMapper;

    @Autowired
    private JedisPool jedisPool;

    private final Logger log = LoggerFactory.getLogger(CMSCommonInterceptor.class);

    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 获取请求路径
        String requestUri = request.getRequestURI();
//获取登录的token
        String token = request.getHeader("Accept-CMS-Token");


        if (!request.getMethod().equals("OPTIONS")) {
            //保存日志
            saveLog(request, response);
        }

        if (requestUri.equals("/cms/1/login")) {
            return true;
        } else {
            Jedis jedis = jedisPool.getResource();
            String cmsUserToken = jedis.get("cmsUserToken" + token);
            if (null != cmsUserToken) {
                jedis.close();
                return true;
            }
            ResultUtils result = new ResultUtils();
            result.setMsg("请登录");
            result.setResultCode("515");
            result.setSuccess(true);
            result.setValue("");
            String json = JsonUtils.objectToJson(result);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(json);
            writer.close();
            jedis.close();
            return false;
        }
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     * <p>
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//		log.info("==============出异常啦！程序员哥哥================");
    }


    /**
     * 保存日志
     *
     * @param request
     * @param response
     */
    private void saveLog(HttpServletRequest request, HttpServletResponse response) throws IOException {


        // 获取请求路径
        String requestUri = request.getRequestURI();


        // 记录日志
        SysLog sysLog = BaseRQAndQSParmUtils.getSysLog(request, response);
        String token = request.getHeader("Accept-CMS-Token");
        Jedis jedis = jedisPool.getResource();
        String redisTokens = jedis.get(null != token ? "cmsUserToken" + token : "");

        System.out.println("redisTokens:" + redisTokens);

        if (!StringUtil.isEmpty(redisTokens)) {
            String[] strings = redisTokens.split(",");
            if (strings.length > 1) {
                sysLog.setUserId(strings[1]);
            }
        }

        TreeMap<String, String> treeMap = IntefaceExplanationCompared.cmsIntefaceExplanationCompared();

        if (treeMap.containsKey(requestUri)) {
            sysLog.setMethodDescription(treeMap.get(requestUri));
        }
        sysLog.setType("2");
        int insertSelective = sysLogMapper.insertSelective(sysLog);
        jedis.close();
    }


}