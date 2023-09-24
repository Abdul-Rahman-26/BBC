package com.bbc.bbcops.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

	@Id
	@Column(unique = true)
	private long customerId;
	private String customerName;
	private String otp;
	private String phoneNumber;
	private String email;
	private String address;

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	private List<Bill> bills;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Payment> payments;


	public Customer() {
		super();
	}


	public Customer(long customerId, String customerName, String otp, String phoneNumber, String email, String address,
			List<Bill> bills, List<Payment> payments) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.otp = otp;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.bills = bills;
		this.payments = payments;
	}


	public long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getOtp() {
		return otp;
	}


	public void setOtp(String otp) {
		this.otp = otp;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public List<Bill> getBills() {
		return bills;
	}


	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}


	public List<Payment> getPayments() {
		return payments;
	}


	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

}
