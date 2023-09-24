package com.bbc.bbcops.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbc.bbcops.model.CreditCard;
import com.bbc.bbcops.service.CreditCardService;

@CrossOrigin()
@RestController
@RequestMapping("/bbc/customer/bills")
public class CreditCardController {

	private CreditCardService creditCardService;

	public CreditCardController(CreditCardService creditCardService) {
		super();
		this.creditCardService = creditCardService;
	}

	@PostMapping("/{customerId}/check-credit-card")
	public boolean checkCreditCardExistence(@PathVariable Long customerId, @RequestBody CreditCard creditCard) {
		return creditCardService.isCreditCardValid(customerId, creditCard);
	}
	
	@PutMapping("/{customerId}/{billId}/credit-card-pay")
	 public String payBillByCreditCard(@PathVariable Long customerId, @PathVariable Long billId) {
		 return creditCardService.payBillByCreditCard(customerId,billId);
	 }
}
