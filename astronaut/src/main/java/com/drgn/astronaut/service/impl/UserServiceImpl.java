package com.drgn.astronaut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drgn.astronaut.service.inter.UserService;
import com.drgn.common.annotations.RedisCashe;
import com.drgn.common.exception.ParamException;
import com.drgn.common.mapper.UserMapper;
import com.drgn.common.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @RedisCashe(Type = RedisCashe.HandleType.INSERT)
    @Override
    public List<User> list(String name) {
        //int i = 1/0;
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("name", name);
        List<User> userList = userMapper.selectList(qw);
        if (userList == null) {
            return new ArrayList<>();
        }
        return userList;
    }

    @Override
    public User get(String id) {
        return userMapper.selectById(id);
    }

    @RedisCashe(Type = RedisCashe.HandleType.DELETE)
    @Override
    public String save(User user) {
        userMapper.insert(user);
        return user.getId();
    }

    @RedisCashe(Type = RedisCashe.HandleType.DELETE)
    @Override
    public Boolean delete(String id) {
        return userMapper.deleteById(id) > 0;
    }

    @RedisCashe(Type = RedisCashe.HandleType.DELETE)
    @Override
    public Boolean update(User user) {
        return userMapper.updateById(user) > 0;
    }

    @HystrixCommand(fallbackMethod = "connectTimeOut", commandProperties = {
            //@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    @Override
    public String success(String id) {
        return "成功";
    }

    @HystrixCommand(fallbackMethod = "connectTimeOut", commandProperties = {
            //@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 失败率
    })
    @Override
    public String fail(String id) throws ParamException {
        if ("1".equals(id)) {
            throw new ParamException("shibaile ");
        }
        return id + " cheng gong !";
    }

    public String connectTimeOut(String id) {
        return "id=" + id + " fallback!!";
    }
}
