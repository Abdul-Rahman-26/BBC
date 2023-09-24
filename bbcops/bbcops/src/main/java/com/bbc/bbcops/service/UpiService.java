package com.bbc.bbcops.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbc.bbcops.dao.UpiDao;
import com.bbc.bbcops.model.Upi;

@Service
public class UpiService {

	private UpiDao upiDao;
	
	@Autowired
	public UpiService(UpiDao upiDao) {
		super();
		this.upiDao = upiDao;
	}


	public boolean checkUpi(Long customerId, Upi upi) {
		return upiDao.checkUpi(customerId,upi);
	}

	
	public String payBillByUpi(Long customerId, Long billId) {
		return upiDao.payBillByUpi(customerId,billId);
	}
}
