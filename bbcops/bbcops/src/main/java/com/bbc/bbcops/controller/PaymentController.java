package com.bbc.bbcops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbc.bbcops.model.Payment;
import com.bbc.bbcops.service.PaymentService;

@CrossOrigin()
@RestController
@RequestMapping("/bbc/customer/bills")
public class PaymentController {
	
	private PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}
	
	@GetMapping("{customerId}/payment")
	public List<Payment> getAllPaymentsByCustomerId(@PathVariable Long customerId) {
		return paymentService.getAllPaymentsByCustomerId(customerId);
	}
	

}
