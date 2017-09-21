package com.quanmin.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang on 2017/7/12.
 * 全局异常处理器
 */

public class DefaultExceptionHandler implements HandlerExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ModelAndView mv = new ModelAndView();
            /*  使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常   */
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("code", "1000000");
        attributes.put("msg", "请联系佳老板，佳老板请撸串，撸串解决一切问题！！！");
        attributes.put("status", ex.getLocalizedMessage());
        view.setAttributesMap(attributes);
        mv.setView(view);
        log.debug("异常:" + ex.getMessage(), ex);
        return mv;
    }
}
