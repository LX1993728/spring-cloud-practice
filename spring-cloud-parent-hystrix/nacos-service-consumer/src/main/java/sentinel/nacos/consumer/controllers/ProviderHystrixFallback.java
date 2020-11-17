package sentinel.nacos.consumer.controllers;

import sentinel.common.base.User;
import org.springframework.stereotype.Component;

@Component
public class ProviderHystrixFallback implements ProviderCient {
    @Override
    public String getStr(String string) {
        return null;
    }

    @Override
    public User getUser(User user) {
        return null;
    }
}
