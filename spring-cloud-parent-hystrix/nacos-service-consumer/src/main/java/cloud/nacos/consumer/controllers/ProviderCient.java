package cloud.nacos.consumer.controllers;

import cloud.common.base.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NacosServiceProvider", fallback = ProviderHystrixFallback.class)
public interface ProviderCient {
    @GetMapping(value = "/echo/{string}")
    public String getStr(@PathVariable String string);

    @PostMapping("/post_user")
    public User getUser(@RequestBody User user);

}
