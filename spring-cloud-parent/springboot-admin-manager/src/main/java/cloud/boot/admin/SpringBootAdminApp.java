package cloud.boot.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootAdminApp {
    public static void main(String[] args){
        SpringApplication.run(SpringBootAdminApp.class);
    }
}
