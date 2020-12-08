package com.boot.netty;

import com.boot.netty.servers.TcpServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@SpringBootApplication
@EnableDiscoveryClient
public class NettyBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(NettyBootApplication.class, args);
	}

	private final TcpServer tcpServer;

	@Bean
	public ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(){
		return applicationReadyEvent -> {
			new Thread(tcpServer::start).start();
		};
	}

}
