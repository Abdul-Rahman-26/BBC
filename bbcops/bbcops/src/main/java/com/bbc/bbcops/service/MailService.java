package com.bbc.bbcops.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bbc.bbcops.dao.MailDao;

@Service
public class MailService {

	@Value("${spring.mail.username}")
	private String fromEmail;
	
	private JavaMailSender javaMailSender;
	private MailDao mailDao;

	@Autowired
	public MailService(JavaMailSender javaMailSender, MailDao mailDao) {
		super();
		this.javaMailSender = javaMailSender;
		this.mailDao = mailDao;
	}


	public String sendMail(Long customerId, String to, String[] cc, String subject, String body) {
	    try {
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
	        mimeMessage.setFrom(fromEmail);
	        to = mailDao.getToAddress(customerId);
	        body = mailDao.getBody(customerId);
	        
	        if (to == null || body == null) {
	            return "Data not found";
	        }
	        
	        mimeMessageHelper.setTo(to);
	        mimeMessageHelper.setCc(to);
	        mimeMessageHelper.setSubject("OTP");
	        mimeMessageHelper.setText(body);
	        javaMailSender.send(mimeMessage);
	        return "Mail Send for Otp";
	    } catch (Exception e) {
	        throw new  MailSendException("Error sending email", e);
	    }
	}


}
