package com.Pedidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI configDoc() {
		return new OpenAPI().info(new Info().title("Pedidos")
				.description("Api para tener un control de los pedidos ademas de un inventario de los producto")
				.version("1.0.0"));
	}
}
