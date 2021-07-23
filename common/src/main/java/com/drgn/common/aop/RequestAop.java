package com.drgn.common.aop;

import com.drgn.common.exception.BaseException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(value = 1)
@Component
public class RequestAop {

    @Order(value = 1)
    @Before("execution(* com.drgn.common.controller.RequestController.*(..))")
    public void requestBefore(JoinPoint joinPoint) {
        System.out.println("before");
    }

    //自定义异常处理
    @AfterThrowing(throwing = "e", pointcut = "execution(* com.drgn.common.controller.RequestController.*(..))")
    public void requestAfterThrowing(BaseException e) {
        System.out.println("AfterThrowing");
    }
}
