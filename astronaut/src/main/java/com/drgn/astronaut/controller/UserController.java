package com.drgn.astronaut.controller;

import com.drgn.astronaut.service.inter.UserService;
import com.drgn.common.config.ServerConfig;
import com.drgn.common.exception.BaseException;
import com.drgn.common.exception.ParamException;
import com.drgn.common.model.User;
import com.drgn.common.utils.LockerProxy;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    ServerConfig serverConfig;

    @Resource
    UserService userService;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    LockerProxy lockerProxy;

    @GetMapping("/testRedisson")
    public String testRedisson(List<String> idList) throws BaseException {
        lockerProxy.lock(() -> {
            try {
                Thread.sleep(20 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("具体逻辑");
        }, "testRedisson",30L, 5L, TimeUnit.SECONDS);
        return "testRedisson";
    }

    @GetMapping("/list")
    public List<User> list(String name) {
        //int n = 1/0;
        System.out.println(serverConfig.getPort());
        return userService.list(name);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable String id) {
        return userService.get(id);
    }

    @PostMapping
    public String save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return userService.delete(id);
    }

    @PutMapping
    public Boolean update(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("/success/{id}")
    public String success(String id) {
        return userService.success(id);
    }

    @GetMapping("/fail/{id}")
    public String fail(@PathVariable String id) throws ParamException {
        return userService.fail(id);
    }
}
