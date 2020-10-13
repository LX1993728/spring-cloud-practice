package cloud.nacos.consumer.controllers;

import cloud.common.base.User;
import org.springframework.stereotype.Component;

@Component
public class ProviderClientFallback implements ProviderCient {
    @Override
    public String getStr(String string) {
        return null;
    }

    @Override
    public User getUser(User user) {
        return null;
    }
}
