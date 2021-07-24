package com.drgn.clefki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(value = {"com.drgn.**.mapper"})
@ComponentScan(value = {"com.drgn"})
public class ClefkiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClefkiApplication.class, args);
    }

}
