package com.sheva.studentmanager.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author Sheva
 * @data 2019/11/1  下午8:17
 * @Version 1.0
 *
 * 实现Web层的日志切面
 */
@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义一个切入点
     */

    @Pointcut("execution(* com.sheva.studentmanager.service.impl.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());

        //接受到请求，记录请求内容
        logger.info("WebLogAspect.deBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null){
            HttpServletRequest request = attributes.getRequest();
            logger.info("URL: " + request.getRequestURL().toString());
            logger.info("HTTP:METHOD : " + request.getMethod());
            logger.info("IP : " + request.getRemoteAddr());
            logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            logger.info("ARGS: " + Arrays.toString(joinPoint.getArgs()));

            //获取所有参数方法一：
            Enumeration<String> enu = request.getParameterNames();
            while(enu.hasMoreElements()){
                String paraName = enu.nextElement();
                System.out.println(paraName + ": " + request.getParameter(paraName));
            }
        }

    }

    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint){

        //处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");
        logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
        startTime.remove();
    }
}
