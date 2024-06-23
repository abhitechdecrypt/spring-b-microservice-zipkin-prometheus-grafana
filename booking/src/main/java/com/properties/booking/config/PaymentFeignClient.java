package com.properties.booking.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.properties.booking.dto.PaymentRequest;
import com.properties.booking.dto.ResponseDto;

@FeignClient(name = "PAYMENT", path = "/payment")
public interface PaymentFeignClient {

	@PostMapping
	public ResponseEntity<ResponseDto> makePayment(@RequestBody PaymentRequest PaymentReq);

}
