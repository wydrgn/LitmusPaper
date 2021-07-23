package com.drgn.consumer.sentinel.spare;

import com.drgn.common.model.web.Result;

/**
 * @description: 全局降级处理类
 * @author: wydrgn
 * @createDate: 2021-06-18 1:08
 * @todo:
 */
public class SentinelFallback {
    // 异常 降级
    public static Result test2(String p1, String p2, Throwable e) {
        return Result.FAIL("fallback reason:" + e.getMessage());
    }
}
