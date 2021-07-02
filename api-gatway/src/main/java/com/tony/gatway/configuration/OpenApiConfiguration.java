package com.tony.gatway.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class OpenApiConfiguration {

	@Bean
	@Lazy(false) //para ser carregado assim que o API gateway ser carregado
	public List<GroupedOpenApi> apis(
			SwaggerUiConfigParameters config,
			RouteDefinitionLocator locator){
		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
		definitions.stream().filter(
				routeDefinition -> routeDefinition.getId().matches(".*-service"))//pega as rotas que tem service no nome
				.forEach(routeDefinition -> {
					String name = routeDefinition.getId();
					config.addGroup(name);
					GroupedOpenApi.builder().pathsToMatch("/"+name+"/**")
					.group(name).build();
				}
				);
		return new ArrayList<>();
	}
	
	
}
