package com.properties.property.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Property {

	private int id; // primary key , auto increment
	private String title; // title of the property
	private String location;
	private String type;// (Apartment, House, Villa, etc.)
	private double price; // Price Per Night
	private int availableRooms; // Number of Rooms available
	private boolean lockForBook; // lock flag for booking
}
