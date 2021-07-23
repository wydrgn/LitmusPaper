package com.drgn.common.utils;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @description: 异常工具类
 * @author: wydrgn
 * @createDate: 2021-06-18 3:04
 * @todo:
 */
public class ExceptionUtil {

    // 处理 OpenFeign 服务降级
    public static ClientHttpResponse handleException(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException exception) {
        //todo
        SentinelClientHttpResponse schr = new SentinelClientHttpResponse("限流");
        return schr;
    }
}
