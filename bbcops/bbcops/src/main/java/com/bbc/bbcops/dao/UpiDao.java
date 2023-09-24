package com.bbc.bbcops.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bbc.bbcops.model.Bill;
import com.bbc.bbcops.model.Customer;
import com.bbc.bbcops.model.Payment;
import com.bbc.bbcops.model.Upi;

@Repository
public class UpiDao {

	private SessionFactory sessionFactory;
	
	@Autowired
	public UpiDao(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public boolean checkUpi(Long customerId, Upi upi) {
		   try (Session session = sessionFactory.openSession()) {
	            Criteria criteria = session.createCriteria(Upi.class);
	            Customer customer = session.get(Customer.class, customerId);
	            criteria.add(Restrictions.eq("customer", customer));
	            criteria.add(Restrictions.eq("upiId", upi.getUpiId()));
	            List<Upi> matchingUpi = criteria.list();
	            return !matchingUpi.isEmpty();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	public String payBillByUpi(Long customerId, Long billId) {
	    try (Session session = sessionFactory.openSession()) {
	        Customer customer = getCustomerById(session, customerId);
	        if (customer == null) {
	            return "Customer not found";
	        }

	        Upi validUpi = getValidUpi(session, customer);
	        if (validUpi == null) {
	            return "Not a valid UPI account";
	        }

	        Bill bill = getBillById(session, billId, customerId);
	        if (bill == null) {
	            return "Bill not found";
	        }

	        if (bill.isPaid()) {
	            return "Bill is already paid";
	        }

	        double billAmount = bill.getBillAmount();
	        double discount = 0.05;
	        double discountedAmount = billAmount - (billAmount * discount);

	        double upiBalance = validUpi.getBalance();

	        if (upiBalance < discountedAmount) {
	            return "2";
	        }

	        Payment payment = createPayment(customer, bill, discountedAmount, billAmount, discount);

	        bill.setIsPaid(true);
	        validUpi.setBalance(upiBalance - discountedAmount);

	        savePaymentAndBill(session, payment, bill);
	        updateUpi(session, validUpi);

	        return "1";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "An error occurred during payment";
	    }
	}

	private void savePaymentAndBill(Session session, Payment payment, Bill bill) {
		session.save(payment);
		bill.setPayment(payment);
		session.saveOrUpdate(bill);
		session.beginTransaction().commit();
		
	}

	private Payment createPayment(Customer customer, Bill bill, double discountedAmount, double billAmount,
			double discount) {
		Payment payment = new Payment();
		payment.setAmount(billAmount);
		payment.setDiscountAmount(billAmount * discount);
		payment.setFinalAmount(discountedAmount);
		payment.setPaidCurrency(false);
		payment.setPaidinOnline(true);
		payment.setCustomer(customer);
		payment.setBill(bill);
		payment.setPaymentDate(LocalDate.now());
		return payment;
	}

	private Bill getBillById(Session session, Long billId, Long customerId) {
		Criteria billCriteria = session.createCriteria(Bill.class);
		billCriteria.add(Restrictions.eq("billId", billId));
		billCriteria.createAlias("customer", "c");
		billCriteria.add(Restrictions.eq("c.customerId", customerId));
		return (Bill) billCriteria.uniqueResult();
	}

	private Customer getCustomerById(Session session, Long customerId) {
		return session.get(Customer.class, customerId);
			}

	private Upi getValidUpi(Session session, Customer customer) {
	    Criteria upiCriteria = session.createCriteria(Upi.class);
	    upiCriteria.add(Restrictions.eq("customer", customer));
	    return (Upi) upiCriteria.uniqueResult();
	}

	
	private void updateUpi(Session session, Upi validUpi) {
	    session.update(validUpi);
	}

}
