package com.quanmin.interceptor;

import com.alibaba.fastjson.JSON;
import com.quanmin.dao.mapper.SysLogMapper;
import com.quanmin.dao.mapper.SysUserMapper;
import com.quanmin.model.SysLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.Beta;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @Author: By heasy.
 * @Date: 2017/9/18.
 * @Contcat: yz972641975@gmail.com.
 * @Description:
 * @Modified By:
 * @TODO
 */
public class LogInterceptor {

    @Autowired
    private SysLogMapper sysLogMapper;

    private final static Log log = LogFactory.getLog(LogInterceptor.class);

    long startTime = 0L;
    long endTime = 0L;

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* com.quanmin.service.*.*(..))")
    public void aspect() {
    }

    /*
     * 配置前置通知,使用在方法aspect()上注册的切入点
     * 同时接受JoinPoint切入点对象,可以没有该参数
     * 在某连接点之前执行的通知，但这个通知不能阻止连接点之前的执行流程（除非它抛出一个异常）。
     */
    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("开始执行方法：" + joinPoint.getSignature().getName());
            SysLog sysLog = new SysLog();
            sysLog.setMethodDescription("111");
            sysLog.setPhoneModel("1");
            sysLogMapper.insertSelective(sysLog);
            log.info("请求的参数为：" + Arrays.toString(joinPoint.getArgs()));
            startTime = Instant.now().toEpochMilli();

        }
    }

    /*配置后置通知,使用在方法aspect()上注册的切入点
    *当某连接点退出的时候执行的通知（不论是正常返回还是异常退出）。
     */
    @After("aspect()")
    public void after(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            endTime = Instant.now().toEpochMilli();
            log.info("执行方法" + joinPoint.getSignature().getName() + "耗时：" + (endTime - startTime) + "ms");
        }
    }

    /*配置环绕通知,使用在方法aspect()上注册的切入点
    *包围一个连接点的通知，如方法调用。这是最强大的一种通知类型。环绕通知可以在方法调用前后完成自定义的行为。它也会选择是否继续执行连接点或直接返回它自己的返回值或抛出异常来结束执行。
    */
    @Around("aspect()")
    public void around(JoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        this.before(joinPoint);
        try {
            Object proceed = ((ProceedingJoinPoint) joinPoint).proceed();
            System.out.println("执行类：" + joinPoint.getSignature().getDeclaringTypeName() + "中的方法：" + joinPoint.getSignature().getName() + "返回数据是：" + JSON.toJSON(proceed));
            this.after(joinPoint);
            long end = System.currentTimeMillis();
            if (log.isInfoEnabled()) {
                log.info("执行类：" + joinPoint.getSignature().getDeclaringTypeName() + "中的方法：" + joinPoint.getSignature().getName() + "执行用时是： " + (end - start) + " ms!");
            }
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            if (log.isInfoEnabled()) {
                log.info("执行类：" + joinPoint.getSignature().getDeclaringTypeName() + "中的方法：" + joinPoint.getSignature().getName() + "执行用时是： " + (end - start) + " ms! 异常信息是 : " + e.getMessage());
            }
        }
    }

    /*配置后置返回通知,使用在方法aspect()上注册的切入点
     * 在某连接点正常完成后执行的通知：例如，一个方法没有抛出任何异常，正常返回。
     * @param joinPoint
     */
    @AfterReturning(returning = "rvt", value = "aspect()")
    public void afterReturn(JoinPoint joinPoint, Object rvt) {
        System.out.println("执行方法：" + joinPoint.getSignature().getName() + "返回数据是：" + JSON.toJSON(rvt));
        if (log.isInfoEnabled()) {
            log.info("正常执行方法：" + joinPoint.getSignature().getName());
        }
    }

    /*配置抛出异常后通知,使用在方法aspect()上注册的切入点
      *在方法抛出异常退出时执行的通知。
     */
    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex) {
        if (log.isInfoEnabled()) {
            log.info("执行方法：" + joinPoint.getSignature().getName() + "异常信息是：" + ex.getMessage());
        }
    }
}
