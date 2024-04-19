package com.nowcoder.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 王修豪
 * @version 1.0
 */
//@Component//因为不是特定的某一层
//@Aspect
public class AlphaAspect {
    //织入哪些bean的哪些位置
    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")//第一个*代表返回值 什么返回值都可以，然后是包名，sercive所有的类，所有的方法(所有的参数)
    public void pointcut(){

    }
    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("after");
    }

    @AfterReturning("pointcut()")
    public void afterRetuning() {
        System.out.println("afterRetuning");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

    //用于代替原始对象
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before");
        Object obj = joinPoint.proceed();//运行原始对象
        System.out.println("around after");
        return obj;
    }
}
