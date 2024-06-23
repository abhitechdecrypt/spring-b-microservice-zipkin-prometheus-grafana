package com.properties.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Booking microservice REST API Documentation",
				description = "Properties Booking microservice REST API Documentation",
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
				description = "Booking microservice REST API Documentation",
				url = "https://www.demoproperty.com/swagger-ui.html"
		)
)
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

}
