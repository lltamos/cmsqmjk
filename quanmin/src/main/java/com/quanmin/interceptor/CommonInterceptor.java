package com.quanmin.interceptor;

import com.quanmin.dao.mapper.SysLogMapper;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.dao.mapper.TokenMapper;
import com.quanmin.model.SysLog;
import com.quanmin.model.TokenExample;
import com.quanmin.model.TokenExample.Criteria;
import com.quanmin.util.BaseRQAndQSParmUtils;
import com.quanmin.util.JsonUtils;
import com.quanmin.util.ResultUtils;
import com.quanmin.util.StringUtil;
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

/**
 * 自定义拦截器,用户过滤token，记录日志
 *
 * @author heasy
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysLogMapper sysLogMapper;

    @Autowired
    private JedisPool jedisPool;

    private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);

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
        // 用户token
        String token = request.getParameter("token");
        // 获取用户手机号
        String phone = request.getParameter("phone");

        //保存日志
        saveLog(request, response);

        if (requestUri.equals("/api/1/getcode")
                || requestUri.equals("generatehtml")
                || requestUri.equals("/api/1/generatecache")
//                || requestUri.equals("/api/1/getversion")
//                || requestUri.equals("/api/1/getisoversion")
                || requestUri.equals("/api/1/loginout")
//                || requestUri.equals("/api/1/showanalyze")
                || requestUri.equals("/api/1/touristslogin")
                || requestUri.equals("/api/1/order/productionorder")
//                || requestUri.contains("/api/1/findbycommodityid")
//                || requestUri.contains("/api/1/showpersoninfo")
//                || requestUri.contains("/api/1/checkcard")
//                || requestUri.contains("/api/1/showindexinformation")
//                || requestUri.contains("/api/1/findbynumber")
//                || requestUri.contains("/api/1/usersaveorcanncelshopcollection")
//                || requestUri.contains("/api/1/cart/addshoppingcart")
//                || requestUri.contains("/api/1/commons/finddictlist")
                || requestUri.contains("/alipay/callbackalipay")
//                || requestUri.contains("/api/1/updatenickname")
                ) {
            return true;
        }
        Jedis jedis = jedisPool.getResource();
        String tokenAndUserIdAndtokenId = jedis.get("tokenAndUserIdAndtokenId" + phone);

        // 如果是登录页面接口
        if (requestUri.equals("/api/1/login")) {
            // 根据用户id查询token是否存在,如果存在，移除这token
            if (!StringUtil.isEmpty(tokenAndUserIdAndtokenId)) {
                String[] tokenAndUserIdAndtokenIds = tokenAndUserIdAndtokenId.split(",");
                if (!StringUtil.isEmpty(tokenAndUserIdAndtokenIds[1])) {
                    TokenExample tokenexample = new TokenExample();
                    Criteria tokenexampleCriteria = tokenexample.createCriteria();
                    tokenexampleCriteria.andUserIdEqualTo(Integer.parseInt(tokenAndUserIdAndtokenIds[1]));
                    int i = tokenMapper.deleteByExample(tokenexample);
                    jedis.del(tokenAndUserIdAndtokenIds[0]);
//                    System.out.println(i);
                    jedis.close();
                    return true;
                }
            }
        } else {
            String redisTokens = jedis.get(null != token ? token : "");
            if (StringUtil.isEmpty(redisTokens)) {
                ResultUtils result = new ResultUtils();
                result.setMsg("您的账号已在其他设备登录，请重新登录");
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
        jedis.close();
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        saveLog(request, response);
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
        String token = request.getParameter("token");
        Jedis jedis = jedisPool.getResource();
        String redisTokens = jedis.get(null != token ? token : "");
        System.out.println("redisTokens:" + redisTokens);

        if (!StringUtil.isEmpty(redisTokens)) {
            String[] strings = redisTokens.split(",");
            if (strings.length > 1) {
                sysLog.setUserId(strings[1]);
            }
        }
        sysLog.setType("1");
        int insertSelective = sysLogMapper.insertSelective(sysLog);
        jedis.close();
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


}