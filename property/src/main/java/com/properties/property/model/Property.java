package com.properties.property.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // primary key , auto increment
	
	@NotNull(message = "Title Filed can't be empty")
	private String title; // title of the property
	
	@NotNull(message = "Location Filed can't be Empty")
	private String location;
	
	@NotNull(message = "Apartment can't be Empty")
	private String type;// (Apartment, House, Villa, etc.)
	
	@NotNull(message = "price can't be empty")
	private double price; // Price Per Night
	
	@NotNull(message = "Available Rooms can't be empty")
	private int availableRooms; // Number of Rooms available
	private boolean lockForBook; // lock flag for booking
}
