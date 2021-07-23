package com.drgn.common.aop;

import com.alibaba.fastjson.JSONObject;
import com.drgn.common.annotations.RedisCashe;
import com.drgn.common.config.RedisConfig;
import com.drgn.common.utils.RedisLockUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


@Aspect
@Component
public class RedisCasheAop {

    @Resource
    RedisConfig redisConfig;
    @Resource
    private RedisLockUtils redisLockUtils;
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;
    private static AtomicBoolean isRedisAlive = new AtomicBoolean(false);// 判断redis是否alive

    @Pointcut(value = "@annotation(com.drgn.common.annotations.RedisCashe)")
    public void pointcut() {
    }

    @Before("pointcut() && @annotation(redisCashe)")
    public void before(JoinPoint jp, RedisCashe redisCashe) {
        if (isRedisAlive.get()) {
            return;
        }
        if (RedisCashe.HandleType.DELETE.equals(redisCashe.Type())) { //删除缓存
            deleteCashe(jp);
        }
    }

    @Around(value = "pointcut()&& @annotation(redisCashe)")
    public Object around(ProceedingJoinPoint pjp, RedisCashe redisCashe) throws Throwable {
        // 每次调用前检查是否redis在线
        isRedisAlive.set(redisConfig.isAlive());
        if (isRedisAlive.get() && RedisCashe.HandleType.INSERT.equals(redisCashe.Type())) { //插入缓存
            Type t = getReturnType(pjp);
            if (redisLockUtils.isLock(redisLockUtils.lockKey((Class<Object>) pjp.getTarget().getClass()))) {// 是否上锁
                String s = redisTemplate.opsForValue().get(getCasheKey(pjp));
                if (!StringUtils.isEmpty(s)) {
                    return JSONObject.parseObject(s, t);
                }
            }
        }
        Object proceed = pjp.proceed();
        System.out.println("待施工");
        return proceed;
    }

    @AfterReturning(value = "pointcut() && @annotation(redisCashe)", returning = "returnValue")
    public void afterReturning(JoinPoint jp, RedisCashe redisCashe, Object returnValue) {
        if (isRedisAlive.get()) {
            return;
        }
        if (RedisCashe.HandleType.DELETE.equals(redisCashe.Type())) { //删除缓存
            deleteCashe(jp);
        } else if (RedisCashe.HandleType.INSERT.equals(redisCashe.Type())) { //插入缓存
            redisTemplate.opsForValue().setIfAbsent(getCasheKey(jp), JSONObject.toJSONString(returnValue),
                    30, TimeUnit.MINUTES);
        }
    }

    private void deleteCashe(JoinPoint jp) {
        redisLockUtils.lockWithoutListen(redisLockUtils.lockKey((Class<Object>) jp.getTarget().getClass()), "1", 3L);// 加锁3S
        String clazzName = getClazzName(jp);
        Set<String> keys = redisTemplate.keys(clazzName + "_*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    private Type getReturnType(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        //获取方法返回值类型
        Object[] args = pjp.getArgs();
        Class<?>[] paramsCls = new Class<?>[args.length];
        for (int i = 0; i < args.length; ++i) {
            paramsCls[i] = args[i].getClass();
        }
        //获取方法
        Method method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), paramsCls);
        //获取返回值类型
        return method.getAnnotatedReturnType().getType();
    }

    private String getClazzName(JoinPoint jp) {
        MethodInvocationProceedingJoinPoint mipjp = (MethodInvocationProceedingJoinPoint) jp;
        Class<?> clazz = Objects.requireNonNull(mipjp.getTarget()).getClass();
        // 类名
        return clazz.getName();
    }

    private String getCasheKey(JoinPoint jp) {
        MethodInvocationProceedingJoinPoint mipjp = (MethodInvocationProceedingJoinPoint) jp;
        Class<?> clazz = Objects.requireNonNull(mipjp.getTarget()).getClass();
        String clazzName = clazz.getName(); // 类名
        String paramStr = "";
        Object[] args = mipjp.getArgs();
        if (args.length > 0) {
            List<Object> objects = Arrays.asList(args);
            paramStr = objects.stream().map(JSONObject::toJSONString).collect(Collectors.joining(",")); // 参数值
        }
        String methodName = ((MethodSignature) jp.getSignature()).getMethod().getName(); // 方法名
        return clazzName + "_" + methodName + "_" + paramStr;
    }
}
