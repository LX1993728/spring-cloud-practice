package cloud.nacos.registry.controllers;

import cloud.common.base.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProviderController {
    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }

    @PostMapping("/post_user")
    public User postUser(@RequestBody User user){
        return user;
    }

}
