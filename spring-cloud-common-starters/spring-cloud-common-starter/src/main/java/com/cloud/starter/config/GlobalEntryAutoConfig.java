package com.cloud.starter.config;

import com.cloud.starter.SQ;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration(SQ.PRE + "GlobalEntryConfig")
@PropertySource(value = {"classpath:common-default.properties"},ignoreResourceNotFound = true)
@ComponentScan(basePackages = {"com.cloud.starter"})
@EnableDiscoveryClient
@EnableFeignClients
public class GlobalEntryAutoConfig {

}
