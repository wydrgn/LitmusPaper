package com.drgn.astronaut.controller;

import com.drgn.astronaut.service.inter.PmService;
import com.drgn.common.config.ServerConfig;
import com.drgn.common.model.Pm;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 宝可梦
 * @author: wydrgn
 * @createDate: 2021-06-22 2:49
 */
@RestController
@RequestMapping("/pm")
public class PmController {
    @Resource
    ServerConfig serverConfig;

    @Resource
    PmService pmService;

    @GetMapping("/list")
    public List<Pm> list(String name) {
        //int n = 1/0;
        System.out.println(serverConfig.getPort());
        return pmService.list(name);
    }

    @GetMapping("/{id}")
    public Pm get(@PathVariable String id) {
        return pmService.get(id);
    }

    @PostMapping
    public String save(@RequestBody Pm pm) {
        return pmService.save(pm);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        return pmService.delete(id);
    }

    @PutMapping
    public Boolean update(@RequestBody Pm pm) {
        return pmService.update(pm);
    }
}
