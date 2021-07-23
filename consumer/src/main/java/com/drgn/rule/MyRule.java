package com.drgn.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//todo 注:nacos整合这个会出问题 其中某个服务找不到
//@Configuration
public class MyRule {

    //@Bean
    public IRule mySelfRule() {
        return new RandomRule();
    }
}
