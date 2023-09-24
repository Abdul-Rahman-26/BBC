package com.bbc.bbcops.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbc.bbcops.dao.WalletDao;
import com.bbc.bbcops.model.Wallet;

@Service
public class WalletService {

	private WalletDao walletDao;
	@Autowired
	public WalletService(WalletDao walletDao) {
		super();
		this.walletDao = walletDao;
	}

	public boolean checkWallet(Long customerId, Wallet wallet) {
		return walletDao.checkWallet(customerId,wallet);
	}

	 public String payBillByWallet(Long customerId, Long billId) {
		 return walletDao.payBillByWallet(customerId, billId);
	 }
}
