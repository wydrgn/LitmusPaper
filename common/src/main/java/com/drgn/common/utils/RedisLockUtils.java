package com.drgn.common.utils;

import com.drgn.common.model.utils.SnowFlake;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * redis实现的简单分布式锁,可以用redisson框架取代,也更加完善
 * 当前是一个非常非常非常简单的基本实现
 * 思路:分布式服务通过使用redis原子操作SETNX(redisTemplate.opsForValue().setIfAbsent()) 添加键值对并设置过期时间.
 * 同时看门狗方法 通过线程池分配线程, 循环检查key是否过期,未过期则续期key并sleep,直到下一次循环开始.
 * wuyunlong
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RedisLockUtils {
    @Resource(name = "redisThreadPoolExecutor")
    ThreadPoolExecutor redisThreadPoolExecutor;
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    public static final String DELETE_KEY_SCRIPT = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
    public static Long DELETE_KEY_SUCCESS = 1L;

    public boolean isLock(String key) {
        return !StringUtils.isEmpty(redisTemplate.opsForValue().get(key));
    }

    public boolean lock(String key, String value) {
        Boolean isLockSuccess = redisTemplate.opsForValue().setIfAbsent(key, value, 30L, TimeUnit.SECONDS);
        if (isLockSuccess != null && isLockSuccess) {
            //System.out.println("加锁成功 key:" + key + " value:" + value);
            watchDog(key, value);
            return true;
        }
        return false;
    }


    public boolean lockWithoutListen(String key, String value) {
        return lockWithoutListen(key, value, 60L);
    }

    public boolean lockWithoutListen(String key, String value, Long timeout) {
        Boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
        return isSuccess != null && isSuccess;
    }

    public String getsnowFlakeId() {
        SnowFlake sf = new SnowFlake(1, 1);
        return String.valueOf(sf.nextId());
    }

    /**
     * 根据类,方法名,id返回redis key值
     *
     * @param clazz      类名
     * @param methodName 方法名
     * @param id         参数id
     * @return redis key
     */
    public String lockKey(Class<Object> clazz, String methodName, String id) {
        return clazz.getName() + methodName + id;
    }

    /**
     * 根据类返回redis key值
     *
     * @param clazz 类名
     * @return redis key
     */
    public String lockKey(Class<Object> clazz) {
        return clazz.getName();
    }

    /**
     * 解锁(使用lua脚本)
     *
     * @param key   redis Key
     * @param value redis Value
     * @return 删除成功返回true, 失败false
     */
    public boolean unLock(String key, String value) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(DELETE_KEY_SCRIPT);
        redisScript.setResultType(Long.class);
        return DELETE_KEY_SUCCESS.equals(redisTemplate.execute(redisScript, Collections.singletonList(key), value));
    }

    /**
     * 监听,给锁提供续期
     *
     * @param key   redis Key
     * @param value redis Value
     */
    public void watchDog(String key, String value) {
        System.out.println("活跃线程数" + redisThreadPoolExecutor.getActiveCount());
        // 延长锁时间
        redisThreadPoolExecutor.execute(new Runnable() {
            public void run() {
                Thread.currentThread().setName(key + "_" + value);
                System.out.println(Thread.currentThread().getName());
                int flag = 0; //最多延期锁4次
                while (value.equals(redisTemplate.opsForValue().get(key))) {
                    try {
                        Thread.sleep(25 * 1000);
                        //LockSupport.parkUntil(25 * 1000L);
                        //wait(25 * 1000);
                        if (value.equals(redisTemplate.opsForValue().get(key)) && flag < 5) {
                            redisTemplate.expire(key, 30L, TimeUnit.SECONDS);
                            flag++;
                        } else {
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.out.println("线程执行结束" + redisThreadPoolExecutor.getActiveCount());

    }
}
