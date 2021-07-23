package com.drgn.common.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import javax.annotation.Resource;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;


@Configuration
public class RedisConfig {
    private static final Integer CORE_POOL_SIZE = 1000; // 核心线程数
    private static final Integer MAXIMUM_POOL_SIZE = 1000; // 最大线程数
    private static final Integer KEEP_ALIVE_TIME = 60; // 生命周期

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @HystrixCommand(fallbackMethod = "connectTimeOut", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    public boolean isAlive() {
        boolean b = false;
        try {
            b = "PONG".equals(ping());
        } catch (Exception e) {
            System.out.println("redis connect fail");
        }
        return b;
    }

    //500ms没有响应默认redis已下线
    public boolean connectTimeOut() {
        return false;
    }

    private String ping() {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                String ping = null;
                try {
                    ping = connection.ping();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ping;

            }
        });
    }

    /**
     * 自定义线程工厂类
     */
    static class NameThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "redisThread_" + threadNum.updateAndGet(i ->
                    // 防止越界 重置1
                    i >= Integer.MAX_VALUE ? 1 : i + 1
            ));
        }
    }

    @Bean("redisThreadPoolExecutor")
    public ThreadPoolExecutor getRedisThreadPoolExecutor() {
        //ThreadPoolExecutor tpe = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, getRedisPriorityBlockingQueue());
        return (ThreadPoolExecutor) getThreadPoolExecutorFactoryBean().getObject();

    }

    /**
     * redis线程池工厂
     *
     * @return
     */
    @Bean("redisThreadPoolExecutorFactory")
    public ThreadPoolExecutorFactoryBean getThreadPoolExecutorFactoryBean() {
        ThreadPoolExecutorFactoryBean tpef = new ThreadPoolExecutorFactoryBean();
        // 核心线程数
        tpef.setCorePoolSize(CORE_POOL_SIZE);
        // 最大线程数
        tpef.setMaxPoolSize(MAXIMUM_POOL_SIZE);
        // 生命周期
        tpef.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        tpef.setThreadNamePrefix("redisTPE");
        tpef.setThreadFactory(new NameThreadFactory());
        // 拒绝策略 DiscardOldestPolicy 队列已满会先移除最老 的
        tpef.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        return tpef;
    }
}
