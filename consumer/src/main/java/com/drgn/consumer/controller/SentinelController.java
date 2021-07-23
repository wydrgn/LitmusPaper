package com.drgn.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.drgn.common.exception.BaseException;
import com.drgn.common.exception.ParamException;
import com.drgn.common.model.web.Result;
import com.drgn.consumer.sentinel.breaker.SentinelBlockHandler;
import com.drgn.consumer.sentinel.spare.SentinelFallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: Sentinel整合 测试接口
 * @author: wydrgn
 * @createDate: 2021-06-24 17:21
 */
@RestController
@RequestMapping("sentinel")
public class SentinelController {
    @GetMapping("/test")
    @SentinelResource(value = "test", blockHandler = "currentBlockHandler", fallback = "currentFallback", exceptionsToIgnore = BaseException.class)
    public Result test(@RequestParam(value = "p1") String p1,
                       @RequestParam(value = "p2", required = false) String p2) throws ParamException {
        if ("0".equals(p1)) {
            throw new ParamException("参数不被允许"); // 测试抛出自定义异常(忽略)
        } else if ("1".equals(p1)) {
            int a = 1 / 0; // 测试抛出运行时异常
        }
        return Result.SUCCESS("调用成功!p1=" + p1 + ",p2=" + p2);
    }

    // 异常 降级
    public Result currentFallback(String p1, String p2, Throwable e) {
        return Result.FAIL("fallback: " + e.getMessage());
    }

    // 限流 类似熔断 (优先级高于fallback)
    public Result currentBlockHandler(String p1, String p2) {
        return Result.FAIL("限流 blockHandler ");
    }

    @GetMapping("/test2")
    @SentinelResource(value = "test2", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "test2",
            fallbackClass = SentinelFallback.class, fallback = "test2", exceptionsToIgnore = BaseException.class)
    public Result test2(@RequestParam(value = "p1") String p1,
                        @RequestParam(value = "p2", required = false) String p2) throws ParamException {
        if ("0".equals(p1)) {
            throw new ParamException("参数不被允许"); // 测试抛出自定义异常(忽略)
        } else if ("1".equals(p1)) {
            int a = 1 / 0; // 测试抛出运行时异常
        }
        return Result.SUCCESS("调用成功!p1=" + p1 + ",p2=" + p2);
    }

}
