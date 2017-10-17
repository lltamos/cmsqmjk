package com.quanmin.interceptor;

import com.alibaba.fastjson.JSON;
import com.quanmin.util.ResultUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @Author: By heasy.
 * @Date: 2017/9/18.
 * @Contcat: yz972641975@gmail.com.
 * @Description: 定义aop
 * @Modified By:
 */
public class LogInterceptor {
    private final static Log log=LogFactory.getLog(LogInterceptor.class);
    long startTime=0L;
    long endTime=0L;

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* com.quanmin.service.*.*(..))")
    public void aspect() {
    }


    /*配置环绕通知,使用在方法aspect()上注册的切入点
    *包围一个连接点的通知，如方法调用。这是最强大的一种通知类型。环绕通知可以在方法调用前后完成自定义的行为。它也会选择是否继续执行连接点或直接返回它自己的返回值或抛出异常来结束执行。
    */
//    @Around("aspect()")
    public Object around(JoinPoint joinPoint) {

        long start=System.currentTimeMillis();
        try {
            Object[] args=joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof Errors) {
                    Errors error=(Errors) arg;
                    List<ObjectError> allErrors=error.getAllErrors();
                    if (allErrors != null && allErrors.size() > 0) {
                        return ResultUtils.returnException();
                    }
                }
            }

            Object proceed=((ProceedingJoinPoint) joinPoint).proceed();
            log.info("\n********************执行类********************" + joinPoint.getSignature().getDeclaringTypeName() + "\n********************方法********************" + joinPoint.getSignature().getName() + "\n********************返回数据是********************" + JSON.toJSON(proceed));
            long end=System.currentTimeMillis();
            if (log.isInfoEnabled()) {
                log.info("\n********************执行类********************" + joinPoint.getSignature().getDeclaringTypeName() + "\n********************方法********************" + joinPoint.getSignature().getName() + "\n********************执行用时是********************" + (end - start) + " ms!");
            }
            return proceed;
        } catch (Throwable e) {
            long end=System.currentTimeMillis();
            if (log.isInfoEnabled()) {
                log.info("\n********************执行类********************" + joinPoint.getSignature().getDeclaringTypeName() + "\n********************方法********************" + joinPoint.getSignature().getName() + "\n********************执行用时是********************" + (end - start) + " ms!\n********************异常信息是********************" + e.getMessage());
            }
            e.printStackTrace();
            return null;
        }
    }
}
