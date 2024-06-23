package com.properties.payments.dtoMapper;

import com.properties.payments.dto.PaymentRequest;
import com.properties.payments.model.Payment;

public class PaymentMapper {

	public static Payment paymentRequestToPaymentMapper(PaymentRequest paymentReq, Payment payment) {
		payment.setAmount(paymentReq.getAmount());
		payment.setBookingId(paymentReq.getBookingId());

		return payment;
	}
}
