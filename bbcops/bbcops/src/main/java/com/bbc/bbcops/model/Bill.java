package com.bbc.bbcops.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long billId;
	private double unitConsumed;
	private double billAmount;
	private LocalDate billDuration;
	private LocalDate dueDate;
	private boolean isPaid;

	@JsonIgnore
	@ManyToOne
	private Customer customer;
	
	@JsonIgnore
	@OneToOne
	private Payment payment;


	public Bill() {
		super();
	}


	public Bill(long billId, double unitConsumed, double billAmount, LocalDate billDuration, LocalDate dueDate,
			boolean isPaid, Customer customer, Payment payment) {
		super();
		this.billId = billId;
		this.unitConsumed = unitConsumed;
		this.billAmount = billAmount;
		this.billDuration = billDuration;
		this.dueDate = dueDate;
		this.isPaid = isPaid;
		this.customer = customer;
		this.payment = payment;
	}


	public long getBillId() {
		return billId;
	}


	public void setBillId(long billId) {
		this.billId = billId;
	}


	public double getUnitConsumed() {
		return unitConsumed;
	}


	public void setUnitConsumed(double unitConsumed) {
		this.unitConsumed = unitConsumed;
	}


	public double getBillAmount() {
		return billAmount;
	}


	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}


	public LocalDate getBillDuration() {
		return billDuration;
	}


	public void setBillDuration(LocalDate billDuration) {
		this.billDuration = billDuration;
	}


	public LocalDate getDueDate() {
		return dueDate;
	}


	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}


	public boolean isPaid() {
		return isPaid;
	}


	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}


	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", unitConsumed=" + unitConsumed + ", billAmount=" + billAmount
				+ ", billDuration=" + billDuration + ", dueDate=" + dueDate + ", isPaid=" + isPaid + ", customer="
				+ customer + ", payment=" + payment + "]";
	}

	

}
