package com.properties.booking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Property", description = "Schema to hold Booking information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

	@NotNull(message = "Title Filed can't be empty")
	@Schema(description = "bookingDescription of property", example = "make you trip a memorable one.")
	private String bookingDescription; // title of the property

	@NotNull(message = "orderNumber Filed can't be Empty")
	@Schema(description = "Location of property", example = "BK-01")
	private String orderNumber;

	@NotNull(message = "propertyId can't be Empty")
	@Schema(description = "propertyId of property", example = "1")
	private int propertyId;

	@NotNull(message = "numRooms Rooms can't be empty")
	@Schema(description = "numRooms Rooms of property", example = "12")
	private int numRooms; // Number of Rooms available
}
