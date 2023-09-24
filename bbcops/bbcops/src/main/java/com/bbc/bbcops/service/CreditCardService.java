package com.bbc.bbcops.service;
import org.springframework.stereotype.Service;

import com.bbc.bbcops.dao.CreditCardDao;
import com.bbc.bbcops.model.CreditCard;

@Service
public class CreditCardService {

	private CreditCardDao creditCardDao;

	public CreditCardService(CreditCardDao creditCardDao) {
		super();
		this.creditCardDao = creditCardDao;
	}

	public boolean isCreditCardValid(Long customerId, CreditCard creditCard) {
		return creditCardDao.isCreditCardValid(customerId,creditCard);
	}

	 public String payBillByCreditCard(Long customerId, Long billId) {
		 return creditCardDao.payBillByCreditCard(customerId,billId);
	 }

}
