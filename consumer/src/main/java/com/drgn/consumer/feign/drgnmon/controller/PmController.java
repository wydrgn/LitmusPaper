package com.drgn.consumer.feign.drgnmon.controller;

import com.drgn.common.model.Pm;
import com.drgn.common.model.web.Result;
import com.drgn.consumer.feign.drgnmon.service.inter.PmService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author: wydrgn
 * @createDate: 2021-06-24 15:32
 */
@RestController
@RequestMapping("pm")
public class PmController {

    @Resource
    PmService pmService;

    @GetMapping("/list")
    Result list(String name) {
        return Result.SUCCESS(pmService.list(name));
    }

    @GetMapping("/{id}")
    Result get(@PathVariable String id) {
        return Result.SUCCESS(pmService.get(id));
    }

    @PostMapping
    Result save(@RequestBody Pm pm) {
        return Result.SUCCESS(pmService.save(pm));
    }

    @DeleteMapping("/{id}")
    Result delete(@PathVariable String id) {
        return Result.SUCCESS(pmService.delete(id));
    }

    @PutMapping
    Result update(@RequestBody Pm pm) {
        return Result.SUCCESS(pmService.update(pm));
    }
}
