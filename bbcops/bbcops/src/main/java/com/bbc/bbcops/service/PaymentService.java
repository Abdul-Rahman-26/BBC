package com.bbc.bbcops.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bbc.bbcops.dao.PaymentDao;
import com.bbc.bbcops.model.Payment;

@Service
public class PaymentService {

	private PaymentDao paymentDao;
	
	 public PaymentService(PaymentDao paymentDao) {
		super();
		this.paymentDao = paymentDao;
	}

	public List<Payment> getAllPaymentsByCustomerId(Long customerId) {
		return paymentDao.getAllPaymentsByCustomerId(customerId);
	}
		   
}
