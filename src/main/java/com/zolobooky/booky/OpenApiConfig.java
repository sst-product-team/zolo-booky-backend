package com.zolobooky.booky;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Value("${booky.openapi.dev-url}")
	private String devURL;

	@Value("${booky.openapi.prod-url}")
	private String prodURL;

	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl(devURL);
		devServer.setDescription("Server URL in Development environment");

		Server prodServer = new Server();
		prodServer.setUrl(prodURL);
		prodServer.setDescription("Server URL in Production environment");

		Contact contact = new Contact();
		contact.setEmail("shrinibasmahanta2004@gmail.com");
		contact.setName("Zolo-Backend-Team");

		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		Info info = new Info().title("Zolo Booky book sharing API")
			.version("0.0.1")
			.contact(contact)
			.description("This API exposes endpoints to manage book sharing.")
			.license(mitLicense);

		return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	}

}
