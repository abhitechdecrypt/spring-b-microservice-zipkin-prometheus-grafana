package com.properties.payments.service;

import java.util.List;

import com.properties.payments.dto.PaymentRequest;
import com.properties.payments.model.Payment;

public interface PaymentServices {

	/**
	 * <h1>Post Saves a Property</h1>
	 * 
	 * @param property
	 * @return Property
	 */
	Payment payment(PaymentRequest property);

	/**
	 * <h1>Get gives all Properties</h1>
	 * 
	 * @return List<Property>
	 */
	List<Payment> listAllPayment();

	/**
	 * <h1>Get information about a particular Property</h1>
	 * 
	 * @param p_id
	 * @return Property
	 */
	Payment getPaymentById(Integer p_id);

	/**
	 * <h1>Delete Deletes a Property</h1>
	 * 
	 * @param property
	 * @return Property
	 */
	String deletePayment(int propertyId);

	/**
	 * <h1>Put updates a particular Property</h1>
	 * 
	 * @param property
	 * @return Property
	 */
	Payment updatePayment(PaymentRequest paymentRequest, int p_id);
}
