package com.boot.netty.config;

import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RefreshScope
@RequiredArgsConstructor
@RequestMapping("/config")
public class ConfigController {
    @Value("${useLocalCache:false}")
    private boolean useLocalCache;
    private final GlobalTrafficShapingHandler trafficShapingHandler;

    @GetMapping("/get")
    public boolean get() {
        return useLocalCache;
    }

    @GetMapping("/modify_tcp")
    public Object modifyTcpConfig(@RequestParam(name = "limit", defaultValue = "1000")Long limit){
        if (trafficShapingHandler != null){
            trafficShapingHandler.setReadLimit(limit);
            trafficShapingHandler.setWriteLimit(limit * 10);
            return "修改限速成功";
        }else {
            return "未添加限速配置";
        }
    }
}
