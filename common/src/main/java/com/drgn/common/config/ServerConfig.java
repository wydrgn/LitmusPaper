package com.drgn.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {
    @Value("${server.port}")
    private String port;

    public String getPort() {
        return port;
    }

}
