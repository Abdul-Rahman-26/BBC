package com.bbc.bbcops.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbc.bbcops.dao.CustomerDao;
import com.bbc.bbcops.model.CreditCard;
import com.bbc.bbcops.model.Upi;
import com.bbc.bbcops.model.Wallet;

@Service
public class CustomerService {
	
	private CustomerDao customerDao;
	
	@Autowired
	public CustomerService(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	public String getCustomerNameByCustomerId(Long customerId) {
		return customerDao.getCustomerNameByCustomerId(customerId);
	}

	

	public String addCreditCard(Long customerId, CreditCard creditcard) {
		return customerDao.addCreditCard(customerId,creditcard);
	}
	
	public String changeCreditCard(Long customerId, CreditCard creditcard) {
		return customerDao.changeCreditCard(customerId,creditcard);
	}
	
	public String removeCreditCard(Long customerId) {
		return customerDao.removeCreditCard(customerId);
	}

	public String addWallet(Long customerId, Wallet wallet) {
		return customerDao.addWallet(customerId, wallet);
	}

	public String changeWallet(Long customerId, Wallet wallet) {
		return customerDao.changeWallet(customerId,wallet);
	}

	public String removeWallet(Long customerId) {
		return customerDao.removeWallet(customerId);
	}
	
    public String addUPI(Long customerId, Upi upi) {
        return customerDao.addUPI(customerId, upi);
    }

    public String removeUPI(Long customerId) {
        return customerDao.removeUPI(customerId);
    }

    public String changeUPI(Long customerId, Upi updatedUPI) {
        return customerDao.changeUPI(customerId, updatedUPI);
    }


	public Boolean login(Long customerId) {
		return customerDao.login(customerId);
	}

}
