package com.bbc.bbcops.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbc.bbcops.model.CreditCard;
import com.bbc.bbcops.model.Upi;
import com.bbc.bbcops.model.Wallet;
import com.bbc.bbcops.service.CustomerService;

@CrossOrigin()
@RestController
@RequestMapping("/bbc/customer")
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping("login")
	public Boolean login(@RequestParam Long customerId) {
		return customerService.login(customerId);
	}
	
	@GetMapping("/{customerId}/getname")
	public String getCustomerNameByCustomerId(@PathVariable Long customerId) {
		return customerService.getCustomerNameByCustomerId(customerId);
	}
	
	//Not using purpose for trail
	@PostMapping("{customerId}/credit-card/add-card")
	public String addCreditCard(@PathVariable Long customerId, @RequestBody CreditCard creditcard) {
		return customerService.addCreditCard(customerId, creditcard);
	}

	@PostMapping("{customerId}/credit-card/change-card")
	public String changeCreditCard(@PathVariable Long customerId, @RequestBody CreditCard creditcard) {
		return customerService.changeCreditCard(customerId, creditcard);
	}

	@DeleteMapping("{customerId}/credit-card/remove-card")
	public String removeCreditCard(@PathVariable Long customerId) {
		return customerService.removeCreditCard(customerId);
	}
	
	@PostMapping("{customerId}/wallet/add-wallet")
	public String addWallet(@PathVariable Long customerId, @RequestBody Wallet wallet) {
		return customerService.addWallet(customerId, wallet);
	}

	@PostMapping("{customerId}/wallet/change-wallet")
	public String changeWallet(@PathVariable Long customerId, @RequestBody Wallet wallet) {
		return customerService.changeWallet(customerId, wallet);
	}

	@DeleteMapping("{customerId}/wallet/delete-wallet")
	public String removeWallet(@PathVariable Long customerId) {
		return customerService.removeWallet(customerId);
	}
	
	@PostMapping("{customerId}/upi/add-upi")
    public String addUPI(Long customerId, Upi upi) {
        return customerService.addUPI(customerId, upi);
    }
	@PostMapping("{customerId}/upi/change-upi")
    public String removeUPI(Long customerId) {
        return customerService.removeUPI(customerId);
    }
	@DeleteMapping("{customerId}/upi/remove-upi")
    public String changeUPI(Long customerId, Upi updatedUPI) {
        return customerService.changeUPI(customerId, updatedUPI);
    }

	


}
