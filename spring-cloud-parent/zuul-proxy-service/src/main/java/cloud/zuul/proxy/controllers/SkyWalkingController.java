package cloud.zuul.proxy.controllers;

import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @apiNote 测试skywalking 自定义注解和探针的使用
 */
@RestController
public class SkyWalkingController {
    @GetMapping("/s")
    @Trace
    public Object s(){
        ActiveSpan.tag("TEST","test sky tool");
        ActiveSpan.setOperationName("操作名称测试端点");
        ActiveSpan.error(new RuntimeException("Test-Error-Throwable"));
        return "aaa";
    }
}
