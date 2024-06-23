package com.properties.payments.controller;

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

import com.properties.payments.constant.PaymentConstant;
import com.properties.payments.dto.PaymentRequest;
import com.properties.payments.dto.ErrorResponseDto;
import com.properties.payments.dto.ResponseDto;
import com.properties.payments.model.Payment;
import com.properties.payments.service.PaymentServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentServiceImpl PaymentService;

	@Operation(summary = "Payment REST API for rgister", description = "REST API to book new Payment")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PostMapping
	public ResponseEntity<ResponseDto> makePayment(@RequestBody PaymentRequest PaymentReq) {
		ResponseDto responseDto = new ResponseDto();
		Payment Payment = PaymentService.payment(PaymentReq);
		if (Payment != null) {
			responseDto.setStatusMsg(PaymentConstant.PAYMENT_MESSAGE_201);
			responseDto.setStatusCode(PaymentConstant.PAYMENT_STATUS_201);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@Operation(summary = "Payment REST API list Payment details", description = "REST API to list all Payment details")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping
	public ResponseEntity<List<Payment>> listAllPayment() {
		List<Payment> payments = PaymentService.listAllPayment();
		return ResponseEntity.status(HttpStatus.OK).body(payments);
	}

	@Operation(summary = "Payment REST API get Payment By Id", description = "REST API to find Payment by Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable("id") Integer id) {
		Payment payment = PaymentService.getPaymentById(id);
		return ResponseEntity.status(HttpStatus.OK).body(payment);
	}

	@Operation(summary = "Payment REST API delete Payment by id", description = "REST API to delete Payment by id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> PaymentPayment(@PathVariable("id") int id) {
		String response = PaymentService.deletePayment(id);
		ResponseDto responseDto = new ResponseDto();
		if (response.equals("SUCCESS")) {
			responseDto.setStatusMsg(PaymentConstant.PAYMENT_MESSAGE_200);
			responseDto.setStatusCode(PaymentConstant.PAYMENT_STATUS_200);
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@Operation(summary = "Payment REST API to update Payment", description = "REST API to update Payment")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PutMapping("/{id}")
	public ResponseEntity<Payment> updatePayment(@RequestBody PaymentRequest PaymentReq,
			@PathVariable("id") int id) {
		Payment payment = PaymentService.updatePayment(PaymentReq, id);
		return ResponseEntity.status(HttpStatus.OK).body(payment);
	}
}
