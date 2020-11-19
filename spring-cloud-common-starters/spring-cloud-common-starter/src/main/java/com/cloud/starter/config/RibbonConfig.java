package com.cloud.starter.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.cloud.starter.SQ;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @apiNote 使用sentinel包装RestTemplate
 */
@Configuration(SQ.PRE + "RibbonConfig")
public class RibbonConfig {

    @Bean
    @LoadBalanced
    @SentinelRestTemplate
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
