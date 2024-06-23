package com.properties.payments;

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
				title = "Payment microservice REST API Documentation",
				description = "Properties payment microservice REST API Documentation",
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
				description = "Paymnet microservice REST API Documentation",
				url = "https://www.demoproperty.com/swagger-ui.html"
		)
)
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

}
