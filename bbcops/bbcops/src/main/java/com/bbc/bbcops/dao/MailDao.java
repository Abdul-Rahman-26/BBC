package com.bbc.bbcops.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbc.bbcops.model.Customer;

@Repository
public class MailDao {

	private SessionFactory sessionFactory;
	@Autowired
	public MailDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public String getToAddress(Long customerId) {
		try(Session session = sessionFactory.openSession()){
			 Customer customer = session.get(Customer.class, customerId);
	            if (customer != null) {
	            	return customer.getEmail();
	            }else {
	            	return null;
	            }
		}
	}

	public String getBody(Long customerId) {
		try(Session session = sessionFactory.openSession()){
			 Customer customer = session.get(Customer.class, customerId);
	            if (customer != null) {
	            	return customer.getOtp();
	            }else {
	            	return null;
	            }
		}
}
}