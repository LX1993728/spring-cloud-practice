package sentinel.nacos.consumer.controllers;

import sentinel.common.base.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/echo/{str}")
    @HystrixCommand(fallbackMethod = "echoFallback")
    public String echo(@PathVariable("str") String string) {
        return restTemplate.getForObject("http://NacosServiceProvider/echo/" + string, String.class);
    }

    @Autowired
    private ProviderCient providerCient;


    public String echoFallback( String string){
        return "{}";
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
