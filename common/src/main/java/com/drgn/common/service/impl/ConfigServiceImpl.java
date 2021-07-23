package com.drgn.common.service.impl;

import com.drgn.common.config.ServerConfig;
import com.drgn.common.service.inter.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@RefreshScope
public class ConfigServiceImpl implements ConfigService {
    @Resource
    ServerConfig serverConfig;

    @Value("${config.info}")
    String info;

    @Override
    public String info() {
        return info;
    }

    @Override
    public String port() {
        return serverConfig.getPort();
    }
}
