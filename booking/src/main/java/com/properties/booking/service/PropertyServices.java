package com.properties.booking.service;

import java.util.List;

import com.properties.booking.dto.BookingRequest;
import com.properties.booking.model.Booking;

public interface PropertyServices {

	/**
	 * <h1>Post Saves a Property</h1>
	 * 
	 * @param property
	 * @return Property
	 */
	Booking booking(BookingRequest property);

	/**
	 * <h1>Get gives all Properties</h1>
	 * 
	 * @return List<Property>
	 */
	List<Booking> listAllBooking();

	/**
	 * <h1>Get information about a particular Property</h1>
	 * 
	 * @param p_id
	 * @return Property
	 */
	Booking getBookingById(Integer p_id);

	/**
	 * <h1>Delete Deletes a Property</h1>
	 * 
	 * @param property
	 * @return Property
	 */
	String deleteBooking(int propertyId);

	/**
	 * <h1>Put updates a particular Property</h1>
	 * 
	 * @param property
	 * @return Property
	 */
	Booking updateBooking(Booking bookingRequest, int p_id);
}
