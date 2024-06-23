package com.properties.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.properties.booking.config.PaymentFeignClient;
import com.properties.booking.config.PropertyFeignClientConfig;
import com.properties.booking.constant.BookingConstant;
import com.properties.booking.dto.BookingRequest;
import com.properties.booking.dto.PaymentRequest;
import com.properties.booking.dto.Property;
import com.properties.booking.dto.PropertyRequest;
import com.properties.booking.dto.ResponseDto;
import com.properties.booking.dtoMapper.BookingMapper;
import com.properties.booking.exception.BookingDataNotFoundException;
import com.properties.booking.model.Booking;
import com.properties.booking.repositroy.BookingRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements PropertyServices {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private PropertyFeignClientConfig propertyFeignClientConfig;
	
	@Autowired
	private PaymentFeignClient paymentFeignClient;

	@Override
	@Transactional
	public Booking booking(BookingRequest bookingReq) {
		Booking newBooking = null;
		ResponseEntity<Property> propertyObj = propertyFeignClientConfig.getPropertyById(bookingReq.getPropertyId());

		if (propertyObj == null) {
			throw new BookingDataNotFoundException("The property you are trying to book if Not Available for Booking.",
					BookingConstant.BOOKING_NOT_FOUND_EXIT_CODE);
		} else {
			if (bookingReq.getNumRooms() > propertyObj.getBody().getAvailableRooms()) {
				throw new BookingDataNotFoundException("There is no room Available for booking",
						BookingConstant.BOOKING_NOT_FOUND_EXIT_CODE);
			}
			if (propertyObj.getBody().isLockForBook()) {
				throw new BookingDataNotFoundException(
						"Booking is currently unavailable. We apologize for the inconvenience and kindly ask you to try again later.",
						BookingConstant.BOOKING_NOT_FOUND_EXIT_CODE);
			}
			PropertyRequest propertyRequest = new PropertyRequest();
			propertyRequest.setLockForBook(true);
			propertyFeignClientConfig.updateProperty(propertyRequest, bookingReq.getPropertyId());
			newBooking = BookingMapper.bookingRequestToBookingMapper(bookingReq, new Booking());
			newBooking.setBookingStatus("Initiated");
			Booking saveBooking = bookingRepository.save(newBooking);
			if (saveBooking != null && saveBooking.getBookingStatus().equals("Initiated")) {
				ResponseEntity<ResponseDto> payment = paymentFeignClient
						.makePayment(new PaymentRequest(saveBooking.getId(), propertyObj.getBody().getPrice()));
				if(payment.getBody().getStatusCode().equals("PAY_201")) {
					propertyRequest.setLockForBook(false);
					propertyRequest.setAvailableRooms(propertyObj.getBody().getAvailableRooms() - bookingReq.getNumRooms());
					propertyFeignClientConfig.updateProperty(propertyRequest, bookingReq.getPropertyId());
					saveBooking.setBookingStatus("Booked");
					bookingRepository.save(saveBooking);
				}else {
					propertyRequest.setLockForBook(false);
					propertyRequest.setAvailableRooms(propertyObj.getBody().getAvailableRooms());
					propertyFeignClientConfig.updateProperty(propertyRequest, bookingReq.getPropertyId());
					saveBooking.setBookingStatus("Failed");
					bookingRepository.save(saveBooking);
				}
			}
		}

		return newBooking;
	}

	@Override
	public List<Booking> listAllBooking() {
		List<Booking> listOfBooking = bookingRepository.findAll();
		if (listOfBooking.size() == 0) {
			throw new BookingDataNotFoundException(
					"There is no Booking Register with us. So, Please keep some patience to see the latest and best property",
					BookingConstant.BOOKING_NOT_FOUND_EXIT_CODE);
		}
		return listOfBooking;
	}

	@Override
	public Booking getBookingById(Integer b_id) {
		Booking property = bookingRepository.findById(b_id)
				.orElseThrow(() -> new BookingDataNotFoundException("The Property You are tyring to find is not found",
						BookingConstant.BOOKING_NOT_FOUND_EXIT_CODE));

		return property;
	}

	@Override
	public String deleteBooking(int propertyId) {
		String response = "";
		Booking booking = bookingRepository.findById(propertyId).orElseThrow(() -> new BookingDataNotFoundException(
				"The Property You are tyring to delete is not found", BookingConstant.BOOKING_NOT_FOUND_EXIT_CODE));
		if (booking != null) {
			bookingRepository.delete(booking);
			response = "SUCCESS";
		}
		return response;
	}

	@Override
	public Booking updateBooking(Booking bookingReq, int p_id) {
		Booking property = bookingRepository.findById(p_id).orElseThrow(() -> new BookingDataNotFoundException(
				"The Property You are tyring to delete is not found", BookingConstant.BOOKING_NOT_FOUND_EXIT_CODE));
		bookingRepository.save(bookingReq);
		return bookingReq;
	}

}
