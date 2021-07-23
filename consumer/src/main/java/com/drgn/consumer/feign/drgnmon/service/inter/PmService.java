package com.drgn.consumer.feign.drgnmon.service.inter;

import com.drgn.common.model.Pm;
import com.drgn.consumer.config.FeignConfig;
import com.drgn.consumer.feign.drgnmon.service.fallback.PmFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: drgnmon模块 pm接口
 * @author: wydrgn
 * @createDate: 2021-06-23 17:01
 */
@Component
@FeignClient(name = "drgnmon", path = "/drgnmon/pm", configuration = FeignConfig.class, fallback = PmFallback.class)
public interface PmService {
    @GetMapping("/list")
    List<Pm> list(String name);

    @GetMapping("/{id}")
    Pm get(@PathVariable String id);

    @PostMapping
    String save(@RequestBody Pm pm);

    @DeleteMapping("/{id}")
    Boolean delete(@PathVariable String id);

    @PutMapping
    Boolean update(@RequestBody Pm pm);
}
