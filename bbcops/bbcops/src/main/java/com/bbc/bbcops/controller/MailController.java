package com.bbc.bbcops.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbc.bbcops.service.MailService;

@CrossOrigin()
@RestController
@RequestMapping("/bbc/customer/email")
public class MailController {

	private MailService mailService;

	@Autowired
	public MailController(MailService mailService) {
		super();
		this.mailService = mailService;
	}
	
	@GetMapping("send/{customerId}")
	public String sendMail(@PathVariable Long customerId, String to, String[] cc,String subject, String body) {
		return mailService.sendMail(customerId,to,cc,subject,body);
	}
	
	
}
