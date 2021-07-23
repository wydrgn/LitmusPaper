package com.drgn.consumer.feign.astronaut.controller;

import com.drgn.common.model.User;
import com.drgn.common.model.web.Result;
import com.drgn.consumer.feign.astronaut.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/list")
    public Result list(String name) {
        return Result.SUCCESS(userService.list(name));
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable String id) {
        return Result.SUCCESS(userService.get(id));
    }

    @PostMapping
    public Result save(@RequestBody User user) {
        return Result.SUCCESS(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        return Result.SUCCESS(userService.delete(id));
    }

    @PutMapping
    public Result update(User user) {
        return Result.SUCCESS(userService.update(user));
    }

}
