package com.bbc.bbcops.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.bbc.bbcops.model.Customer;

@Repository
public class OTPDao {

	private SessionFactory sessionFactory;

	public OTPDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public String checkOTP(Long customerId) {
		try (Session session = sessionFactory.openSession()) {
			Customer customer = session.get(Customer.class, customerId);
			if (customer != null) {
				return customer.getOtp();
			} else {
				return null;
			}
		}
	}

}
