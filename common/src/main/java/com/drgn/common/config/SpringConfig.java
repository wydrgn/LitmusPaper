package com.drgn.common.config;

import com.drgn.common.model.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfig {
    @Value("${SnowFlake.datacenterId}")
    private Long datacenterId;
    @Value("${SnowFlake.machineId}")
    private Long machineId;

    /**
     * 默认超时时间一分钟，也就是说想要设置超时时间，请在浏览器中设置；或者重新newRestTemplate;但自己new的没有负载均衡实现
     *
     * @return RestTemplate
     */
    @Bean("restTemplate")
    //@SentinelRestTemplate(blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)
    // Spring Cloud Alibaba Sentinel 支持对 RestTemplate 的服务调用使用 Sentinel 进行保护，在构造 RestTemplate bean的时候需要加上 @SentinelRestTemplate 注解。
    @LoadBalanced
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(1000 * 60);
        httpRequestFactory.setConnectTimeout(1000 * 60);
        httpRequestFactory.setReadTimeout(1000 * 60);
        return new RestTemplate();
    }

    @Bean("snowFlake")
    public SnowFlake getSnowFlake() {
        if (datacenterId == null) {
            datacenterId = 1L;
        }
        if (machineId == null) {
            machineId = 1L;
        }
        return new SnowFlake(datacenterId, machineId);
    }
    /*seata*/
    /*@Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        String applicationName = this.applicationContext.getEnvironment().getProperty("spring.application.name");
        String txServiceGroup = this.seataProperties.getTxServiceGroup();
        if (StringUtils.isEmpty(txServiceGroup)) {
            txServiceGroup = applicationName + "-fescar-service-group";
            this.seataProperties.setTxServiceGroup(txServiceGroup);
        }

        return new GlobalTransactionScanner(applicationName, txServiceGroup);
    }*/
}
