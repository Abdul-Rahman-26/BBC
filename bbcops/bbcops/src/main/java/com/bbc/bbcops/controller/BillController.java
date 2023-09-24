package com.bbc.bbcops.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbc.bbcops.model.Bill;
import com.bbc.bbcops.service.BillService;

@CrossOrigin()
@RestController
@RequestMapping("/bbc/customer")
public class BillController {

	private BillService billService;

	public BillController(BillService billService) {
		super();
		this.billService = billService;
	}
	
	@GetMapping("/{customerId}/bills")
	public List<Bill> getBillsByCustomerId(@PathVariable Long customerId) {
		return billService.getBillsByCustomerId(customerId);
	}
	
	@GetMapping("/{customerId}/bills/paid")
	public List<Bill> getPaidBillsByCustomerId(@PathVariable Long customerId){
		return billService.getPaidBillsByCustomerId(customerId);
	}
	
	@GetMapping("/{customerId}/bills/not-paid")
	public List<Bill> getNotPaidBillsByCustomerId(@PathVariable Long customerId){
		return billService.getNotPaidBillsByCustomerId(customerId);
	}
	
	@GetMapping("/{customerId}/bills/bill-count")
	public Long getBillsCount(@PathVariable Long customerId) {
		return billService.getBillsCount(customerId);
	}
	
	@GetMapping("/{customerId}/bills/paid-count")
	public Long getPaidBillsCount(@PathVariable Long customerId){
		return billService.getPaidBillsCount(customerId);
	}
	
	@GetMapping("/{customerId}/bills/not-paid-count")
	public Long getNotPaidBillsCount(@PathVariable Long customerId){
		return billService.getNotPaidBillsCount(customerId);
	}
	
	@GetMapping("/{customerId}/{billId}/get-bill")
	public Bill getBillByCustomerIdAndBillId(@PathVariable Long customerId, @PathVariable Long billId) {
	    return billService.getBillByCustomerIdAndBillId(customerId, billId);
	}

	
}
