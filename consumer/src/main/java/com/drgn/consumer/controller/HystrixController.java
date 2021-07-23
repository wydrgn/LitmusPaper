package com.drgn.consumer.controller;

import com.drgn.common.exception.BaseException;
import com.drgn.common.model.DTO.EurekaServiceRequestDTO;
import com.drgn.common.model.web.Result;
import com.drgn.common.utils.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @description: Hystrix整合 测试接口
 * @author: wydrgn
 * @createDate: 2021-06-24 17:21
 */
//全局fallback设置 hystrix
@DefaultProperties(defaultFallback = "dealWithTimeOut", ignoreExceptions = {BaseException.class}, commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
})
@RestController
@RequestMapping("hystrix")
public class HystrixController {

    @Resource(name = "restTemplate")
    RestTemplate restTemplate;

    /**
     * 测试hystrix注解 优先以当前详细注释为准
     *
     * @param eurekaServiceRequestDTO
     * @return
     */
    @HystrixCommand(fallbackMethod = "dealWithTimeOutByCurrentMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    @PostMapping("/currentTimeout")
    public Result currentTimeout(@RequestBody EurekaServiceRequestDTO eurekaServiceRequestDTO) {
        //int errorNum = 10 / 0;
        try {
            // 实现超时
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.SUCCESS(null);
    }

    /**
     * 测试hystrix注解 使用类全局fallback注解
     *
     * @param eurekaServiceRequestDTO
     * @return
     */
    @HystrixCommand
    @PostMapping("/classTimeout")
    public Result hystrixTest(@RequestBody EurekaServiceRequestDTO eurekaServiceRequestDTO) {
        // 触发fallback
        // 抛出异常
        int errorNum = 10 / 0;
        try {
            // 实现超时
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.SUCCESS(null);
    }

    public Result dealWithTimeOut() {
        return Result.FAIL("Hystrix fallback:超时");
    }

    public Result dealWithTimeOutByCurrentMethod(EurekaServiceRequestDTO eurekaServiceRequestDTO) {
        //注:当fallback注解精确到方法时, 必须保证形参一致 否则会抛出 FallbackDefinitionException异常
        // fallback method wasn't found: dealWithTimeOutByCurrentMethod([class com.drgn.common.model.DTO.EurekaServiceRequestDTO])
        return Result.FAIL("Hystrix fallback:超时");
    }
}
