package com.drgn.consumer.feign.drgnmon.controller;

import com.drgn.common.model.MyPm;
import com.drgn.common.model.web.Result;
import com.drgn.consumer.feign.drgnmon.service.inter.MyPmService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author: wydrgn
 * @createDate: 2021-06-24 15:31
 */
@RestController
@RequestMapping("myPm")
public class MyPmController {
    @Resource
    MyPmService myPmService;

    @GetMapping("/list")
    Result list(String name) {
        return Result.SUCCESS(myPmService.list(name));
    }

    @GetMapping("/{id}")
    Result get(@PathVariable String id) {
        return Result.SUCCESS(myPmService.get(id));
    }

    @PostMapping
    Result save(@RequestBody MyPm myPm) {
        return Result.SUCCESS(myPmService.save(myPm));
    }

    @DeleteMapping("/{id}")
    Result delete(@PathVariable String id) {
        return Result.SUCCESS(myPmService.delete(id));
    }

    @PutMapping
    Result update(@RequestBody MyPm myPm) {
        return Result.SUCCESS(myPmService.update(myPm));
    }
}
