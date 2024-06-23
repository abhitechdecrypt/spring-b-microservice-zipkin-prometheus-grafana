package com.properties.property.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(name = "Property",
description = "Schema to hold Property information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {
	
	@NotNull(message = "Title Filed can't be empty")
	@Schema(
            description = "title of property", example = "Dream House"
    )
	private String title; // title of the property
	
	@NotNull(message = "Location Filed can't be Empty")
	@Schema(
            description = "Location of property", example = "New Delhi"
    )
	private String location;
	
	@NotNull(message = "Apartment can't be Empty")
	@Schema(
            description = "Type of property", example = "Apartment"
    )
	private String type;// (Apartment, House, Villa, etc.)
	
	@NotNull(message = "price can't be empty")
	@Schema(
            description = "Price of property", example = "20342.3"
    )
	private double price; // Price Per Night
	
	@NotNull(message = "Available Rooms can't be empty")
	@Schema(
            description = "Available Rooms of property", example = "12"
    )
	private int availableRooms; // Number of Rooms available
	
	@Schema(
            description = "Lock for Book of property", example = "true/false"
    )
	private boolean lockForBook; // lock flag for booking
}



