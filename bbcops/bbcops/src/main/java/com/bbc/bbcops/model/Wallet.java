package com.bbc.bbcops.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Wallet{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String walletName;
	private double amount;

	@OneToOne
	private Customer customer;


	public Wallet() {
		super();
	}
	
	
	public Wallet(Long id, String walletName, double amount, Customer customer) {
		super();
		this.id = id;
		this.walletName = walletName;
		this.amount = amount;
		this.customer = customer;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
