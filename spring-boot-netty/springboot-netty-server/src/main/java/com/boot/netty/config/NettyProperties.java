package com.boot.netty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ConfigurationProperties(prefix = "netty")
@Component
public class NettyProperties {
    @NotNull
    @Size(min = 1000, max = 65535)
    private int tcpPort;

    @NotNull
    @Min(1)
    private int bossCount;

    @NotNull
    @Min(2)
    private int workerCount;

    @NotNull
    private boolean keepAlive;

    @NotNull
    @Min(10)
    private long readLimit;

    @NotNull
    @Min(10)
    private long writeLimit;

    @NotNull
    private int backlog;
}
