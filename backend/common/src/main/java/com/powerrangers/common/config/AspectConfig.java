package com.powerrangers.common.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {

    @Pointcut("@annotation(com.powerrangers.common.config.SystemLog)")
    public void PointCut() {}

    @Around("PointCut()")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        System.out.println("around test start");

        Object point = proceedingJoinPoint.proceed();
        System.out.println(methodSignature.getMethod().getAnnotation(SystemLog.class).value());

        System.out.println("around test end");

        return point;
    }

    @Before("PointCut()")
    public Object Before(JoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("before test");

        System.out.println(methodSignature.getMethod().getAnnotation(SystemLog.class).value());

        return joinPoint;
    }

    @After("PointCut()")
    public Object After(JoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println(methodSignature.getMethod().getAnnotation(SystemLog.class).value());

        System.out.println("after test");

        return joinPoint;
    }
}
