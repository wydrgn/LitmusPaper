package com.drgn.consumer.feign.drgnmon.service.inter;

import com.drgn.common.model.MyPm;
import com.drgn.consumer.config.FeignConfig;
import com.drgn.consumer.feign.drgnmon.service.fallback.MyPmFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: drgnmon模块 myPm接口
 * @author: wydrgn
 * @createDate: 2021-06-23 17:01
 */
@Component
@FeignClient(name = "drgnmon", path = "/drgnmon/myPm", configuration = FeignConfig.class, fallback = MyPmFallback.class)
public interface MyPmService {
    @GetMapping("/list")
    List<MyPm> list(String name);

    @GetMapping("/{id}")
    MyPm get(@PathVariable String id);

    @PostMapping
    String save(@RequestBody MyPm myPm);

    @DeleteMapping("/{id}")
    Boolean delete(@PathVariable String id);

    @PutMapping
    Boolean update(@RequestBody MyPm myPm);
}
