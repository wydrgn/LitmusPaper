package com.drgn.common.aop;

import com.drgn.common.exception.BaseException;
import com.drgn.common.model.web.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Aspect
@Order(value = 1)
@Component
public class RequestAop {

    @Resource
    private RedissonClient redissonClient;

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
