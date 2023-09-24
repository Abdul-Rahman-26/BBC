package com.bbc.bbcops.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbc.bbcops.model.Upi;
import com.bbc.bbcops.service.UpiService;

@CrossOrigin()
@RestController
@RequestMapping("/bbc/customer/bills")
public class UpiController {

	private UpiService upiService;
	
	@Autowired
	public UpiController(UpiService upiService) {
		super();
		this.upiService = upiService;
	}
	
	@PostMapping("{customerId}/check-upi")
	public boolean checkUpi(@PathVariable Long customerId, @RequestBody Upi upi) {
		return upiService.checkUpi(customerId,upi);
	}
	
	@PutMapping("/{customerId}/{billId}/upi-pay")
	public String payBillByUpi(@PathVariable Long customerId,@PathVariable Long billId) {
		return upiService.payBillByUpi(customerId, billId);
	}
}
