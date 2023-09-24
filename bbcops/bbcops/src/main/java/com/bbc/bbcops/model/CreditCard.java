package com.bbc.bbcops.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CreditCard{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardName;
    private String cardNumber;
    private String zipCode;
    private LocalDate expiration;
    private String cvv;
    private double amount;

    @ManyToOne
    private Customer customer;
    
    public CreditCard() {
    	
    }
	public CreditCard(Long id, String cardName, String cardNumber, String zipCode, LocalDate expiration, String cvv,
			double amount) {
		super();
		this.id = id;
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.zipCode = zipCode;
		this.expiration = expiration;
		this.cvv = cvv;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
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