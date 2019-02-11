package com.saber.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    // execution(方法修饰符(可选) 返回类型 方法名 参数 异常模式(可选))
    @Pointcut("execution(public * com.saber.controller.*.*(..))")
    public void LogAspect() {}

    @Before("LogAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("doBefore");
    }

    @After("LogAspect()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("doAfter");
    }

    @AfterReturning("LogAspect()")
    public void doAfterReturning(JoinPoint joinPoint) {
        System.out.println("doAfterReturning");
    }

    @AfterThrowing("LogAspect()")
    public void doAfterThrowing(JoinPoint joinPoint) {
        System.out.println("doAfterThrowing");
    }

    @Around("LogAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("doAround");
        return joinPoint.proceed();
    }
}
