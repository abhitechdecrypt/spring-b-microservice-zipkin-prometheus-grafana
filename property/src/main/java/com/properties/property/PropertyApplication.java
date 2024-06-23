package com.properties.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Property microservice REST API Documentation",
				description = "Properties Property microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Abhishek kumar",
						email = "abhishek.kumar@gmail.com",
						url = "https://www.demoproperty.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.demoproperty.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Property microservice REST API Documentation",
				url = "https://www.demoproperty.com/swagger-ui.html"
		)
)
public class PropertyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyApplication.class, args);
	}

}
