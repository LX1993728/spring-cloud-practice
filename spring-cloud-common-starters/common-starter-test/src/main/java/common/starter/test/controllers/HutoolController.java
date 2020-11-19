package common.starter.test.controllers;

import cn.hutool.core.net.NetUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/hutool")
public class HutoolController {
    @GetMapping("/test1")
    public Object getServletUtilData(HttpServletRequest request){
        String clientIp = ServletUtil.getClientIP(request);
        Set<String> localIps = NetUtil.localIps();
        Set<String> localIpv4s = NetUtil.localIpv4s();

        Map<String, Object> result = new HashMap<>();
        result.put("clientIp", clientIp);
        result.put("localIps", localIps);
        result.put("localIpv4s", localIpv4s);

        return result;
    }

}
