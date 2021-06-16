package com.tony.gatway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApyGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
					.filters(f -> f.addRequestHeader("Hello", "World")
									.addRequestParameter("Hello", "World Parameter"))
					.uri("http://httpbin.org:80"))
				.route(p -> p.path("/cambio-service/**")
						.uri("lb://cambio-service")) //passando nome do serviço registrado no eureka
				.route(p -> p.path("/book-service/**")
						.uri("lb://book-service")) //passando nome do serviço registrado no eureka
				.build();
	}

}
