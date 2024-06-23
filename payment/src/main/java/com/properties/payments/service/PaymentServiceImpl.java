package com.properties.payments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.properties.payments.constant.PaymentConstant;
import com.properties.payments.dto.PaymentRequest;
import com.properties.payments.dtoMapper.PaymentMapper;
import com.properties.payments.exception.PaymentDataNotFoundException;
import com.properties.payments.model.Payment;
import com.properties.payments.repositroy.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImpl implements PaymentServices {

	@Autowired
	private PaymentRepository PaymentRepository;

	@Override
	@Transactional
	public Payment payment(PaymentRequest PaymentReq) {
		Payment payment = PaymentMapper.paymentRequestToPaymentMapper(PaymentReq, new Payment());
		PaymentRepository.save(payment);
		return payment;
	}

	@Override
	public List<Payment> listAllPayment() {
		List<Payment> listOfPayment = PaymentRepository.findAll();
		if (listOfPayment.size() == 0) {
			throw new PaymentDataNotFoundException(
					"There is no Payment Register with us. So, Please keep some patience to see the latest and best property",
					PaymentConstant.PAYMENT_NOT_FOUND_EXIT_CODE);
		}
		return listOfPayment;
	}

	@Override
	public Payment getPaymentById(Integer b_id) {
		Payment property = PaymentRepository.findById(b_id)
				.orElseThrow(() -> new PaymentDataNotFoundException("The Payment You are tyring to find is not found",
						PaymentConstant.PAYMENT_NOT_FOUND_EXIT_CODE));

		return property;
	}

	@Override
	public String deletePayment(int propertyId) {
		String response = "";
		Payment payment = PaymentRepository.findById(propertyId).orElseThrow(() -> new PaymentDataNotFoundException(
				"The Property You are tyring to delete is not found", PaymentConstant.PAYMENT_NOT_FOUND_EXIT_CODE));
		if (payment != null) {
			PaymentRepository.delete(payment);
			response = "SUCCESS";
		}
		return response;
	}

	@Override
	public Payment updatePayment(PaymentRequest PaymentReq, int p_id) {
		Payment property = PaymentRepository.findById(p_id).orElseThrow(() -> new PaymentDataNotFoundException(
				"The Property You are tyring to delete is not found", PaymentConstant.PAYMENT_NOT_FOUND_EXIT_CODE));
		Payment Payment = PaymentMapper.paymentRequestToPaymentMapper(PaymentReq, property);
		PaymentRepository.save(Payment);
		return Payment;
	}

}
