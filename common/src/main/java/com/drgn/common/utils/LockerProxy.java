package com.drgn.common.utils;

import com.drgn.common.exception.BaseException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁代理
 **/
@Component
public class LockerProxy {

    @Autowired
    private RedissonClient redissonClient;

    public interface CallBack {
        void invoke() throws BaseException;
    }

    public void lock(CallBack cb, String lockKey, long timeout, long waitTimeout, TimeUnit unit) throws BaseException {
        RLock rLock = redissonClient.getLock(lockKey);
        try {
            if (!rLock.tryLock(waitTimeout, timeout, unit)) {
                throw new BaseException("当前系统正忙，请稍后再试");
            }
        } catch (InterruptedException e) {
            throw new BaseException("当前系统正忙，请稍后再试");
        }
        try {
            cb.invoke();
        } finally {
            if (!rLock.isLocked()) {
                return;
            }
            rLock.unlock();
        }
    }

    public void lock(CallBack cb, String lockKey) throws BaseException {
        lock(cb, lockKey, 5, 10, TimeUnit.SECONDS);
    }

    public boolean isLocked(String lockKey) {
        RLock rLock = redissonClient.getLock(lockKey);
        return rLock.isLocked();
    }
}
