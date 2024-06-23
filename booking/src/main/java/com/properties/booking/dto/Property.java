package com.properties.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {

	private int id;

	private String title;

	private String location;

	private String type;

	private double price;

	private int availableRooms;
	private boolean lockForBook;
}
