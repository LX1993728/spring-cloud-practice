package cloud.nacos.registry.controllers;

import cloud.common.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProviderCient providerCient;

    @GetMapping(value = "/echo/{str}")
    public String echo(@PathVariable("str") String string) {
        return restTemplate.getForObject("http://nacos-service-provider/echo/" + string, String.class);

    }
    
    @GetMapping("/post_user")
    public User postUser(){
        User user = new User();
        user.setAge(30);
        user.setEmail("xxx@gmail.com");
        user.setTelephone("12377876754");
        user.setUserName("张三");
        final User cientUser = providerCient.getUser(user);
        return cientUser;
    }

}
