package com.properties.payments.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Payment", description = "Schema to hold paymnet information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

	@NotNull(message = "Title Filed can't be empty")
	@Schema(description = "bookingId of property", example = "BK-023.")
	private int bookingId; // title of the property
	
	@NotNull(message = "bookingStatus can't be empty")
	@Schema(description = "bookingStatus of property", example = "2344")
	private double amount; 
}
