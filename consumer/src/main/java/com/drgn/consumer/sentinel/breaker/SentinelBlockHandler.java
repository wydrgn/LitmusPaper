package com.drgn.consumer.sentinel.breaker;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.drgn.common.model.web.Result;

/**
 * @description: {@link com.drgn.consumer.controller.SentinelController} 熔断处理器
 * @author: wydrgn
 * @createDate: 2021-06-18 0:57
 * @todo:
 */
public class SentinelBlockHandler {
    public static Result test2(String p1, String p2) {
        return Result.FAIL("当前服务已熔断");
    }
}
