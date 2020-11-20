package sentinel.gateway.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelGatewayApp {
    public static void main(String[] args){
        SpringApplication.run(SentinelGatewayApp.class);
    }
}
