package com.properties.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.properties.booking.constant.BookingConstant;
import com.properties.booking.dto.BookingRequest;
import com.properties.booking.dto.ErrorResponseDto;
import com.properties.booking.dto.ResponseDto;
import com.properties.booking.model.Booking;
import com.properties.booking.service.BookingServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookingController {

	@Autowired
	private BookingServiceImpl bookingService;

	@Operation(summary = "Booking REST API for rgister", description = "REST API to book new Booking")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	 @Retry(name = "myBookingRetry",fallbackMethod = "myBookingRetryFallback")
	@CircuitBreaker(name = "myBookingcircuitBreaker", fallbackMethod = "myBookingFallback")
	@PostMapping
	public ResponseEntity<?> bookBooking(@RequestBody BookingRequest bookingReq) {
		ResponseDto responseDto = new ResponseDto();
		Booking Booking = bookingService.booking(bookingReq);
		if (Booking != null) {
			responseDto.setStatusMsg(BookingConstant.BOOKING_MESSAGE_201);
			responseDto.setStatusCode(BookingConstant.BOOKING_STATUS_201);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	public ResponseEntity<?> myBookingFallback(Exception exception) {
		Booking booking = Booking.builder().bookingDescription("Testing")
			.bookingStatus("Failed")
			.id(0)
			.numRooms(1)
			.orderNumber("234")
			.propertyId(2).build();
		log.info("I am in the fallback of booking service {} ",booking);
		return ResponseEntity.status(HttpStatus.CREATED).body(booking);
	}

	public ResponseEntity<?> myBookingRetryFallback(Exception exception) {
		Booking booking = Booking.builder().bookingDescription("Testing")
				.bookingStatus("Failed")
				.id(0)
				.numRooms(1)
				.orderNumber("234")
				.propertyId(2).build();
		log.info("I am in the fallback of booking service  retry {} ",booking);
		return ResponseEntity.status(HttpStatus.CREATED).body(booking);
	}

	@Operation(summary = "Booking REST API list booking details", description = "REST API to list all booking details")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping
	public ResponseEntity<List<Booking>> listAllBooking() {
		List<Booking> bookings = bookingService.listAllBooking();
		return ResponseEntity.status(HttpStatus.OK).body(bookings);
	}

	@Operation(summary = "Booking REST API get Booking By Id", description = "REST API to find booking by Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable("id") Integer id) {
		Booking Booking = bookingService.getBookingById(id);
		return ResponseEntity.status(HttpStatus.OK).body(Booking);
	}

	@Operation(summary = "Booking REST API delete Booking by id", description = "REST API to delete Booking by id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> bookingBooking(@PathVariable("id") int id) {
		String response = bookingService.deleteBooking(id);
		ResponseDto responseDto = new ResponseDto();
		if (response.equals("SUCCESS")) {
			responseDto.setStatusMsg(BookingConstant.BOOKING_MESSAGE_200);
			responseDto.setStatusCode(BookingConstant.BOOKING_STATUS_200);
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@Operation(summary = "Booking REST API to update booking", description = "REST API to update booking")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PutMapping("/{id}")
	public ResponseEntity<Booking> updateBooking(@RequestBody Booking bookingReq, @PathVariable("id") int id) {
		Booking booking = bookingService.updateBooking(bookingReq, id);
		return ResponseEntity.status(HttpStatus.OK).body(booking);
	}
}
