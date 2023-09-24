package com.bbc.bbcops.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long paymentId;
	private double amount;
	private double discountAmount;
	private double finalAmount;
	private boolean paidCurrency;
	private boolean paidinOnline;
	private LocalDate paymentDate;

	@ManyToOne
	@JsonIgnore
	private Customer customer;
	
	@OneToOne
	@JsonIgnore
	private Bill bill;

	public Payment() {
		super();
	}

	public Payment(long paymentId, double amount, double discountAmount, double finalAmount, boolean paidCurrency,
			boolean paidinOnline, LocalDate paymentDate, Customer customer, Bill bill) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.discountAmount = discountAmount;
		this.finalAmount = finalAmount;
		this.paidCurrency = paidCurrency;
		this.paidinOnline = paidinOnline;
		this.paymentDate = paymentDate;
		this.customer = customer;
		this.bill = bill;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public boolean isPaidCurrency() {
		return paidCurrency;
	}

	public void setPaidCurrency(boolean paidCurrency) {
		this.paidCurrency = paidCurrency;
	}

	public boolean isPaidinOnline() {
		return paidinOnline;
	}

	public void setPaidinOnline(boolean paidinOnline) {
		this.paidinOnline = paidinOnline;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	}
