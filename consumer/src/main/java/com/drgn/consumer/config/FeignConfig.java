package com.drgn.consumer.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.Logger;
import feign.hystrix.HystrixFeign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({HystrixCommand.class, HystrixFeign.class})
public class FeignConfig {

    /*@Bean
    @Scope("prototype")
    @ConditionalOnProperty(name = "feign.hystrix.enabled")
    public Feign.Builder feignHystrixBuilder() {
        HystrixFeign.Builder builder = HystrixFeign.builder();
        SetterFactory setterFactory = new SetterFactory() {
            @Override
            public HystrixCommand.Setter create(Target<?> target, Method method) {
                String groupKey = target.name();
                String commandKey = Feign.configKey(target.type(), method);
                //HystrixThreadPoolProperties 线程池相关配置
                HystrixThreadPoolProperties.Setter setter = HystrixThreadPoolProperties.Setter().withCoreSize(100).withMaxQueueSize(200);
                //HystrixCommandProperties 熔断器相关属性配置
                HystrixCommandProperties.Setter setter1 = HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(16000);
                return HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                        .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                        .andThreadPoolPropertiesDefaults(setter)
                        .andCommandPropertiesDefaults(setter1);
            }
        };
        builder.setterFactory(setterFactory);
        return builder;
    }*/

    /*@Bean
    public Request.Options feignRequestOptions() {
        return new Request.Options(3000, TimeUnit.MILLISECONDS,
                10000, TimeUnit.MILLISECONDS,
                true);
    }*/

    @Bean
    public Logger.Level Logger() {
        return Logger.Level.FULL;
    }

   /* @Bean
    public Retryer retryer(){
        //重试间隔为 100ms，最大重试时间为 1s, 重试次数为 5 次
        return new Retryer.Default(100,SECONDS.toMillis(1),5);
    }*/
}
