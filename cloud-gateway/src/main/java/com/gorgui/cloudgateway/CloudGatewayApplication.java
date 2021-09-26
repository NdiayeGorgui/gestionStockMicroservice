package com.gorgui.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudGatewayApplication {

	/*//configuration static
	@Bean
	RouteLocator routes(RouteLocatorBuilder builder){
		return builder.routes()
			    //.route(r->r.path("/customers/**").uri("http://localhost:8081"))
				//.route(r->r.path("/products/**").uri("lb://localhost:8082"))
				//lb signifie loud balancer
				.route(r->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
				.route(r->r.path("/products/**").uri("lb://INVENTORY-SERVICE"))
				.build();

	}*/
	//configuration dynamique pour trouver les routes
	@Bean
	DiscoveryClientRouteDefinitionLocator dymamicRoutes(ReactiveDiscoveryClient rdc,
														DiscoveryLocatorProperties dlp){
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

}
