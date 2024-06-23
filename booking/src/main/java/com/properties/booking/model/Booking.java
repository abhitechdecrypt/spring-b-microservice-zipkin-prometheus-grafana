package com.properties.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // primary key, auto increment
    
    private String bookingDescription; // Booking description
    
    @NotNull(message = "Order number cannot be empty")
    private String orderNumber; // Booking number like BK-01
    
    @NotNull(message = "Property ID cannot be empty")
	private int propertyId; // primary key of the property table
    
    @NotNull(message = "Number of rooms cannot be empty")
    private int numRooms; // required number of rooms
    
    @NotNull(message = "Booking status cannot be empty")
    private String bookingStatus; // Booking status like “Initiated”, ”Booked”, ”Failed”
}

