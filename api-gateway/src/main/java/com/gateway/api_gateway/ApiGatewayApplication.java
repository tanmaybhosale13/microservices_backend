package com.gateway.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {

		// 🎯 CRITICAL FOR WEBFLUX/NETTY: Forces tracing IDs to hop across asynchronous threads
		reactor.core.publisher.Hooks.enableAutomaticContextPropagation();
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
