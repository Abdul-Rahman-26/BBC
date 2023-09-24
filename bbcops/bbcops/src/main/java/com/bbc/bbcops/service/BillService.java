package com.bbc.bbcops.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbc.bbcops.dao.BillDao;
import com.bbc.bbcops.exception.EmptyBillException;
import com.bbc.bbcops.model.Bill;

@Service
public class BillService {
	private BillDao billDao;

	@Autowired
	public BillService(BillDao billDao) {
		super();
		this.billDao = billDao;
	}

	public List<Bill> getBillsByCustomerId(Long customerId) {
		List<Bill> bills = billDao.getBillsByCustomerId(customerId);
		handleEmptyBillList(bills, customerId);
		bills = sortBillsByDueDateDescending(bills);
		return bills;
	}

	public List<Bill> getPaidBillsByCustomerId(Long customerId) {
		List<Bill> paidBills = billDao.getPaidBillsByCustomerId(customerId);
		handleEmptyBillList(paidBills, customerId);
		paidBills = sortBillsByDueDateDescending(paidBills);
		return paidBills;
	}

	public List<Bill> getNotPaidBillsByCustomerId(Long customerId) {
		List<Bill> notPaidBills = billDao.getNotPaidBillsByCustomerId(customerId);
		handleEmptyBillList(notPaidBills, customerId);
		notPaidBills = sortBillsByDueDateDescending(notPaidBills);
		return notPaidBills;
	}
	
	private void handleEmptyBillList(List<Bill> bills, Long customerId) {
		if (bills.isEmpty()) {
			throw new EmptyBillException("No bills found for customer with ID: " + customerId);
		}
	}

	public List<Bill> sortBillsByDueDateDescending(List<Bill> bills) {
		return bills.stream().sorted(Comparator.comparing(Bill::getDueDate).reversed()).collect(Collectors.toList());
	}
	public Bill getBillByCustomerIdAndBillId(long customerId, long billId) {
		return billDao.getBillByCustomerIdAndBillId(customerId, billId);
	}
	
	public Long countBills(List<Bill> bills) {
	    return (long) bills.size();
	}

	public Long getBillsCount(Long customerId) {
		List<Bill> bills = billDao.getBillsByCustomerId(customerId);
		handleEmptyBillList(bills, customerId);
		return countBills(bills);
	}

	public Long getPaidBillsCount(Long customerId) {
		List<Bill> paidBills = billDao.getPaidBillsByCustomerId(customerId);
		handleEmptyBillList(paidBills, customerId);
		return countBills(paidBills);
	}

	public Long getNotPaidBillsCount(Long customerId) {
		List<Bill> notPaidBills = billDao.getPaidBillsByCustomerId(customerId);
		handleEmptyBillList(notPaidBills, customerId);
		return countBills(notPaidBills);
	}
}
