package com.bbc.bbcops.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbc.bbcops.service.OTPService;

@CrossOrigin()
@RestController
@RequestMapping("/bbc/customer/email")
public class OTPController {

	private OTPService otpService;
	
	
	@Autowired
	public OTPController(OTPService otpService) {
		super();
		this.otpService = otpService;
	}

	@PostMapping("{customerId}/otp")
	public Boolean checkOTP(@PathVariable Long customerId,@RequestParam String otp) {
		return otpService.checkOTP(customerId,otp);
	}
		
}
