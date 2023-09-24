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

import com.bbc.bbcops.model.Wallet;
import com.bbc.bbcops.service.WalletService;

@CrossOrigin()
@RestController
@RequestMapping("/bbc/customer/bills")
public class WalletController {

	private WalletService walletService;
	@Autowired
	public WalletController(WalletService walletService) {
		super();
		this.walletService = walletService;
	}
	
	@PostMapping("/{customerId}/check-wallet")
	public boolean checkWallet(@PathVariable Long customerId,@RequestBody Wallet wallet) {
		return walletService.checkWallet(customerId,wallet);
	}
	
	@PutMapping("/{customerId}/{billId}/wallet-pay")
	 public String payBillByWallet(@PathVariable Long customerId,@PathVariable Long billId) {
		 return walletService.payBillByWallet(customerId, billId);
	 }
}
