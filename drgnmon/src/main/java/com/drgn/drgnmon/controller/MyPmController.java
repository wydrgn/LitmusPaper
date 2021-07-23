package com.drgn.drgnmon.controller;

import com.drgn.common.model.MyPm;
import com.drgn.drgnmon.service.inter.MyPmService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 我的宝可梦
 * @author: wydrgn
 * @createDate: 2021-06-23 3:47
 */
@RestController
@RequestMapping("myPm")
public class MyPmController {
    @Resource
    MyPmService myPmService;

    @GetMapping("/list")
    public List<MyPm> list(String name) {
        return myPmService.list(name);
    }

    @GetMapping("/{id}")
    public MyPm get(@PathVariable String id) {
        return myPmService.get(id);
    }

    @PostMapping
    public String save(@RequestBody MyPm myPm) {
        return myPmService.save(myPm);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return myPmService.delete(id);
    }

    @PutMapping
    public Boolean update(@RequestBody MyPm myPm) {
        return myPmService.update(myPm);
    }
}
