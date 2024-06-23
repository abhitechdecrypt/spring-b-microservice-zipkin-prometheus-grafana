package com.properties.booking.dtoMapper;

import com.properties.booking.dto.BookingRequest;
import com.properties.booking.model.Booking;

public class BookingMapper {

	public static Booking bookingRequestToBookingMapper(BookingRequest bookingReq, Booking booking) {
		booking.setBookingDescription(bookingReq.getBookingDescription());
		booking.setNumRooms(bookingReq.getNumRooms());
		booking.setOrderNumber(bookingReq.getOrderNumber());
		booking.setPropertyId(bookingReq.getPropertyId());

		return booking;
	}
}
