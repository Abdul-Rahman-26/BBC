package com.bbc.bbcops.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Upi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String upiId;
	private String linkedBankAccountNo;
	private String linkedPhoneNumber;
	private double balance;

	@OneToOne
	private Customer customer;

	public Upi() {
	}

	public Upi(Long id, String upiId, String linkedBankAccount, String linkedPhoneNumber, double balance,
			Customer customer) {
		super();
		this.id = id;
		this.upiId = upiId;
		this.linkedBankAccountNo = linkedBankAccount;
		this.linkedPhoneNumber = linkedPhoneNumber;
		this.balance = balance;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public String getLinkedBankAccount() {
		return linkedBankAccountNo;
	}

	public void setLinkedBankAccount(String linkedBankAccount) {
		this.linkedBankAccountNo = linkedBankAccount;
	}

	public String getLinkedPhoneNumber() {
		return linkedPhoneNumber;
	}

	public void setLinkedPhoneNumber(String linkedPhoneNumber) {
		this.linkedPhoneNumber = linkedPhoneNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
