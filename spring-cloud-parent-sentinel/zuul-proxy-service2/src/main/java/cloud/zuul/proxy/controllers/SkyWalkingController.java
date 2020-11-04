package cloud.zuul.proxy.controllers;

import cloud.common.base.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @apiNote 测试skywalking 自定义注解和探针的使用
 */
@RestController
@Slf4j
public class SkyWalkingController {
    @GetMapping("tractAnnotation")
    public User traceAnnotation(
            @RequestParam("name") String name
    ) {
        log.info("从前端接收到的参数:[{}]", name);
        User user = trace(name);
        ActiveSpan.tag("new-tag", user.toString());
        ActiveSpan.info("输出信息");
        log.info("tractId:[{}]", TraceContext.traceId());
        return user;
    }

    @Trace(operationName = "添加自定义的方法")
    @Tags({
            @Tag(key = "从方法参数中获取值", value = "arg[0]"),
            @Tag(key = "从返回值中获取值", value = "returnedObj.userName")
    })
    private User trace(String name) {
        log.info("如果此方法没有被SkyWalking收集，但是又需要被收集到，可以加上@Trace注解");
        User user = new User();
        user.setUserName("创建的名字");
        return user;
    }

}
