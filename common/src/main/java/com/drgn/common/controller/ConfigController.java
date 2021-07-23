package com.drgn.common.controller;

import com.drgn.common.service.inter.ConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Resource
    ConfigService configService;

    @GetMapping("info")
    public String info() {
        return configService.info();
    }

    @GetMapping("port")
    public String port() {
        return configService.port();
    }

}
